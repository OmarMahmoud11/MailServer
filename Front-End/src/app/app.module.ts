import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login/login.component';
import { HomeComponent } from './home/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { TrashComponent } from './home/home/trash/trash.component';
import { InboxComponent } from './home/home/inbox/inbox.component';
import { SendEmailComponent } from './home/home/send-email/send-email.component';
import { SentComponent } from './home/home/sent/sent.component';
import { DraftComponent } from './home/home/draft/draft.component';
import { ContactsComponent } from './home/home/contacts/contacts.component';
import { FoldersComponent } from './home/home/folders/folders.component';
import { SpecificFolderComponent } from './home/home/folders/specific-folder/specific-folder.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    SentComponent,
    TrashComponent,
    ContactsComponent,
    SendEmailComponent,
    InboxComponent,
    DraftComponent,
    FoldersComponent,
    SpecificFolderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    LoginComponent,
    HomeComponent,
    SentComponent,
    TrashComponent,
    ContactsComponent,
    SendEmailComponent,
    InboxComponent,
    DraftComponent,
    FoldersComponent,
    SpecificFolderComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
