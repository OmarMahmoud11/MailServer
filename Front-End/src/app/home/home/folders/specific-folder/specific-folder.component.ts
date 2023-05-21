import { Component, OnInit } from '@angular/core';
import { EmailI, HomeComponent } from '../../home.component';
import { FoldersComponent } from '../folders.component';
import { Router } from '@angular/router';
import { SpecificFolderService } from './specific-folder.service';
import { LoginComponent } from 'src/app/login/login/login.component';
import $ from "jquery"

@Component({
  selector: 'app-specific-folder',
  templateUrl: './specific-folder.component.html',
  styleUrls: ['./specific-folder.component.css']
})
export class SpecificFolderComponent implements OnInit {

  public static listOfEmails : EmailI[]
  private viewArray : string[][] = [];
  private listPreSize : number
  private iterationsNum : number
  private listOfButtons : NodeList
  public folderOpened : string

  constructor(private serveMe: SpecificFolderService, private router:Router) {
    HomeComponent.pageIndicator = "Specific Folder"
    this.folderOpened = FoldersComponent.folderOpened
    SpecificFolderComponent.listOfEmails = [];
    this.listPreSize = this.viewArray.length
    this.iterationsNum = 5;
    HomeComponent.pageIndicator = "Sent";

  }

  ngOnInit(): void {

    console.log("ng on Init called")
    this.serveMe.getFolder(LoginComponent.globalUsername, this.folderOpened).subscribe((data : EmailI[])=> {
      SpecificFolderComponent.listOfEmails = data;
      console.log(SpecificFolderComponent.listOfEmails);
      this.listPreSize = this.viewArray.length;
      this.parseArray();
      this.place(this.viewArray,this.iterationsNum,this.listPreSize);
      this.listOfButtons = document.querySelectorAll("#folder-mybody td  > button");
      console.log(this.listOfButtons)
      this.checkClick();
    });
  }

  parseArray(){
    console.log("In PARSE")
    console.log(SpecificFolderComponent.listOfEmails)
    this.viewArray = []

    for (let email=0; email < SpecificFolderComponent.listOfEmails.length;email++){
      console.log("In Loop")
      this.viewArray[email] = []
      this.viewArray[email][0] = SpecificFolderComponent.listOfEmails[email].senderUsername
      this.viewArray[email][1] = SpecificFolderComponent.listOfEmails[email].timeSentString
      this.viewArray[email][2] = SpecificFolderComponent.listOfEmails[email].subject
      this.viewArray[email][3] = SpecificFolderComponent.listOfEmails[email].priority
      console.log("View Array")
      console.log(this.viewArray)
    }
  }

