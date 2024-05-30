package org.skylite.reservationapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.skylite.reservationapp.dao.AdminDao;
import org.skylite.reservationapp.dao.BusDao;
import org.skylite.reservationapp.dto.BusRequest;
import org.skylite.reservationapp.dto.BusResponse;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.exception.AdminNotFoundException;
import org.skylite.reservationapp.exception.BusNotFoundException;
import org.skylite.reservationapp.exception.NoBusPresentException;
import org.skylite.reservationapp.model.Admin;
import org.skylite.reservationapp.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(BusRequest busRequest, int admin_id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(admin_id);
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			Bus bus = mapToBus(busRequest);
			dbAdmin.getBuses().add(bus);
			adminDao.saveOrUpdateAdmin(dbAdmin);
			bus.setAdmin(dbAdmin);
			structure.setData(mapToBusResponse(busDao.saveOrUpdateBus(bus)));
			structure.setMessage("Bus registered");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new AdminNotFoundException("Invalid admin id");
	}

	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(BusRequest busRequest, int bus_id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> recBus = busDao.getBus(bus_id);
		if (recBus.isPresent()) {
			Bus dbBus = mapToBus(busRequest);
			dbBus.setId(bus_id);
			structure.setData(mapToBusResponse(busDao.saveOrUpdateBus(dbBus)));
			structure.setMessage("Bus updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new BusNotFoundException("Cannot update as bus id is invalid");
	}

	public ResponseEntity<ResponseStructure<BusResponse>> getBus(int bus_id) {
		ResponseStructure<BusResponse> structure = new ResponseStructure<>();
		Optional<Bus> recBus = busDao.getBus(bus_id);
		if (recBus.isPresent()) {
			structure.setData(mapToBusResponse(recBus.get()));
			structure.setMessage("Bus Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(structure);
		}
		throw new BusNotFoundException("Invalid Bus ID");
	}

	public ResponseEntity<ResponseStructure<String>> deleteBus(int bus_id, int admin_id) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.getAdmin(admin_id);
		if (recAdmin.isPresent()) {
			Optional<Bus> recBus = busDao.getBus(bus_id);
			if (recBus.isPresent()) {
				Admin dbAdmin = recAdmin.get();
				Bus dbBus = recBus.get();
//				boolean res = deleteBusFromAdmin(dbAdmin.getBuses(), bus_id);
				if(deleteBusFromAdmin(dbAdmin.getBuses(), bus_id)) {					
					System.out.println(dbAdmin.getBuses());
					adminDao.saveOrUpdateAdmin(dbAdmin);
					busDao.deleteBus(dbBus);
					structure.setData("Bus Deleted");
					structure.setMessage("Bus with id "+dbBus.getId()+" Deleted");
					structure.setStatusCode(HttpStatus.OK.value());
					return ResponseEntity.status(HttpStatus.OK).body(structure);
				}
				structure.setData("Bus not deleted");
				structure.setMessage("Admin doesn't own this bus");
				structure.setStatusCode(HttpStatus.FORBIDDEN.value());
				return ResponseEntity.status(HttpStatus.OK).body(structure);
				
			}
			throw new BusNotFoundException("Invalid Bus Id");
		}
		throw new AdminNotFoundException("Invalid Admin Id");
	}
	
	public ResponseEntity<ResponseStructure<List<BusResponse>>> getBus(String fromLoc, String toLoc) {
		ResponseStructure<List<BusResponse>> structure = new ResponseStructure<>();
		List<Bus> buses = busDao.getBus(fromLoc, toLoc);
		if(buses.size()<1) {
			throw new NoBusPresentException("No bus with given from and to locations");
		}
		List<BusResponse> resBus = new ArrayList<>();
		buses.forEach((bus)->{
			resBus.add(mapToBusResponse(bus));
		});
		structure.setData(resBus);
		structure.setMessage("List of buses with given From and To locations");
		structure.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(structure);
	}

	private Bus mapToBus(BusRequest busRequest) {
		return Bus.builder().name(busRequest.getName()).dateOfDeparture(busRequest.getDateOfDeparture())
				.busNo(busRequest.getBusNo()).fromLoc(busRequest.getFromLoc()).toLoc(busRequest.getToLoc())
				.numberOfSeats(busRequest.getNumberOfSeats()).build();
	}
	
	private BusResponse mapToBusResponse(Bus bus) {
		return BusResponse.builder().busNo(bus.getBusNo())
				.fromLoc(bus.getFromLoc())
				.name(bus.getName())
				.toLoc(bus.getToLoc())
				.id(bus.getId())
				.numberOfSeats(bus.getNumberOfSeats()).build();
	}

	/**
	 * This method is used to delete a specific bus from the list of buses of given
	 * admin.
	 * 
	 * @param List<Bus>
	 * @param int
	 */
	private boolean deleteBusFromAdmin(List<Bus> adminBuses, int busId) {
		if (adminBuses.size() < 1) {
			throw new NoBusPresentException("Admin does not have any bus");
		}
		for (Bus bus : adminBuses) {
			if (bus.getId() == busId) {
				adminBuses.remove(bus);
				return true;
			}
		}
		return false;
	}
	

}
