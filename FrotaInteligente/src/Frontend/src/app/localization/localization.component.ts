import {Component, OnInit, ViewChild} from '@angular/core';
import {Vehicle} from "../vehicles/vehicles.model";
import {VehiclesService} from "../vehicles/vehicles.service";
import {ToastrService} from "ngx-toastr";
import {AgmMap} from "@agm/core";
import { interval } from 'rxjs/observable/interval';
import 'rxjs/add/observable/fromEvent';


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
  private source = interval(30000);
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

    this.source.subscribe(val => this.updatePosition());
  }

  updatePosition(){
    this.vehiclesService.getAllVehicles().subscribe(result => {
      for (let car of result) {
        if (this.vehicles.find(x => x.id === car.id) == undefined) {
          car.inMenu = false;
          this.vehicles.push(car);
        } else {
          this.vehicles.find(x => x.id === car.id).last_west_coord = car.last_west_coord;
          this.vehicles.find(x => x.id === car.id).last_south_coord = car.last_south_coord;
          this.vehicles.find(x => x.id === car.id).name= car.name;
        }
      }

      for(let car of this.vehicles){
        if (this.vehiclesToShow.find(x => x.id === car.id) == undefined){

        }else{
          this.vehiclesToShow.find(x => x.id === car.id).last_west_coord = car.last_west_coord;
          this.vehiclesToShow.find(x => x.id === car.id).last_south_coord = car.last_south_coord;
          this.vehicles.find(x => x.id === car.id).name= car.name;
        }
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

  onMouseOver(infoWindow, gm) {
    gm.lastOpen = infoWindow;
    infoWindow.open();
  }

  onMouseOut(infoWindow, gm) {
    gm.lastOpen = infoWindow;
    infoWindow.close();
  }
}
