import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { MailService } from '../mail/mail.service';
import { MailDetails } from '../model/mail';
import { PageData } from '../model/response';

// const ELEMENT_DATA: MailDetails[] = [
//   {id: 1, fromName: 'Musk', fromEmail: 'musk@mail.com', toName: 'Eric', toEmail: 'eric@mail.com', subject: 'Subject', message: 'Message', status: 'SUCCESS'},
// ];

@Component({
  selector: 'app-mails',
  templateUrl: './mails.component.html',
  styleUrls: ['./mails.component.css']
})
export class MailsComponent implements OnInit, AfterViewInit {

  displayedColumns: string[] = ['id', 'sender', 'receiver', 'subject', 'message', 'status', 'provider'];
  totalElements = 0;
  pageSize = 10;
  dataSource: MatTableDataSource<MailDetails>;
  mails: MailDetails[] = [];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private mailService: MailService) {}

  ngOnInit(): void {
    this.refreshData();
  }
  
  ngAfterViewInit() {
    if (this.dataSource) {
      this.dataSource.paginator = this.paginator;
    }
  }

  refreshData(pageIndex = 0) {
    this.mailService.getMails(pageIndex, this.pageSize).subscribe(response => {
      let pageData = response.data as PageData;
      this.totalElements = pageData.totalElements
      this.mails = pageData.data as MailDetails[];
      this.dataSource = new MatTableDataSource<MailDetails>(this.mails);
    })
  }

  page(event: PageEvent) {
    this.refreshData(event.pageIndex);
  }


}
