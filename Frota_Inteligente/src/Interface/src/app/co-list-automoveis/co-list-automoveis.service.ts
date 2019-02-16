import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Car, CarInterface} from "./co-list-automoveis.model";


@Injectable()
export class CarService {

  private resourceUrl: string = environment.apiUrl;

  constructor(private http: HttpClient){}

  save(car: Car):Observable<Car>{
    return this.http.post<Car>(`${this.resourceUrl}/cars`,car);
  }

  update(car: Car):Observable<Car>{
    return this.http.put<Car>(`${this.resourceUrl}/cars`,car);
  }

  getCars():Observable<CarInterface[]>{
    return this.http.get<CarInterface[]>(`${this.resourceUrl}/cars`);
  }

  getAllCars():Observable<Car[]>{
    return this.http.get<Car[]>(`${this.resourceUrl}/cars`);
  }

  deleteCar(ids: number[]){
    return this.http.post(`${this.resourceUrl}/cars/deleteMulti`,ids);
  }

  getCar(id: number):Observable<Car>{
    return this.http.get<Car>(`${this.resourceUrl}/cars/${id}`);
  }

  getUpdatedPosition(ids:number[]):Observable<Car[]>{
    return this.http.post<Car[]>(`${this.resourceUrl}/cars/updated-positions`,ids);
  }
}
