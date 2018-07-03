import {NgModule} from "@angular/core";
import {Routes, RouterModule} from "@angular/router";
import { LoginComponent } from './login/login.component';
import {HomeComponent} from "./home/home.component";
import {VehiclesComponent} from "./vehicles/vehicles.component";
import {VehiclesCadastrarComponent} from "./vehicles/vehicles-cadastrar.component";

const appRoutes: Routes = [
  {path:'', component: LoginComponent},
  {path:'home/:id', component: HomeComponent},
  {path:'carros/:id', component: VehiclesComponent},
  {path:'carros-c/:id/:type/:idv', component: VehiclesCadastrarComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  //Exporta as rotas que existem nesse modulo para que outros modulos possam usa-las
  exports: [RouterModule]
})

export class  AppRoutingModule{

}
