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
  }

  constructor(private loginService: LoginService, private customer: CustomerService, private router: Router) {
  }

  tryLogin() {
    this.loginService.verifyLogin(this.login).subscribe(
        r => {
          if (r) {
            this.customer.setToken(''+r);
            this.router.navigate(['frota-inteligente']);
          }
        }, r => {
          alert(r.error.error);
        });
  }

}
