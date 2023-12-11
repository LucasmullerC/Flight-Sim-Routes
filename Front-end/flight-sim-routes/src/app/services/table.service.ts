import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TableService {

  constructor() { }

  buildColumns(data: any): string[] {
    const columns: string[] = [];

    let object = data[0];
    for (let key in object) {
      if (object[key] !== '' && object[key] !== '0'&& object[key] !== 'J') {
        columns.push(this.buildNameColumns(key));
      }
    }

    return columns;
  }

  buildRows(data:any): any[] {

    for (let object of data) {
      for (let key in object) {
        if (object[key] !== '' && object[key] !== '0'&& object[key] !== 'J') {
          object[key] = this.formatRows(key,object[key]);
        }
        else{
          delete object[key]
        }
      }
    }

    return data;
  }

  private formatRows(key:string,response:string): string{
    switch(key){
      case 'airline':{
        return 'https://raw.githubusercontent.com/sexym0nk3y/airline-logos/main/logos/'+response+'.png';
      }
      case 'subfleets':{
        return '<a href="'+response+'">Aircraft Database</a>'
      }
      default:{
        return response
      }
    }
  }

  private buildNameColumns(key:string): string{
    switch(key){
      case 'airline':{
        return 'Airline';
      }
      case 'flight_number':{
        return 'Flight Number';
      }
      case 'dpt_airport':{
        return 'Departure Airport'
      }
      case 'arr_airport':{
        return 'Arrival Airport'
      }
      case 'distance':{
        return 'Distance (NM)'
      }
      case 'subfleets':{
        return 'Aircraft'
      }
      default:{
        return ''
      }
    }
  }
}
