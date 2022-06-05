import { Component, NgZone, ViewChild } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MailService } from './mail/mail.service';
import { Mail } from './model/mail';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
}
