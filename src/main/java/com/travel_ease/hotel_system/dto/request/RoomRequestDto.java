package com.travel_ease.hotel_system.dto.request;

import com.travel_ease.hotel_system.enums.RoomStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomRequestDto {
    private int bedCount;
    private RoomStatusEnum status;
    private BigDecimal price;
    private String roomNumber;
    private String roomType;
    private String branchId;
}
