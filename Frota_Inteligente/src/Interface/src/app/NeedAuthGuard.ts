
import {CanActivate, Router} from '@angular/router';
import {Injectable} from '@angular/core';

import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router/src/router_state';
import {CustomerService} from "./CustomerService";

@Injectable()
export class NeedAuthGuard implements CanActivate {

  constructor(private customerService: CustomerService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

    if (this.customerService.isLogged()) {
      return true;
    }
    return false;
  }
}
