import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Car} from "../co-list-automoveis.model";
import {CarService} from "../co-list-automoveis.service";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {ToastrService} from "ngx-toastr";
import {MatSnackBar} from "@angular/material";
import {ConstantesUtil} from "../../util/constantes.util";

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

  constructor(private snackBar: MatSnackBar,
              private carService: CarService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      if(params['id']){
        this.blockUI.start(ConstantesUtil.LOADING);
        this.titulo = "Editar";
        this.edit = true;
        this.carService.getCar(params['id']).subscribe(result =>{
          this.car = result;
          this.blockUI.stop();
        },err => {
          this.blockUI.stop();
          this.snackBar.open(ConstantesUtil.FAIL_LOAD_DATA,ConstantesUtil.CLOSE,{duration: 3000});
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
      this.blockUI.start(ConstantesUtil.SAVING);
      this.carService.update(this.car).subscribe(result => {
        this.blockUI.stop();
        this.snackBar.open(ConstantesUtil.SUCESS_SAVE,ConstantesUtil.CLOSE,{duration: 3000});
        this.router.navigate(['frota-inteligente/list-cars']);
      }, error => {
        this.blockUI.stop();
        this.snackBar.open(ConstantesUtil.FAIL_SAVE_DATA,ConstantesUtil.CLOSE,{duration: 3000});
      });
    }else {
      this.blockUI.start(ConstantesUtil.SAVING);
      this.carService.save(this.car).subscribe(result => {
        this.blockUI.stop();
        this.snackBar.open(ConstantesUtil.SUCESS_SAVE,ConstantesUtil.CLOSE,{duration: 3000});
        this.router.navigate(['frota-inteligente/list-cars']);
      }, error => {
        this.blockUI.stop();
        this.snackBar.open(ConstantesUtil.FAIL_SAVE_DATA,ConstantesUtil.CLOSE,{duration: 3000});
      });
    }
  }
}
