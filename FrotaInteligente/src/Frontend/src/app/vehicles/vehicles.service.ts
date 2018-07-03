import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs/Observable";
import {Vehicle} from "./vehicles.model";

@Injectable()
export class VehiclesService{

  private VehiclesUrl = 'http://192.168.1.110:8080/vehicles';

  constructor(private http: HttpClient){}

  getAllVehicles():Observable<Vehicle[]>{
    return this.http.get<Vehicle[]>(this.VehiclesUrl+'/get-all-vehicles');
  }

  getVehicleById(id: number): Observable<Vehicle>{
    return this.http.get<Vehicle>(this.VehiclesUrl+'/get-vehicle-by-id/'+id);
  }

  searchVehicles(vehicles: string):Observable<Vehicle[]>{
    return this.http.get<Vehicle[]>(this.VehiclesUrl+'/search-vehicle/'+vehicles);
  }

  create(vehicle: Vehicle):Observable<Vehicle>{
    return this.http.post<Vehicle>(this.VehiclesUrl+'/save-vehicle',vehicle);
  }

  update(vehicle: Vehicle):Observable<Boolean>{
    return this.http.put<Boolean>(this.VehiclesUrl+'/update-vehicle',vehicle);
  }

  delete(id: number): Observable<any>{
    return this.http.delete(this.VehiclesUrl+'/delete-vehicle/'+id);
  }
}
