import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login/login.component';
import { EmailI } from '../../home.component';
@Injectable({
  providedIn: 'root'
})
export class SpecificFolderService {
  loginUsername = LoginComponent.globalUsername

  constructor(private http : HttpClient) { }

  getFolder(loginUsername:string, folderName: string) : Observable<EmailI[]>{

    return this.http.get<EmailI[]>("http://localhost:8080/getFolder?username=" + loginUsername + "&foldername=" + folderName);
  }
  
  movetoTrash(folderName:string, email:EmailI) : Observable<EmailI[]>{

    return this.http.post<EmailI[]>("http://localhost:8080/moveToTrashFolder?foldername=" + folderName, email);
  }


  
  
}