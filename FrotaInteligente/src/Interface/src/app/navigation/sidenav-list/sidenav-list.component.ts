import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-sidenav-list',
  templateUrl: './sidenav-list.component.html',
  styleUrls: ['./sidenav-list.component.css']
})
export class SidenavListComponent implements OnInit {

  @Output() sidenavClose = new EventEmitter();

  constructor(private router: Router) { }

  ngOnInit() {
  }

  public onSidenavClose = (page: number) => {

    if(page === 1){
      this.router.navigate(['frota-inteligente']);
    }else if(page === 3){
      this.router.navigate(['frota-inteligente/list-cars'])
    }

    this.sidenavClose.emit();
  }

}
