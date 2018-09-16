package com.sys.frotainteligente.Resource;

import com.google.gson.Gson;
import com.sys.frotainteligente.DTO.VehiclesDTO;
import com.sys.frotainteligente.Entity.Vehicles;
import com.sys.frotainteligente.Mapper.VehiclesMapper;
import com.sys.frotainteligente.Repository.VehiclesRepository;
import com.sys.frotainteligente.Util.ConstantesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://"+ConstantesUtil.IP_ADDRESS+":4200")
@RequestMapping("/vehicles")
public class VehiclesResource {

    Gson gson = new Gson();

    @Autowired
    VehiclesRepository vehiclesRepository;

    @GetMapping("get-all-vehicles")
    public String getAllVehicles(){
        List<Vehicles> result = vehiclesRepository.findAll();
        System.out.println(VehiclesMapper.ListEntityToListDTO(result).toString());
        return gson.toJson(VehiclesMapper.ListEntityToListDTO(result));
    }

    @GetMapping("get-vehicle-by-id/{id}")
    public String getVehicleById(@PathVariable Long id){
        Vehicles result = vehiclesRepository.getOne(id);
        return gson.toJson(VehiclesMapper.EntityToDTO(result));
    }

    @GetMapping("search-vehicle/{param}")
    public String searchVehicle(@PathVariable String param){
        List<Vehicles> result =vehiclesRepository.searchVehicleByParam(param);
        return gson.toJson(VehiclesMapper.ListEntityToListDTO(result));
    }

    @PostMapping(path="/save-vehicle",  consumes = "application/json", produces = "application/json")
    public String createVehicle(@RequestBody VehiclesDTO vehiclesDTO){
        Vehicles vehicles = VehiclesMapper.DTOToEntity(vehiclesDTO);
        vehicles.setUpdated_at(new Date());
        Vehicles result = vehiclesRepository.save(vehicles);
        return gson.toJson(VehiclesMapper.EntityToDTO(result));
    }

    @PutMapping(path="/update-vehicle",  consumes = "application/json", produces = "application/json")
    public @ResponseBody String updateVehicle(@RequestBody VehiclesDTO vehiclesDTO){
        vehiclesRepository.updateVehicle(vehiclesDTO.getId(),vehiclesDTO.getName(),vehiclesDTO.getImei(), vehiclesDTO.getChassi_number(),vehiclesDTO.getDescription(),vehiclesDTO.getLicense(),vehiclesDTO.getPhone_number(),new Date());
        return gson.toJson(true);
    }

    @DeleteMapping("/delete-vehicle/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehiclesRepository.deleteById(id);
        return gson.toJson(id);
    }

}
