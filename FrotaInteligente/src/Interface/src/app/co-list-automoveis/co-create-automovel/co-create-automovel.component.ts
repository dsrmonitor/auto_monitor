import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-co-create-automovel',
  templateUrl: './co-create-automovel.component.html',
  styleUrls: ['./co-create-automovel.component.css']
})
export class CoCreateAutomovelComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  back(){
    this.router.navigate(['frota-inteligente/list-cars']);
  }
}
