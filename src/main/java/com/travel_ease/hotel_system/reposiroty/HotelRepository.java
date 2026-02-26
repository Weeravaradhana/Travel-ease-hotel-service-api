package com.travel_ease.hotel_system.reposiroty;


import com.travel_ease.hotel_system.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
