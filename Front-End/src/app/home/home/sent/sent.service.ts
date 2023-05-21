import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login/login.component';
import { EmailI } from '../home.component';
@Injectable({
  providedIn: 'root'
})
export class SentService {
  loginUsername = LoginComponent.globalUsername

  constructor(private http : HttpClient) { }

  getSent(loginUsername:string) : Observable<EmailI[]>{

    return this.http.get<EmailI[]>("http://localhost:8080/getSent?username=" + loginUsername + "&priority=false");
  }
  movetoTrash(email:EmailI) : Observable<EmailI[]>{

    return this.http.post<EmailI[]>("http://localhost:8080/movetoTrashSent", email);
  }


  
  
}