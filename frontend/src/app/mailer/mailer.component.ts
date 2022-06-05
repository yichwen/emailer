import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MailService } from '../mail/mail.service';
import { Mail } from '../model/mail';

@Component({
  selector: 'app-mailer',
  templateUrl: './mailer.component.html',
  styleUrls: ['./mailer.component.css']
})
export class MailerComponent implements OnInit {

  mail: Mail = {
    fromName: '',
    fromEmail: '',
    toName: '',
    toEmail: '',
    subject: '',
    message: ''
  }

  loading = false;

  constructor(
    private mailService: MailService,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
  }

  send(): void {
    this.loading = true;
    this.mailService.send(this.mail).subscribe(response => {
      this.loading = false;
      if (response.code == 200) {
         this.openSnackBar("Mail sent successful.");
         this.mail = {
          fromName: '',
          fromEmail: '',
          toName: '',
          toEmail: '',
          subject: '',
          message: ''
        }
      } else {
        this.openSnackBar(response.message);
      }
    });
  }

  private openSnackBar(message: string): void {
    this.snackBar.open(message, undefined, {
      duration: 3000
    });
  }

}