  place(viewArray : string[][],iterationsNum : number,listPreSize: number,btnName: string = "Show"){
    var body = document.getElementById("folder-mybody")
    var buttonCount = 0
    for (let i=0;i<listPreSize;i++){
      body?.removeChild(body?.childNodes[0])
    }
    console.log(viewArray)
    for (let i=0;i<viewArray.length;i++){
      var node = document.createElement("tr");
      node.style.width = "300px"
      node.style.textAlign = "center"
      node.style.padding = "7px"
      node.style.margin = "50px"
      for (let j=0;j<iterationsNum;j++){
          var node2 = document.createElement("td");
          if (j!=iterationsNum-1){
            var textNode = document.createTextNode(viewArray[i][j]);
            node2.appendChild(textNode);
          }else{
            if (btnName!="Delete"){
              var node3 = document.createElement("button");
              node3.style.marginRight = "5px"
              var textNode = document.createTextNode(btnName);
              node3.appendChild(textNode);
              node3.type = "button";
              node3.id = (buttonCount).toString()
              node2.appendChild(node3);
              buttonCount++
            }
            var textNode2 = document.createTextNode("Delete");
            var node4 = document.createElement("button");
            node4.style.marginRight = "5px"
            node4.appendChild(textNode2);
            node4.type = "button";
            node4.id = (buttonCount).toString()
            node2.appendChild(node4);
            buttonCount++
          }
          node.appendChild(node2);
      }
      document.getElementById("folder-mybody")?.appendChild(node);
  }
}

checkClick(){
  for (var i =  0 ; i < this.listOfButtons.length ; i++){
    console.log("In button Loop")
    console.log(this.listOfButtons[i])

    if (i%2){
      this.listOfButtons[i].addEventListener("click",$.proxy(this.deleteClicked,this));
    }else{
      this.listOfButtons[i].addEventListener("click",$.proxy(this.showClicked,this));
    }

  }
}

closeEmailPopup(){
  (<HTMLElement>document.getElementById("email-popup")).style.display = "none";
}

deleteClicked(e: any){
  try{
    console.log("Delete Clicked")
    const buttonNum = parseInt(e.target.id)
    this.serveMe.movetoTrash(this.folderOpened, SpecificFolderComponent.listOfEmails[(buttonNum-1)/2]).subscribe((data : EmailI[])=> {
      this.router.navigateByUrl('/home',{skipLocationChange:true}).then(()=>{
        this.router.navigate(["/home/folders"])

      })
    })
  }catch (error){
    console.log(error)
  }
}

showClicked(e: any){
  try{
    console.log("In show clicked")
    console.log(SpecificFolderComponent.listOfEmails)
    const buttonNum = parseInt(e.target.id)
    console.log(SpecificFolderComponent.listOfEmails[0])
    this.show(SpecificFolderComponent.listOfEmails[buttonNum/2]);
  }catch (error){
    console.log(error)
  }
}

show(email:EmailI){
  var contentsToBeDeleted = document.querySelectorAll("div > p");
  for (var i = 0; i<contentsToBeDeleted.length; i++)
  switch(i){
    case 0 : document.getElementById("sender-container")?.removeChild(document.getElementById("sender-container")?.childNodes[1])
             break
    case 1 : document.getElementById("date-container")?.removeChild(document.getElementById("date-container")?.childNodes[1])
             break
    case 2 : document.getElementById("subject-container")?.removeChild(document.getElementById("subject-container")?.childNodes[1])
             break
    case 3 : document.getElementById("priority-container")?.removeChild(document.getElementById("priority-container")?.childNodes[1])
             break
    case 4 : document.getElementById("message-container")?.removeChild(document.getElementById("message-container")?.childNodes[1])
             break
    case 5 : document.getElementById("attachment-container")?.removeChild(document.getElementById("attachment-container")?.childNodes[1])
             break
  }
  var emailContents = document.querySelectorAll("div.email-container > div");
  for (var i = 0; i<emailContents.length; i++){
    var node = document.createElement("P")
    node.style.marginTop  = "0"
    var textNode = document.createTextNode(email.senderUsername)
    var destinationNode = document.getElementById("sender-container")
    switch(i){
      case 0 : node.id = "sender"
              break
      case 1 : node.id = "date"
              textNode = document.createTextNode(email.timeSentString)
              destinationNode = document.getElementById("date-container")
              break
      case 2 : node.id = "subject"
              textNode = document.createTextNode(email.subject)
              destinationNode = document.getElementById("subject-container")
              break
      case 3 : node.id = "priority"
              textNode = document.createTextNode(email.priority)
              destinationNode = document.getElementById("priority-container")
              break
      case 4 :
              node.id = "message"
              textNode = document.createTextNode(email.body)
              destinationNode = document.getElementById("message-container")

              break
      case 5 :
              node.id = "attachment"
              destinationNode = document.getElementById("attachment-container")
              break
    }
    if(i != 5){
      node.appendChild(textNode)
    }
    else{
      for(let i = 0; i < email.attachments.length; i++){
        var a = document.createElement("a");
        a.href = "data:".concat(email.attachments[i].type).concat(";base64,").concat(email.attachments[i].encoded);
        a.download = email.attachments[i].name;
        var btn = document.createElement("button");
        btn.style.width = "150px";
        btn.style.height = "40px";
        btn.style.whiteSpace = "normal";
        btn.style.wordWrap = "break-word";
        btn.innerHTML = email.attachments[i].name;
        a.appendChild(btn);
        node.appendChild(a)
      }
    }
    destinationNode?.appendChild(node)

  }


  (<HTMLElement>document.getElementById("email-popup")).style.display = "block";
}

}
