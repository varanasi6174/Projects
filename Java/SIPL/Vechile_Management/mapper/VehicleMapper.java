package com.sipl.vehicle.management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sipl.vehicle.management.entity.Vehicle;
import com.sipl.vehicle.management.vehicleDTO.VehicleDTO;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

	VehicleMapper INSTANCE = Mappers.getMapper(VehicleMapper.class);

	Vehicle mapVehicleDTOToVehicle(VehicleDTO vehicleDto);

	VehicleDTO mapVehicleToVehicleDTO(Vehicle vehicle);
}
