import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http : HttpClient) { }

  createAccount(accountDetails:string) : Observable<String>{

    return this.http.post("http://localhost:8080/createProfile" , accountDetails, {responseType : "text"});
  }
  loginToAccount(accountDetails:string) : Observable<string>{

    return this.http.get("http://localhost:8080/getProfile?encryption=" + accountDetails, {responseType : "text"});
  }
}
