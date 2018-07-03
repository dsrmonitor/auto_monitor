import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Vehicle} from "./vehicles.model";
import {VehiclesService} from "./vehicles.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'co-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';

  linhaSelecionada : number = -1;
  vehicles: Vehicle[];

  constructor(private vehiclesService: VehiclesService, private router: Router, private route:ActivatedRoute,private toastr: ToastrService) {

  }

  ngOnInit() {
    this.getVehicles();
  }

  getVehicles(){
    this.blockUI.start("Carregando Dados...");
    this.vehiclesService.getAllVehicles().subscribe(vehicles =>{
      this.vehicles = vehicles;
      this.blockUI.stop();
    });
  }

  setLinhaSelecionada(index){
    this.linhaSelecionada = index;
  }

  changeOpt(){
    this.open = !this.open;
  }

  goNew(){
    this.router.navigate(['carros-c',this.route.snapshot.params['id'],1,0]);
  }

  goView(){
    this.router.navigate(['carros-c',this.route.snapshot.params['id'],2,this.vehicles[this.linhaSelecionada].id]);
  }

  goEdit(){
    this.router.navigate(['carros-c',this.route.snapshot.params['id'],3,this.vehicles[this.linhaSelecionada].id]);
  }

  goDelete(){
    this.blockUI.start("Carregando dados...");
    this.vehiclesService.delete(this.vehicles[this.linhaSelecionada].id).subscribe(id => {
      this.linhaSelecionada= -1;
      this.toastr.success("Aluno deletado","Sucesso!");
      this.getVehicles()
    },error => {
      this.toastr.error("Erro ao excluir","Erro!");
    });
    this.router.navigate(['carros',this.route.snapshot.params['id']]);
  }

  goSearch(search: string){
    this.blockUI.start("Buscando dados");
    if(search === ''){
      this.vehiclesService.getAllVehicles().subscribe(
        vehicles =>{
          this.blockUI.stop();
          this.vehicles = vehicles;
        }
      );
    }else{
      this.vehiclesService.searchVehicles(search).subscribe(
        vehicles =>{
          this.blockUI.stop();
          this.vehicles= vehicles;
        }
      );
    }
  }
}
