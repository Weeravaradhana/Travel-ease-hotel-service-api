package com.travel_ease.hotel_system.reposiroty;

import com.travel_ease.hotel_system.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,String> {
}
