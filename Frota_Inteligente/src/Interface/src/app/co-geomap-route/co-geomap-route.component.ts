import {Component, OnInit, ViewChild} from '@angular/core';
import {AgmMap, LatLngBounds} from "@agm/core";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {interval} from "rxjs";
import {Car} from "../co-list-automoveis/co-list-automoveis.model";
import {CarMenu} from "../co-geomap/co-geomap.model";
import {CarService} from "../co-list-automoveis/co-list-automoveis.service";
import {ToastrService} from "ngx-toastr";
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from "@angular/material";
import {MAT_MOMENT_DATE_FORMATS, MomentDateAdapter} from "@angular/material-moment-adapter";
declare var google: any;

@Component({
  selector: 'co-geomap-route',
  templateUrl: './co-geomap-route.component.html',
  styleUrls: ['./co-geomap-route.component.css'],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'pt-BR'},

    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE]},
    {provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS},
  ],
})
export class CoGeomapRouteComponent implements OnInit {

  @ViewChild(AgmMap) agmMap: AgmMap;

  lat: number;
  lng: number;

  @BlockUI() blockUI: NgBlockUI;

  source = interval(50000);

  subscribe = this.source.subscribe(val => this.updatePosition());
  carsToShow: Car[] = [];
  carsMenu: CarMenu[]=[];
  constructor(private vehiclesService: CarService, private toastr: ToastrService) {

  }

  ngOnInit() {
    this.blockUI.start("Carregando Dados")
    this.vehiclesService.getAllCars().subscribe( result =>{
      if(result.length > 0){

        for(let car of result){
          if(car.lastSouthCoord !== undefined && car.lastSouthCoord !== null && this.lat === undefined){
            this.agmMap.mapReady.subscribe(map => {
              const bounds: LatLngBounds = new google.maps.LatLngBounds();
              for(let item of result) {
                bounds.extend(new google.maps.LatLng(item.lastSouthCoord, item.lastWstCoord));
                map.fitBounds(bounds);
              }
            });
          }
          let carMenu:CarMenu = new CarMenu();
          carMenu.showCar = false;
          carMenu.carObject = car;
          this.carsMenu.push(carMenu);
        }
        this.blockUI.stop();
      }


    });

    // this.source.subscribe(val => this.updatePosition());
  }

  updatePosition(){
    let ids: number[] = [];
    console.log("ENTREI")
    if(this.carsToShow.length > 0) {

      for (let item of this.carsToShow) {
        ids.push(item.id);
      }

      this.vehiclesService.getUpdatedPosition(ids).subscribe(result => {
        for (let item of result) {
          this.carsToShow.find(x => x.id === item.id).lastSouthCoord = item.lastSouthCoord;
          this.carsToShow.find(x => x.id === item.id).lastWstCoord = item.lastWstCoord;
        }
      });
    }
  }

  addPoint(id:number){
    let position: number = this.carsToShow.findIndex(x => x.id === id);
    if(position !== undefined && position !== null && position !== -1){
      this.carsToShow.splice(position,1);
    }else{
      this.carsToShow.push(this.carsMenu.find(x => x.carObject.id === id).carObject);
    }
  }

}
