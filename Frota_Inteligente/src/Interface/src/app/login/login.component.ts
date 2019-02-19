import { Component, OnInit } from '@angular/core';
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Login} from "./login.model";
import {ToastrService} from "ngx-toastr";
import {BlockUI, NgBlockUI} from "ng-block-ui";
import {CustomerService} from "../CustomerService";

@Component({
  selector: 'co-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;

  login: Login = new Login();

  ngOnInit(): void {
    this.newUser();
  }



  constructor(private loginService: LoginService, private customer: CustomerService, private router: Router) {
  }

  newUser(){
    this.loginService.createUser().subscribe(res =>{

    });
  }

  tryLogin() {
    this.loginService.verifyLogin(this.login).subscribe(
        r => {
          if (r) {
            this.customer.setToken(''+r);
            this.router.navigate(['frota-inteligente']);
          }
        }, r => {
          console.log("ERRO CARAI");
          alert(r.error.error);
        });
  }

}
