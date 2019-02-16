import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CustomerService} from "../../CustomerService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  @Output() public sidenavToggle = new EventEmitter();

  constructor(private customer: CustomerService, private router: Router) { }

  ngOnInit() {
  }

  public onToggleSidenav = () => {
    this.sidenavToggle.emit();
  }

  logout(){
      this.customer.setToken(null);
      this.router.navigate(['']);
  }
}
