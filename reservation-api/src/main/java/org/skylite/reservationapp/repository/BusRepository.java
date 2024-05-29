package org.skylite.reservationapp.repository;

import java.util.List;

import org.skylite.reservationapp.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {
	List<Bus> findByFromLocAndToLoc(String fromLoc, String toLoc);
}
