import { HomeComponent } from './../../home/home/home.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from './login.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router,private server: LoginService, private home :HomeComponent) { }

  public static globalUsername: string

  ngOnInit(): void {
  }
  create(){
    var username :string = (<HTMLInputElement> document.getElementById("username")).value;
    var password :string = (<HTMLInputElement> document.getElementById("password")).value;
    var container :String;
    this.server.createAccount(username.concat("$").concat(password)).subscribe((response:String)=>{
      container = response;
      console.log(container)
      if(container == "CREATED PROFILE SUCCESSFULLY"){
        LoginComponent.globalUsername = username
        console.log(LoginComponent.globalUsername)
        this.router.navigate(['/home']);
      }else {
        alert("This username has already used")
      }
    });
  }

  login(){
    var username :string = (<HTMLInputElement> document.getElementById("username")).value;
    var password :string = (<HTMLInputElement> document.getElementById("password")).value;
    var container :string;

    this.server.loginToAccount(username.concat("$").concat(password)).subscribe((response:string)=>{
      container = response;
      console.log(container)
      if(container == ""){
        alert("There is no account by such credintials");
      }
      else{
        this.home.path=container;
        LoginComponent.globalUsername = username
        console.log(LoginComponent)
        this.router.navigate(['/home']);
      }


    });

  }
}
