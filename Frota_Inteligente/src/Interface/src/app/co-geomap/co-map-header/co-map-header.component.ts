import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'co-map-header',
  templateUrl: './co-map-header.component.html',
  styleUrls: ['./co-map-header.component.css']
})
export class CoMapHeaderComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }

}
