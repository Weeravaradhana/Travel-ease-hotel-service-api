package com.travel_ease.hotel_system.reposiroty;

import com.travel_ease.hotel_system.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility,Long> {
}
