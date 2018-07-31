import {Component, OnInit, ViewChild} from '@angular/core';
import { AgmCoreModule } from '@agm/core';

@Component({
  selector: 'app-localization',
  templateUrl: './localization.component.html',
  styleUrls: ['./localization.component.css']
})
export class LocalizationComponent implements OnInit {

  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';
  lat: number = 51.678418;
  lng: number = 7.809007;
  constructor() { }

  ngOnInit() {

  }

  changeOpt(){
    this.open = !this.open;
  }
}
