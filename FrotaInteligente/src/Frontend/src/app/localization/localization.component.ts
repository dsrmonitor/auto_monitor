import {Component, OnInit, ViewChild} from '@angular/core';
import {Vehicle} from "../vehicles/vehicles.model";
import {VehiclesService} from "../vehicles/vehicles.service";
import {ToastrService} from "ngx-toastr";
import {AgmMap} from "@agm/core";

@Component({
  selector: 'app-localization',
  templateUrl: './localization.component.html',
  styleUrls: ['./localization.component.css']
})
export class LocalizationComponent implements OnInit {
  @ViewChild(AgmMap) agmMap: AgmMap;
  vehicles: Vehicle[]= [];
  vehiclesToShow: Vehicle[]= [];
  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';
  mapClass: string =  'col-md-12 map-area';

  carsMenu: boolean = false;
  lng: number ;
  lat: number ;

  constructor(private vehiclesService: VehiclesService, private toastr: ToastrService) {

  }

  ngOnInit() {
    this.vehiclesService.getAllVehicles().subscribe( result =>{
      this.vehicles = result;
      if(this.vehicles.length > 0){
        this.lat = this.vehicles[0].last_west_coord;
        this.lng = this.vehicles[0].last_south_coord;
      }
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
      this.vehiclesToShow.push(this.vehicles.find(x => x.id === vehicleId));
      this.vehicles.find(x => x.id === vehicleId).inMenu = true;
    }else{
      this.vehiclesToShow.splice(this.vehiclesToShow.findIndex(x => x.id === vehicleId),1);
      this.vehicles.find(x => x.id === vehicleId).inMenu = false;
    }
  }
}
