import {Component, OnInit} from '@angular/core';
import {Vehicle} from "../vehicles/vehicles.model";

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
  lat: number = 51.678418;
  lng: number = 7.809007;

  constructor() { }

  ngOnInit() {
    let v1 = new Vehicle();
    v1.name = "Ford Ka";
    v1.last_west_coord = 51.678418;
    v1.last_south_coord = 7.809007;
    v1.id = 1;

    let v2 = new Vehicle();
    v2.name = "Fiat Uno";
    v2.last_west_coord = 51.878418;
    v2.last_south_coord = 7.809007;
    v2.id = 2;

    this.vehicles.push(v1);
    this.vehicles.push(v2);

  }

  changeOpt(){
    this.open = !this.open;
  }

  addPoint(vehicleId: number){

    if(this.vehiclesToShow.find(x => x.id === vehicleId) == undefined){
      this.vehiclesToShow.push(this.vehicles.find(x => x.id === vehicleId));
    }else{
      this.vehiclesToShow.splice(this.vehiclesToShow.findIndex(x => x.id === vehicleId),1);
    }

  }
}
