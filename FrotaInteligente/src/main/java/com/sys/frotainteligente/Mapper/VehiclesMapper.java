package com.sys.frotainteligente.Mapper;

import com.sys.frotainteligente.DTO.VehiclesDTO;
import com.sys.frotainteligente.Entity.Vehicles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VehiclesMapper {

    public static VehiclesDTO EntityToDTO(Vehicles vehicles){
        VehiclesDTO vehiclesDTO = new VehiclesDTO();

        vehiclesDTO.setId(vehicles.getId());
        vehiclesDTO.setChassi_number(vehicles.getChassi_number());
        vehiclesDTO.setDescription(vehicles.getDescription());
        vehiclesDTO.setName(vehicles.getName());
        vehiclesDTO.setImei(vehicles.getImei());
        vehiclesDTO.setLicense(vehicles.getLicense());
        vehiclesDTO.setPhone_number(vehicles.getPhone_number());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        if(vehicles.getUpdated_at() != null){
            vehiclesDTO.setUpdated_at(sdf.format(vehicles.getUpdated_at()));
        }
        if(vehicles.getDeleted_at() != null) {
            vehiclesDTO.setDeleted_at(sdf.format(vehicles.getDeleted_at()));
        }
        if(vehicles.getLast_south_coord()!=null){
            vehiclesDTO.setLast_south_coord(Double.valueOf(vehicles.getLast_south_coord()));
        }
        if(vehicles.getLast_west_coord() != null){
            vehiclesDTO.setLast_west_coord(Double.valueOf(vehicles.getLast_west_coord()));
        }
        return vehiclesDTO;
    }

    public static Vehicles DTOToEntity(VehiclesDTO vehiclesDTO){
        Vehicles vehicles = new Vehicles();

        vehicles.setName(vehiclesDTO.getName());
        vehicles.setImei(vehiclesDTO.getImei());
        vehicles.setChassi_number(vehiclesDTO.getChassi_number());
        vehicles.setDescription(vehiclesDTO.getDescription());
        vehicles.setLicense(vehiclesDTO.getLicense());
        vehicles.setPhone_number(vehiclesDTO.getPhone_number());

        return vehicles;
    }

    public static List<VehiclesDTO> ListEntityToListDTO(List<Vehicles> vehicles){
        List<VehiclesDTO> vehiclesDTO = new ArrayList<>();

        for(Vehicles vehicles1 : vehicles){
            vehiclesDTO.add(EntityToDTO(vehicles1));
        }

        return vehiclesDTO;
    }
}
