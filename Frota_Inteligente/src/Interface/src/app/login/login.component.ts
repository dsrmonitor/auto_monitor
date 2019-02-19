import { Component, OnInit } from '@angular/core';
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Login} from "./login.model";
import {ToastrService} from "ngx-toastr";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {CustomerService} from "../CustomerService";
import {ConstantesUtil} from "../util/constantes.util";
import {MatSnackBar} from "@angular/material";

@Component({
  selector: 'co-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  login: Login = new Login();

  ngOnInit(): void {
  }



  constructor(private loginService: LoginService, private customer: CustomerService, private router: Router, private snackBar: MatSnackBar) {
  }


  tryLogin() {
    this.blockUI.start(ConstantesUtil.LOADING);
    this.loginService.verifyLogin(this.login).subscribe(
        r => {
          this.blockUI.stop();
          if (r) {
            this.snackBar.open(ConstantesUtil.LOGIN_SUCESS,ConstantesUtil.CLOSE,{duration:4000});
            this.customer.setToken(''+r);
            this.router.navigate(['frota-inteligente']);
          }
        }, r => {
          this.blockUI.stop();
          this.snackBar.open(ConstantesUtil.INVALID_DATA,ConstantesUtil.CLOSE,{duration:4000});
        });
  }

}
