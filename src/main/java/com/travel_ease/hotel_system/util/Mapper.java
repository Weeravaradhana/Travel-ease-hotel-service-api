package com.travel_ease.hotel_system.util;

import com.travel_ease.hotel_system.dto.request.HotelRequestDto;
import com.travel_ease.hotel_system.dto.response.*;
import com.travel_ease.hotel_system.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Mapper {

   private final ByteCodeHandler byteCodeHandle;
   private final FileDataExtractor fileDataExtractor;

    public Hotel toHotel(HotelRequestDto dto) throws SQLException {
        return dto==null ? null : Hotel.builder()
                .hotelId(UUID.randomUUID().toString())
                .hotelName(dto.getHotelName())
                .starRating(dto.getStarRating())
                .description(byteCodeHandle.stringToBlob(dto.getDescription()))
                .activeStatus(true)
                .startingFrom(dto.getStartingFrom())
                .build();
    }

    public ResponseHotelDto toResponseHotelDto(Hotel selectedHotel) throws SQLException {
        return ResponseHotelDto.builder()
                .hotelId(selectedHotel.getHotelId())
                .hotelName(selectedHotel.getHotelName())
                .starRating(selectedHotel.getStarRating())
                .createdAt(selectedHotel.getCreatedAt())
                .updatedAt(selectedHotel.getUpdatedAt())
                .description(byteCodeHandle.blobToString(selectedHotel.getDescription()))
                .activeStatus(selectedHotel.isActiveStatus())
                .startingFrom(selectedHotel.getStartingFrom())
                .branches(selectedHotel.getBranches().stream().map(e-> {
                    try {
                        return toResponseBranchDto(e);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }).collect(Collectors.toList()))
                .build();
    }

    public ResponseRoomDto toResponseRoomDto(Room room){

        List<ResponseFacilityDto> facilities = Collections.emptyList();
        if (room.getFacilities() != null && !room.getFacilities().isEmpty()) {
            facilities = room.getFacilities().stream()
                    .map(this::mapFacilityToDto)
                    .collect(Collectors.toList());
        }

        List<ResponseRoomImageDto> images = Collections.emptyList();
        if (room.getRoomImages() != null && !room.getRoomImages().isEmpty()) {
            images = room.getRoomImages().stream()
                    .map(this::mapImageToDto)
                    .collect(Collectors.toList());
        }

        return ResponseRoomDto.builder()
                .roomId(room.getRoomId())
                .roomNumber(room.getRoomNumber())
                .roomType(room.getType())
                .bedCount(room.getBedCount())
                .price(room.getPrice())
                .isAvailable(room.getStatus())
                .branchId(room.getBranch().getBranchId())
                .facilities(facilities)
                .images(images)
                .build();
    }

    private ResponseRoomImageDto mapImageToDto(RoomImage image) {
        return ResponseRoomImageDto.builder()
                .id(image.getId())
                .directory(fileDataExtractor.byteArrayToString(image.getFileFormatter().getDirectory()))
                .fileName(fileDataExtractor.byteArrayToString(image.getFileFormatter().getFileName()))
                .hash(fileDataExtractor.byteArrayToString(image.getFileFormatter().getHash()))
                .resourceUrl(fileDataExtractor.byteArrayToString(image.getFileFormatter().getResourceUrl()))
                .roomId(image.getRoom().getRoomId())
                .build();
    }

    private ResponseFacilityDto mapFacilityToDto(Facility facility) {
        return ResponseFacilityDto.builder()
                .id(facility.getId())
                .name(facility.getName())
                .roomId(facility.getRoom().getRoomId())
                .build();
    }

    private ResponseBranchDto  toResponseBranchDto(Branch selectedBranch) throws SQLException {
        return ResponseBranchDto.builder()
                .branchId(selectedBranch.getBranchId())
                .branchName(selectedBranch.getBranchName())
                .branchType(selectedBranch.getBranchType())
                .roomCount(selectedBranch.getRoomCount())
                .hotelId(selectedBranch.getHotel().getHotelId())
                .address(toResponseAddressDto(selectedBranch.getAddress()))
                .build();
    }

    private ResponseAddressDto toResponseAddressDto(Address selectedAddress) {
        return ResponseAddressDto.builder()
                .addressId(selectedAddress.getAddressId())
                .addressLine(selectedAddress.getAddressLine())
                .city(selectedAddress.getCity())
                .country(selectedAddress.getCountry())
                .longitude(selectedAddress.getLongitude())
                .latitude(selectedAddress.getLatitude())
                .build();
    }
}
