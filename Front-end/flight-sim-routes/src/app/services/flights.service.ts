import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightsService {
  private baseUrl = 'https://flightsimroutes.onrender.com';
  private requestDataSubject = new BehaviorSubject<any>(null);
  requestData$ = this.requestDataSubject.asObservable();

  constructor(private http: HttpClient) { }

  getFlights(data: any,type:string){
    const url = `${this.baseUrl}/${type}`;
    this.http.post(url, data, { withCredentials: true }).subscribe({
      next: responseData => {
        this.requestDataSubject.next(responseData);
      },
      error: error => {
        console.error('An Error Occurred', error);
        this.requestDataSubject.next(error);
      }
    });
  }
}
