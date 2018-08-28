import {Component, OnInit} from '@angular/core';
import {Vehicle} from "../vehicles/vehicles.model";
import {VehiclesService} from "../vehicles/vehicles.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-localization',
  templateUrl: './localization.component.html',
  styleUrls: ['./localization.component.css']
})
export class LocalizationComponent implements OnInit {
  vehicles: Vehicle[]= [];
  vehiclesToShow: Vehicle[]= [];
  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';
  mapClass: string =  'col-md-12 map-area';

  carsMenu: boolean = false;
  lat: number = 51.678418;
  lng: number = 7.809007;

  constructor(private vehiclesService: VehiclesService, private toastr: ToastrService) { }

  ngOnInit() {
    this.vehiclesService.getAllVehicles().subscribe( result =>{
      this.vehicles = result;
    });

  }

  changeOpt(){
    this.open = !this.open;
  }

  changeCarMenu(){
    this.carsMenu = !this.carsMenu;
    if(this.carsMenu){
      this.mapClass = 'col-md-10 map-area';
    }else{
      this.mapClass = 'col-md-12 map-area';
    }
  }

  addPoint(vehicleId: number){

    if(this.vehiclesToShow.find(x => x.id === vehicleId) == undefined){
      if(this.vehicles.find(x => x.id === vehicleId).last_west_coord == null || this.vehicles.find(x => x.id === vehicleId) == null){
        this.toastr.warning("Este veiculo não possui as coordenadas necessarias!");
      }else {
        this.vehiclesToShow.push(this.vehicles.find(x => x.id === vehicleId));
      }
    }else{
      this.vehiclesToShow.splice(this.vehiclesToShow.findIndex(x => x.id === vehicleId),1);
    }

  }
}
