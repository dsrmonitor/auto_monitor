import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Car} from "../co-list-automoveis.model";
import {CarService} from "../co-list-automoveis.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-co-create-automovel',
  templateUrl: './co-create-automovel.component.html',
  styleUrls: ['./co-create-automovel.component.css']
})
export class CoCreateAutomovelComponent implements OnInit {



  car : Car = new Car();
  titulo: string = "Cadastrar";
  @BlockUI() blockUI: NgBlockUI;
  edit: boolean = false;

  constructor(private toastr: ToastrService,
              private carService: CarService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if(params['id']){
        this.blockUI.start("Carregando dados...");
        this.titulo = "Editar";
        this.edit = true;
        this.carService.getCar(params['id']).subscribe(result =>{
          this.car = result;
          this.blockUI.stop();
        },err => {
          this.blockUI.stop();
          this.toastr.error("Erro ao buscar os dados!","Erro",{timeOut: 2000});
          this.router.navigate(['frota-inteligente/list-cars']);
        });
      }
    });
  }

  back(){
    this.router.navigate(['frota-inteligente/list-cars']);
  }

  save(){
    if(this.edit){
      this.carService.update(this.car).subscribe(result => {
        this.toastr.success("Dados salvos com sucesso!","Sucesso",{timeOut: 2000});
        this.router.navigate(['frota-inteligente/list-cars']);
      }, error => {
        this.toastr.error("Erro ao salvar os dados!");
      });
    }else {
      this.carService.save(this.car).subscribe(result => {
        this.toastr.success("Dados salvos com sucesso!","Sucesso",{timeOut: 2000});
        this.router.navigate(['frota-inteligente/list-cars']);
      }, error => {
        this.toastr.error("Erro ao salvar os dados!");
      });
    }
  }
}
