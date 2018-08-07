import {Component, OnInit, ViewChild} from '@angular/core';
import { AgmCoreModule } from '@agm/core';
import {Vehicle} from "../vehicles/vehicles.model";

@Component({
  selector: 'app-localization',
  templateUrl: './localization.component.html',
  styleUrls: ['./localization.component.css']
})
export class LocalizationComponent implements OnInit {
  vehicles: Vehicle[]= [];
  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';
  lat: number = 51.678418;
  lng: number = 7.809007;
  lat2: number =  51.878418;
  lng2: number = 7.909007;
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
}
