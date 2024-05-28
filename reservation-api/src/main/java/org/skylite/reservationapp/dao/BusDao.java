package org.skylite.reservationapp.dao;

import java.util.Optional;

import org.skylite.reservationapp.model.Bus;
import org.skylite.reservationapp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
	@Autowired
	private BusRepository busRepo;
	
	public Bus saveOrUpdateBus(Bus bus) {
		return busRepo.save(bus);
	}
	
	public Optional<Bus> getBus(int id){
		return busRepo.findById(id);
	}
	
	public void deleteBus(Bus bus) {
		busRepo.delete(bus);
	}
	
}
