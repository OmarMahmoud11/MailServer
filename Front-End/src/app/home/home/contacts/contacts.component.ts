import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginComponent } from 'src/app/login/login/login.component';
import { EmailI, HomeComponent } from '../home.component';
import { InboxComponent } from '../inbox/inbox.component';
import { ContactService } from './contacts.service';
import $ from "jquery"
import { SendEmailComponent } from '../send-email/send-email.component';
import { SelectorMatcher } from '@angular/compiler';


export interface ContactI{
  username: string
  emailAddresses: string[]
}
export class Contact implements ContactI{
  username:string;
  emailAddresses: string[]

  constructor(username:string,emailAddresses:string[]){
    this.username = username;
    this.emailAddresses = emailAddresses;
  }
}
@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.css']
})
export class ContactsComponent implements OnInit {
  public static listOfContacts : ContactI[]
  private viewArray : string[][]
  private listPreSize : number
  private iterationsNum : number
  private listOfButtons : NodeList
  private listOfClickableTDs : NodeList
  private id: number;
  private trsCreatedTillNow : number

  constructor(private placer: InboxComponent, private router : Router, private serveMe1: ContactService) {
    SendEmailComponent.emailToBeSent=null;
    ContactsComponent.listOfContacts = []
    this.viewArray = []
    this.listPreSize = this.viewArray.length
    this.iterationsNum = 3
    this.trsCreatedTillNow = 0
    HomeComponent.pageIndicator = "Contacts"

  }



  ngOnInit(): void {
  //  var x : ContactI = {
  //    username: "Joe",
  //    additionalEmails: ["youssef.okab@gmail.com"]
  //  }
  //  var y : ContactI = {
  //   username: "Mn3m",
  //   additionalEmails: ["mono.ghost@gmail.com"]
  // }
  // var z : ContactI = {
  //   username: "Deffo",
  //   additionalEmails: ["deffo.he5o@gmail.com"]
  // }
  // var w : ContactI = {
  //   username: "otb",
  //   additionalEmails: ["otb@gmal.com"]
  // }

  //   ContactsComponent.listOfContacts.push(x)
  //   ContactsComponent.listOfContacts.push(y)
  //   ContactsComponent.listOfContacts.push(z)
  //   ContactsComponent.listOfContacts.push(w)
  console.log("ng on Init called")
  this.serveMe1.getContacts(LoginComponent.globalUsername).subscribe((data : ContactI[])=> {
    console.log("lol")
    ContactsComponent.listOfContacts = data;
    console.log(ContactsComponent.listOfContacts);
    this.listPreSize = this.viewArray.length
    this.parseArray()
    this.placer.place(this.viewArray,this.iterationsNum,this.listPreSize,"Send Email")
    this.listOfButtons = document.querySelectorAll("td  > button")
    this.listOfClickableTDs = document.querySelectorAll("td.addEmail")
    console.log(this.listOfClickableTDs)
    this.checkClick()
    this.checkAddEmail()

    });


}
parseArray(){
  for (let contact=0; contact < ContactsComponent.listOfContacts.length;contact++){
    this.viewArray[contact] = []
    this.viewArray[contact][0] = ContactsComponent.listOfContacts[contact].username
    this.viewArray[contact][1] = ContactsComponent.listOfContacts[contact].emailAddresses[0]
  }
}

addContact(){

  var username_input = (<HTMLInputElement>document.getElementById("username")).value
  var email_input = (<HTMLInputElement>document.getElementById("email")).value
  console.log(username_input)
  console.log(email_input)
  var contact : ContactI = new Contact(username_input,[email_input])
  console.log(contact)

  this.serveMe1.addContact(LoginComponent.globalUsername,contact).subscribe((data : string)=> {
    this.router.navigateByUrl('/home',{skipLocationChange:true}).then(()=>{
      this.router.navigate(['/home/contacts'])
    })
  });

}

checkClick(){
  for (var i =  0 ; i < this.listOfButtons.length ; i++){

    if (i%2){
      this.listOfButtons[i].addEventListener("click",$.proxy(this.deleteClicked,this));
    }else{
      this.listOfButtons[i].addEventListener("click",$.proxy(this.sendEmailClicked,this));
    }

  }
}
sortContacts(input : ContactI[]){
  this.listPreSize = ContactsComponent.listOfContacts.length;
  ContactsComponent.listOfContacts = input
  this.parseArray()
  this.placer.place(this.viewArray,this.iterationsNum,this.listPreSize,"Send Email")
  this.listOfButtons = document.querySelectorAll("td  > button")
  this.listOfClickableTDs = document.querySelectorAll("td.addEmail")
  console.log(this.listOfClickableTDs)
  this.checkClick()
  this.checkAddEmail()
}

filterContacts(input : ContactI[]){
  console.log(input.length)
  this.listPreSize = ContactsComponent.listOfContacts.length;
  console.log("INPUT LENGTH ", input.length)
  ContactsComponent.listOfContacts = input
  console.log("COMP LENGTH ", ContactsComponent.listOfContacts.length);

  this.parseArray();
  console.log(this.viewArray.length);
  if(input.length == 0){
    this.viewArray = [];
  }
  this.parseArray();
  this.placer.place(this.viewArray,this.iterationsNum,this.listPreSize,"Send Email")
  this.listOfButtons = document.querySelectorAll("td  > button")
  this.listOfClickableTDs = document.querySelectorAll("td.addEmail")
  console.log(this.listOfClickableTDs)
  this.checkClick()
  this.checkAddEmail()
}
searchContacts(input : ContactI[]){
  console.log(input.length)
  this.listPreSize = ContactsComponent.listOfContacts.length;
  console.log("INPUT LENGTH ", input.length)
  ContactsComponent.listOfContacts = input
  console.log("COMP LENGTH ", ContactsComponent.listOfContacts.length);

  this.parseArray();
  console.log(this.viewArray.length);
  if(input.length == 0){
    this.viewArray = [];
  }

  this.parseArray();
  this.placer.place(this.viewArray,this.iterationsNum,this.listPreSize,"Send Email")
  this.listOfButtons = document.querySelectorAll("td  > button")
  this.listOfClickableTDs = document.querySelectorAll("td.addEmail")
  console.log(this.listOfClickableTDs)
  this.checkClick()
  this.checkAddEmail()
}



