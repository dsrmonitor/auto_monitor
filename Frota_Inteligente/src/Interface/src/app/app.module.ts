import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BlockUIModule } from 'ng-block-ui';
import {LoginService} from "./login/login.service";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule, MatToolbarModule, MatListModule} from "@angular/material";
import {MatIconModule} from '@angular/material/icon';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { HomeComponent } from './home/home.component';
import {MatSidenavModule} from '@angular/material/sidenav';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LayoutModule } from '@angular/cdk/layout';
import { HeaderComponent } from './navigation/header/header.component';
import { SidenavListComponent } from './navigation/sidenav-list/sidenav-list.component';
import {MatMenuModule} from '@angular/material/menu';
import {FormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";
import {ROUTES} from "./app.routes";
import {NeedAuthGuard} from "./NeedAuthGuard";
import { CoListAutomoveisComponent } from './co-list-automoveis/co-list-automoveis.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatCardModule} from '@angular/material/card';
import { CoCreateAutomovelComponent } from './co-list-automoveis/co-create-automovel/co-create-automovel.component';
import { NgxMaskModule } from 'ngx-mask';
import {CarService} from "./co-list-automoveis/co-list-automoveis.service";
import {ToastrModule} from "ngx-toastr";
import {CoGeomapComponent} from "./co-geomap/co-geomap.component";
import {CoMapHeaderComponent} from "./co-geomap/co-map-header/co-map-header.component";
import {AgmCoreModule} from "@agm/core";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    DashboardComponent,
    HeaderComponent,
    SidenavListComponent,
    CoListAutomoveisComponent,
    CoCreateAutomovelComponent,
    CoGeomapComponent,
    CoMapHeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCheckboxModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    ToastrModule.forRoot(),
    BlockUIModule.forRoot(),
    NgxMaskModule.forRoot(),
    RouterModule.forRoot(ROUTES),
    LayoutModule,
    MatToolbarModule,
    MatListModule,
    MatMenuModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatCardModule,
    AgmCoreModule.forRoot({
      apiKey:'AIzaSyDWbkfyG-0wuE8O0oPgIO4wWwbaUc9CFLk'
    })

  ],
  exports: [
    MatSidenavModule,
    MatToolbarModule
  ],
  providers: [LoginService, CarService, NeedAuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
