import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs/Observable";
import {User} from "./home.model";

@Injectable()
export class HomeService {

  private userUrl = 'http://192.168.1.110:8080/user/get-user-by-id';

  constructor(private http: HttpClient){}

  getUser(id: number):Observable<User>{
    return this.http.get<User>(this.userUrl+'/'+id);
  }
}
