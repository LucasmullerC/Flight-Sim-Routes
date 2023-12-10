import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FlightsService {
  private requestDataSubject = new BehaviorSubject<any>(null);
  requestData$ = this.requestDataSubject.asObservable();

  private randomRoute = 'localhost:8080/random-route';
  private openskyRoute = 'localhost:8080/opensky-route';

  constructor(private http: HttpClient) { }

  getRandomRoute(data: any){
    this.requestDataSubject.next(this.http.post(this.randomRoute, data));
  }
}