    deleteClicked(e: any){
      try{
        const buttonNum = parseInt(e.target.id)
        this.serveMe1.removeContact(LoginComponent.globalUsername,ContactsComponent.listOfContacts[(buttonNum-1)/2].username).subscribe((data : string)=> {
          this.router.navigateByUrl('/home',{skipLocationChange:true}).then(()=>{
            this.router.navigate(["/home/contacts"])

          })
        });
      }catch (error){
        console.log(error)
      }
    }
    sendEmailClicked(e: any){
      try{
        const buttonNum = parseInt(e.target.id)
        SendEmailComponent.emailToBeSent  = {
          senderUsername: '',
          timeSentString: '',
          subject: '',
          body: '',
          owner: '',
          receiversUsernames: [],
          emailID: '',
          emailType: '',
          priority: '',
          attachments: []
        }
        SendEmailComponent.emailToBeSent.receiversUsernames.push(ContactsComponent.listOfContacts[buttonNum/2].username)
        this.router.navigate(['/home/sendEmail'])

      }catch (error){
        console.log(error)
      }
    }
     
    checkAddEmail(){
      for (var i =  0 ; i < this.listOfClickableTDs.length ; i++){
        this.listOfClickableTDs[i].addEventListener("mouseenter",$.proxy(this.hoverMe,this));
        this.listOfClickableTDs[i].addEventListener("mouseleave",$.proxy(this.unhoverMe,this));
        this.listOfClickableTDs[i].addEventListener("click",$.proxy(this.TDclicked,this));

         }
  }
    hoverMe(e : any){
      e.target.style.backgroundColor = "blue";
    }

    unhoverMe(e : any){
      e.target.style.backgroundColor = "inherit";
    }

    TDclicked(e : any){
      this.id = parseInt(e.target.id)
      this.show(ContactsComponent.listOfContacts[this.id].emailAddresses)
    }

    closeEmailPopup(){
      (<HTMLElement>document.getElementById("email-popup")).style.display = "none";
    } 
      
    show(emailList : string[]){
      console.log(emailList)
      const emailsTable = document.getElementById("email-table")
      var savedTrs = this.trsCreatedTillNow
      for (var i=0;i<savedTrs;i++){
        this.trsCreatedTillNow--
        emailsTable?.removeChild(emailsTable.childNodes[0])
      }
      for (var i=0;i<emailList.length;i++){
        var row = document.createElement("tr");
        row.id = "tr" + i.toString()
        emailsTable?.appendChild(row)
        this.trsCreatedTillNow++
        $("#" + row.id).text(emailList[i]);
      }
      (<HTMLElement>document.getElementById("email-popup")).style.display = "block";
    }


  
  addNewEmail(){
    ContactsComponent.listOfContacts[this.id].emailAddresses.push((<HTMLInputElement>document.getElementById("emails")).value)
    this.serveMe1.removeContact(LoginComponent.globalUsername,ContactsComponent.listOfContacts[this.id].username).subscribe((data : string)=> {
      this.serveMe1.addContact(LoginComponent.globalUsername,ContactsComponent.listOfContacts[this.id]).subscribe((data : string)=> {
        this.router.navigateByUrl('/home',{skipLocationChange:true}).then(()=>{
          this.router.navigate(['/home/contacts'])
        })
      });
    });
    this.show(ContactsComponent.listOfContacts[this.id].emailAddresses)
  }
}
