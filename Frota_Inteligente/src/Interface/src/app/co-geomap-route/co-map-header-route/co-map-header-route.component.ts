import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'co-map-header--route',
  templateUrl: './co-map-header-route.component.html',
  styleUrls: ['./co-map-header-route.component.css']
})
export class CoMapHeaderRouteComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();
  dateIni: Date;
  dateFim: Date;
  constructor() { }

  ngOnInit() {
  }

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }

}
