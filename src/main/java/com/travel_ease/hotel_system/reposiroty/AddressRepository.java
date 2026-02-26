package com.travel_ease.hotel_system.reposiroty;

import com.travel_ease.hotel_system.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,String> {
}
