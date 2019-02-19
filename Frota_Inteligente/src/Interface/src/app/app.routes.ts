import {Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./login/login.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {NeedAuthGuard} from "./NeedAuthGuard";
import {CoListAutomoveisComponent} from "./co-list-automoveis/co-list-automoveis.component";
import {CoCreateAutomovelComponent} from "./co-list-automoveis/co-create-automovel/co-create-automovel.component";
import {CoGeomapComponent} from "./co-geomap/co-geomap.component";
import {CoGeomapRouteComponent} from "./co-geomap-route/co-geomap-route.component";

export const ROUTES: Routes = [
  { path: '', component: LoginComponent},
  { path: 'frota-inteligente', component: DashboardComponent, canActivate: [NeedAuthGuard],children:[
      { path: '', component: HomeComponent, canActivate:[NeedAuthGuard]},
      { path: 'list-cars', component: CoListAutomoveisComponent, canActivate: [NeedAuthGuard]},
      { path: 'geo-cars', component: CoGeomapComponent, canActivate: [NeedAuthGuard]},
      { path: 'geo-cars-route', component: CoGeomapRouteComponent, canActivate: [NeedAuthGuard]},
      { path: 'new-cars', component: CoCreateAutomovelComponent, canActivate: [NeedAuthGuard]},
      { path: 'edit-cars/:id', component: CoCreateAutomovelComponent, canActivate: [NeedAuthGuard]},
    ]},
  { path: '**', redirectTo: '' }
];
