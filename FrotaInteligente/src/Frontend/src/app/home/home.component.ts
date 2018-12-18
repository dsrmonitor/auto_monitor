import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'co-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  open: boolean = false;
  opened: string = 'row home-background-open';
  closed: string = 'row home-background-closed';

  constructor() { }

  ngOnInit() {
  }

  changeOpt(){
    console.log("TESTE")
    this.open = !this.open;
  }

}
