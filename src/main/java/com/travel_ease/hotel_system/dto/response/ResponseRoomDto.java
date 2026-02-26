package com.travel_ease.hotel_system.dto.response;

import com.travel_ease.hotel_system.enums.RoomStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseRoomDto {
    private String roomId;
    private int bedCount;
    private RoomStatusEnum isAvailable;
    private BigDecimal price;
    private String roomNumber;
    private String roomType;
    private String branchId;
    // facilities
    private List<ResponseFacilityDto>  facilities;
    // room images
    private List<ResponseRoomImageDto> images;
}
