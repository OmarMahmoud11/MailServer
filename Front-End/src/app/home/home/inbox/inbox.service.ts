import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login/login.component';
import { EmailI } from '../home.component';
@Injectable({
  providedIn: 'root'
})
export class InboxService {
  loginUsername = LoginComponent.globalUsername

  constructor(private http : HttpClient) { }

  getInbox(loginUsername:string, priority: string) : Observable<EmailI[]>{

    return this.http.get<EmailI[]>("http://localhost:8080/getInbox?username=" + loginUsername + "&priority=" + priority);
  }
  movetoTrash(email:EmailI) : Observable<EmailI[]>{

    return this.http.post<EmailI[]>("http://localhost:8080/movetoTrashInbox", email);
  }

  moveToFolder(loginUsername: string, folderName: string, email:EmailI) : Observable<string>{
    return this.http.post("http://localhost:8080/moveToFolder?username=" + loginUsername + "&foldername=" + folderName, email, {responseType : "text"})
  }

  
  
}
