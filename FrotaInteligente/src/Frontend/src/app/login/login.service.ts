import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Login} from "./login.model";
import {Observable} from "rxjs/Observable";
import {ConstantesUtil} from "../util/constantes.util";

@Injectable()
export class LoginService {

  private LoginUrl = 'http://'+ConstantesUtil.IP_ADDRESS+':8888/user/get-user-id';

  constructor(private http: HttpClient){}

  verifyLogin(login: Login):Observable<number>{
    return this.http.get<number>(this.LoginUrl+'/'+login.user_code+'/'+login.user_password);
  }
}
