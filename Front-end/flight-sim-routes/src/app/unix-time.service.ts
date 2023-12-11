import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UnixTimeService {
  private date!: Date;
  constructor() { }

  getUnixYesterday(): string {
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    return Math.floor(yesterday.getTime() / 1000).toString();
  }

  getUnixYesterdayPlusTwoHours(): string {
    const yesterday = new Date();
    yesterday.setDate(yesterday.getDate() - 1);
    
    yesterday.setHours(yesterday.getHours() + 2);

    return Math.floor(yesterday.getTime() / 1000).toString();
  }
}
