import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ApiResponse } from '../model/response';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  constructor(private http: HttpClient) { }

  send(payload: any): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(environment.mailService, payload);
  }

  getMails(page: number, size: number): Observable<ApiResponse> {
    const url = `${environment.mailService}?page=${page}&size=${size}`;
    return this.http.get<ApiResponse>(url);
  }

}
