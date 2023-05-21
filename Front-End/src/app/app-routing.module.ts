import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login/login.component';
import { HomeComponent } from './home/home/home.component';
import { TrashComponent } from './home/home/trash/trash.component';
import { InboxComponent } from './home/home/inbox/inbox.component';
import { SendEmailComponent } from './home/home/send-email/send-email.component';
import { SentComponent } from './home/home/sent/sent.component';
import { DraftComponent } from './home/home/draft/draft.component';
import { ContactsComponent } from './home/home/contacts/contacts.component';
import { FoldersComponent } from './home/home/folders/folders.component';
import { SpecificFolderComponent } from './home/home/folders/specific-folder/specific-folder.component';


const routes: Routes = [
  {path:'',component: LoginComponent},
  {path:'home',component:HomeComponent,children:[
    {path:'trash',component:TrashComponent},
    {path:'inbox',component:InboxComponent},
    {path:'sendEmail',component:SendEmailComponent},
    {path:'sent',component:SentComponent},
    {path:'draft',component:DraftComponent},
  {path:'contacts',component:ContactsComponent},
    {path: 'folders',component:FoldersComponent,children:[
      {path: 'specificFolder',component:SpecificFolderComponent}
    ]}
  ]},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
