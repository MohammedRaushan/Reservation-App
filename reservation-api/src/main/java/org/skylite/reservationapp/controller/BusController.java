package org.skylite.reservationapp.controller;

import java.util.List;

import org.skylite.reservationapp.dto.BusRequest;
import org.skylite.reservationapp.dto.BusResponse;
import org.skylite.reservationapp.dto.ResponseStructure;
import org.skylite.reservationapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bus")
public class BusController {
	@Autowired
	private BusService service;
	
	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<BusResponse>> saveBus(@Valid @RequestBody BusRequest busRequest, @PathVariable(name="id") int admin_id) {
		return service.saveBus(busRequest, admin_id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<BusResponse>> updateBus(@Valid @RequestBody BusRequest busRequest,@PathVariable int id) {
		return service.updateBus(busRequest, id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<BusResponse>> getBus(@PathVariable int id) {
		return service.getBus(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBus(@PathVariable(name="id") int adminId, @RequestParam int busId) {
		return service.deleteBus(busId, adminId);
	}
	@GetMapping("/get-by-locations")
	public ResponseEntity<ResponseStructure<List<BusResponse>>> getBus(@RequestParam String from,@RequestParam String to) {
		return service.getBus(from, to);
	}
}
