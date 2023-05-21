import { Component, OnChanges, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InboxComponent } from '../inbox/inbox.component';
import { EmailI, HomeComponent } from '../home.component';
import { FolderService } from './folders.service';
import $ from "jquery"
import { LoginComponent } from 'src/app/login/login/login.component';
import { SendEmailComponent } from '../send-email/send-email.component';


export interface FoldersI{
  name: string
  listOfEmails: EmailI[]
}

@Component({
  selector: 'app-folders',
  templateUrl: './folders.component.html',
  styleUrls: ['./folders.component.css']
})
export class FoldersComponent implements OnInit{
  public static listOfFolders : FoldersI[]
  private viewArray : string[][]
  private listPreSize : number
  private iterationsNum : number
  private listOfButtons : NodeList
  public static folderOpened : string

  constructor(private router:Router,private serveMe: FolderService,private placer : InboxComponent ) {
    SendEmailComponent.emailToBeSent=null;
    FoldersComponent.listOfFolders = []
    this.viewArray = []
    this.listPreSize = this.viewArray.length
    this.iterationsNum = 2
    HomeComponent.pageIndicator = "Folders"
    FoldersComponent.folderOpened = ""

  }



  ngOnInit(): void {

  //   var x : FoldersI = {
  //     name: "Joe",
  //     listOfEmails: []
  //   }
  //   var y : FoldersI = {
  //     name: "Mn3m",
  //     listOfEmails: []
  //  }

    // FoldersComponent.listOfFolders.push(x)
    // FoldersComponent.listOfFolders.push(y)
    console.log(this.listOfButtons)

    this.serveMe?.getFolders(LoginComponent.globalUsername).subscribe((data : FoldersI[])=> {
    FoldersComponent.listOfFolders = data;
    console.log("List of Folders")
    console.log(FoldersComponent.listOfFolders);
    this.listPreSize = this.viewArray.length
    this.parseArray()
    this.placer.place(this.viewArray,this.iterationsNum,this.listPreSize,"Open")
    this.listOfButtons = document.querySelectorAll("td  > button")
    this.checkClick()
    });



  }


  parseArray(){
    for (let folder=0; folder < FoldersComponent.listOfFolders.length; folder++){
      this.viewArray[folder] = []
      this.viewArray[folder][0] = FoldersComponent.listOfFolders[folder].name
    }
  }

  addFolder(){
    var folder_input = (<HTMLInputElement>document.getElementById("folder")).value
    var folder : FoldersI = {
      name: folder_input,
      listOfEmails: []
    }
    this.serveMe.addUserFolder(LoginComponent.globalUsername,folder.name).subscribe((data : FoldersI[])=> {
      this.router.navigateByUrl('/home',{skipLocationChange:true}).then(()=>{
        this.router.navigate(["/home/folders"])

      })
    });

  }

  checkClick(){
    for (var i =  0 ; i < this.listOfButtons.length ; i++){

      if (i%2){
        this.listOfButtons[i].addEventListener("click",$.proxy(this.deleteClicked,this));
      }else{
        this.listOfButtons[i].addEventListener("click",$.proxy(this.openClicked,this));
      }

    }
}

deleteClicked(e: any){
  try{
    const buttonNum = parseInt(e.target.id)
      this.serveMe.removeUserFolder(LoginComponent.globalUsername,FoldersComponent.listOfFolders[(buttonNum-1)/2].name).subscribe((data : string)=> {
        console.log("Ana Da5alt")
        this.router.navigateByUrl('/home',{skipLocationChange:true}).then(()=>{
          this.router.navigate(["/home/folders"])

        })
    });
  }catch (error){
    console.log(error)
  }
}
  openClicked(e: any){
    try{
        console.log("Open is pressed")
        const buttonNum = parseInt(e.target.id)
        FoldersComponent.folderOpened = FoldersComponent.listOfFolders[(buttonNum)/2].name
        this.router.navigate(['home/folders/specificFolder']);
    }catch (error){
      console.log(error)
    }
  }
}

