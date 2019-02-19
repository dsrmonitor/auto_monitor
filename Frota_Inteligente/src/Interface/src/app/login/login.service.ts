import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

import {Login} from "./login.model";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";


@Injectable()
export class LoginService {

  private resourceUrl: string = environment.apiUrl;

  constructor(private http: HttpClient){}

  verifyLogin(login: Login):Observable<number>{
    return this.http.get<number>(`${this.resourceUrl}/profiles/login/${login.user_code}/${login.user_password}`);
  }

  createUser():Observable<boolean>{
    return this.http.get<boolean>(`${this.resourceUrl}/profiles/new`);
  }
}
