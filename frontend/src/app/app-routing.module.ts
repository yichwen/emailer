import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MailerComponent } from './mailer/mailer.component';
import { MailsComponent } from './mails/mails.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: 'mailer', component: MailerComponent },
  { path: 'mails', component: MailsComponent },
  { path: '', redirectTo: '/mailer', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
