import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs/Observable";
import {User} from "./home.model";
import {ConstantesUtil} from "../util/constantes.util";

@Injectable()
export class HomeService {

  private userUrl = 'http://'+ConstantesUtil.IP_ADDRESS+':8888/user/get-user-by-id';

  constructor(private http: HttpClient){}

  getUser(id: number):Observable<User>{
    return this.http.get<User>(this.userUrl+'/'+id);
  }
}
