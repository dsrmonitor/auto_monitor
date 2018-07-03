import { Component, OnInit } from '@angular/core';
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {Vehicle} from "./vehicles.model";
import {VehiclesService} from "./vehicles.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'co-vehicles-cadastrar',
  templateUrl: './vehicles-cadastrar.component.html',
  styleUrls: ['./vehicles-cadastrar.component.css']
})

export class VehiclesCadastrarComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';

  vehicle: Vehicle = new Vehicle();
  type: number;
  disabledEdit: boolean = false;
  title: string;

  constructor(private vehiclesService: VehiclesService, private toastr: ToastrService, private router: Router, private activateRoute:ActivatedRoute) {

  }

  ngOnInit() {

    console.log("TIPO: "+this.activateRoute.snapshot.params['type'])

      if(this.activateRoute.snapshot.params['type']  == 1){
        this.title = "Cadastrar";
        this.type = 1;
      }else if(this.activateRoute.snapshot.params['type']  == 2){
        this.disabledEdit = true;
        this.title= 'Visualizar';
        this.type = 2;
        this.getVehicle();
      }else if(this.activateRoute.snapshot.params['type']  == 3){
        this.title= 'Editar';
        this.type = 3;
        this.getVehicle();
      }

  }

  getVehicle(){
    this.blockUI.start("Carregando Dados...");
    this.vehiclesService.getVehicleById(this.activateRoute.snapshot.params['idv']).subscribe(vehicles => {
      this.vehicle = vehicles;
      console.log(vehicles);
      this.blockUI.stop();
    });
  }

  changeOpt(){
    this.open = !this.open;
  }

  goBack(){
    this.router.navigate(['carros',this.activateRoute.snapshot.params['id']]);
  }

  goSave(form) {

    if (!form.valid) {
      this.toastr.error("Preencha todos os campos obrigatorios");
      return;
    }

    if(this.type === 1) {
        this.vehiclesService.create(this.vehicle).subscribe(vehicle => {
          if (vehicle.name !== null) {
            if(vehicle.id === -1){
              this.toastr.error("Ja existe um automovel com estes dados","Erro!");
            }else {
              this.toastr.success("Veiculo cadastrado", "Sucesso!");
              this.goBack();
            }
          }else{
            this.toastr.error("Erro ao cadastrar aluno","Erro!");
          }
        });
    }else{
      this.vehiclesService.update(this.vehicle).subscribe(ok =>{
        this.toastr.success("Dados atualizados","Sucesso!");
        this.goBack();
      })
    }
  }

}
