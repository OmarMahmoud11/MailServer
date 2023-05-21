import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login/login.component';
import { ContactI } from '../contacts/contacts.component';

@Injectable({
  providedIn: 'root'
})
export class ContactService {
  loginUsername = LoginComponent.globalUsername

  constructor(private http : HttpClient) { }

  getContacts(loginUsername:string) : Observable<ContactI[]>{

    return this.http.get<ContactI[]>("http://localhost:8080/getContacts?username=" + loginUsername);
  }

  addContact(loginUsername:string, contact: ContactI) : Observable<string>{
    return this.http.post("http://localhost:8080/addContact?username=" + loginUsername , contact,{responseType:"text"})
  }

  removeContact(loginUsername:string,contactName:string) : Observable<string>{

    return this.http.delete("http://localhost:8080/removeContact?username=" + loginUsername + "&contactusername=" + contactName,{responseType:"text"});
  }


}
