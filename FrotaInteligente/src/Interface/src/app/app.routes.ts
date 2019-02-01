import {Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {NeedAuthGuard} from "./NeedAuthGuard";
import {CoListAutomoveisComponent} from "./co-list-automoveis/co-list-automoveis.component";
import {CoCreateAutomovelComponent} from "./co-list-automoveis/co-create-automovel/co-create-automovel.component";

export const ROUTES: Routes = [
  { path: '', component: LoginComponent},
  { path: 'frota-inteligente', component: DashboardComponent, canActivate: [NeedAuthGuard],children:[
      { path: '', component: HomeComponent, canActivate:[NeedAuthGuard]},
      { path: 'list-cars', component: CoListAutomoveisComponent, canActivate: [NeedAuthGuard]},
      { path: 'detail-cars', component: CoCreateAutomovelComponent, canActivate: [NeedAuthGuard]},
    ]},
  { path: '**', redirectTo: '' }
];
