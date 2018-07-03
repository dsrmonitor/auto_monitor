import { Component, OnInit } from '@angular/core';
import {User} from "../home/home.model";
import {HomeService} from "../home/home.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'co-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  cssType: string ='';
  id: number;
  user: User = new User();

  constructor(private activateRoute: ActivatedRoute, private homeService: HomeService, private router: Router) {
    this.id = activateRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this.cssType = 'sidebar fixed-sidebar';
    this.homeService.getUser(this.id).subscribe(
      user =>{
        this.user = user;
      }
    );
  }

  goHome(){
    this.router.navigate(['home/'+this.user.id_user]);
  }

  goCarros(){
    this.router.navigate(['carros/'+this.user.id_user]);
  }
}
