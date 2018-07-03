import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppRoutingModule} from "./app.router";
import {HttpClientModule} from "@angular/common/http";
import {MatIconModule} from "@angular/material";
import {FormsModule} from "@angular/forms";
import { NgxMaskModule } from 'ngx-mask';
import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {LoginService} from "./login/login.service";
import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import {HomeService} from "./home/home.service";
import { VehiclesComponent } from './vehicles/vehicles.component';
import { VehiclesCadastrarComponent} from './vehicles/vehicles-cadastrar.component';
import { BlockUIModule } from 'ng-block-ui';
import {VehiclesService} from "./vehicles/vehicles.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    MenuComponent,
    VehiclesComponent,
    VehiclesCadastrarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatIconModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    BlockUIModule.forRoot(),
    NgxMaskModule.forRoot()
  ],
  providers: [LoginService,HomeService, VehiclesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
