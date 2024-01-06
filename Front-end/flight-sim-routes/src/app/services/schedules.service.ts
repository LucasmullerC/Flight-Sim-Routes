import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SchedulesService {
  private baseUrl = 'https://flightsimroutes.onrender.com';

  constructor(private http: HttpClient) { }

  generateSchedules(requestData: any): Observable<Blob> {
    const url = `${this.baseUrl}/generate-schedules`;

    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });

    return this.http.post(url, requestData, { headers,withCredentials: true, responseType: 'blob' });
  }

  downloadFile(blob: Blob, filename: string): void {
    const downloadLink = document.createElement('a');
    const url = window.URL.createObjectURL(blob);
  
    downloadLink.href = url;
    downloadLink.download = filename;
  
    document.body.appendChild(downloadLink);
  
    downloadLink.click();
  
    document.body.removeChild(downloadLink);
  
    window.URL.revokeObjectURL(url);
  }
  
}
