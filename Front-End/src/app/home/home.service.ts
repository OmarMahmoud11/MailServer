import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/login/login/login.component';
import { EmailI, HomeComponent } from './home/home.component';
import { ContactI } from './home/contacts/contacts.component';


@Injectable({
    providedIn: 'root'
  })
  export class HomeService {
    loginUsername = LoginComponent.globalUsername
    filterOption = HomeComponent.globalFilterOption
    filterText = HomeComponent.globalFilterText
    sortOption = HomeComponent.globalSortOption
    sortOrder = HomeComponent.globalSortOrder
    searchText = HomeComponent.globalSearchText
    pageIndicator = HomeComponent.pageIndicator
  
    constructor(private http : HttpClient) { }


    filterTrash(loginUsername:string,filterOption:string, filterText:string) : Observable<EmailI[]>{

      return this.http.get<EmailI[]>("http://localhost:8080/filterTrash?username=" + loginUsername + "&feature=" + filterOption + "&target=" + filterText);
    }
    sortTrash(loginUsername:string,sortOption: string, sortOrder: string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/sortTrash?username=" + loginUsername + "&target=" + sortOption + "&ascending=" + sortOrder);
      }
  
    searchTrash(loginUsername:string, searchText:string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/searchTrash?username=" + loginUsername + "&target=" + searchText);
      }

    filterInbox(loginUsername:string,filterOption:string, filterText:string) : Observable<EmailI[]>{

      return this.http.get<EmailI[]>("http://localhost:8080/filterInbox?username=" + loginUsername + "&feature=" + filterOption + "&target=" + filterText);
    }
    sortInbox(loginUsername:string,sortOption: string, sortOrder: string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/sortInbox?username=" + loginUsername + "&target=" + sortOption + "&ascending=" + sortOrder);
      }
  
    searchInbox(loginUsername:string, searchText:string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/searchInbox?username=" + loginUsername + "&target=" + searchText);
      }

    filterDraft(loginUsername:string,filterOption:string, filterText:string) : Observable<EmailI[]>{

      return this.http.get<EmailI[]>("http://localhost:8080/filterDraft?username=" + loginUsername + "&feature=" + filterOption + "&target=" + filterText);
    }
    sortDraft(loginUsername:string,sortOption: string, sortOrder: string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/sortDraft?username=" + loginUsername + "&target=" + sortOption + "&ascending=" + sortOrder);
      }
  
    searchDraft(loginUsername:string, searchText:string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/searchDraft?username=" + loginUsername + "&target=" + searchText);
      }

    filterSent(loginUsername:string,filterOption:string, filterText:string) : Observable<EmailI[]>{

      return this.http.get<EmailI[]>("http://localhost:8080/filterSent?username=" + loginUsername + "&feature=" + filterOption + "&target=" + filterText);
    }
    sortSent(loginUsername:string,sortOption: string, sortOrder: string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/sortSent?username=" + loginUsername + "&target=" + sortOption + "&ascending=" + sortOrder);
      }
  
    searchSent(loginUsername:string, searchText:string) : Observable<EmailI[]>{
  
        return this.http.get<EmailI[]>("http://localhost:8080/searchSent?username=" + loginUsername + "&target=" + searchText);
      }

    
    sortContacts(loginUsername:string,sortOption: string, sortOrder: string) : Observable<ContactI[]>{
  
        return this.http.get<ContactI[]>("http://localhost:8080/sortContacts?username=" + loginUsername + "&target=" + sortOption + "&ascending=" + sortOrder);
      }
  
    searchContacts(loginUsername:string, searchText:string) : Observable<ContactI[]>{
  
        return this.http.get<ContactI[]>("http://localhost:8080/searchContacts?username=" + loginUsername + "&target=" + searchText);
      }

    sortFolder(loginUsername: string, folderName: string, sortOption: string, sortOrder: string) : Observable<EmailI[]>{
      return this.http.get<EmailI[]>("http://localhost:8080/sortFolder?username=" + loginUsername + "&foldername=" + folderName + "&target=" + sortOption + "&ascending=" + sortOrder);
    }
  }