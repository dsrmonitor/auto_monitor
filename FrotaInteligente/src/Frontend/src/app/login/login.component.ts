import { Component, OnInit } from '@angular/core';
import {LoginService} from "./login.service";
import {Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {Login} from "./login.model";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'co-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private router: Router, private toastr: ToastrService) { }

  ngOnInit() {
  }

  onSingin(form: NgForm){
    let login: Login = new Login(form.value.code,form.value.password);
    this.loginService.verifyLogin(login).subscribe(
      id => {
        if(id !== null) {
          this.toastr.success("Login realizado","Sucesso");
          this.router.navigate(['home/' + id]);
        }else{
          this.toastr.error("Login incorreto","Erro");
        }
      }
    );
  }

}
