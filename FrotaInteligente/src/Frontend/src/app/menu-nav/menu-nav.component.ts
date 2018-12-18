import {Component, OnInit} from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../home/home.model";
import {HomeService} from "../home/home.service";

@Component({
  selector: 'menu-nav',
  templateUrl: './menu-nav.component.html',
  styleUrls: ['./menu-nav.component.css'],
})
export class MenuNavComponent  implements OnInit{
  user: User = new User();
  id: number;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );



  constructor(private activateRoute: ActivatedRoute,private homeService: HomeService, private breakpointObserver: BreakpointObserver,private router: Router) {
    this.id = activateRoute.snapshot.params['id'];
  }
  goHome(){
    this.router.navigate(['home/'+this.user.id_user]);
  }
  ngOnInit(){
    this.homeService.getUser(this.id).subscribe(
      user =>{
        this.user = user;
      }
    );
  }
  goCarros(){
    this.router.navigate(['carros/'+this.user.id_user]);
  }

  goLocalization(){
    this.router.navigate(['localizacao']);
  }
}
