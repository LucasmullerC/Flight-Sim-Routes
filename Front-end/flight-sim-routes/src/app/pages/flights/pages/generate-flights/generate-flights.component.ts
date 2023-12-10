import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FlightsService } from 'src/app/flights.service';
import { FormFlightsModel } from 'src/app/form-flights-model';


@Component({
  selector: 'app-generate-flights',
  templateUrl: './generate-flights.component.html',
  styleUrls: ['./generate-flights.component.scss']
})
export class GenerateFlightsComponent {
  title: string = 'Flights';
  subtitle: string = '';

  selectedForm: FormFlightsModel[] = [];
  
  formRandomDb = [
    {
      title: 'Departure Airport (ICAO)',
      placeholder: 'KJFK', 
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName:'depAirport',
      required: false,
    },
    {
      title: 'Arrival Airport (ICAO)',
      placeholder: 'KSFO', 
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName:'arrAirport',
      required: false,
    },
    {
      title: 'Departure Country',
      placeholder: 'Select', 
      type: 'country',
      min: 0,
      max: 0,
      formControlName:'depCountry',
      required: false,
    },
    {
      title: 'Arrival Country',
      placeholder: 'Select', 
      type: 'country',
      min: 0,
      max: 0,
      formControlName:'arrCountry',
      required: false,
    },
    {
      title: 'Continous Flights',
      placeholder: 'continous', 
      type: 'number',
      min: 1,
      max: 10,
      formControlName:'quantity',
      required: false,
    },
    {
      title: 'Distance Range (NM)',
      placeholder: 'range', 
      type: 'distance',
      min: 1,
      max: 10000,
      formControlName:'minDistance',
      required: true,
    },
  ]

  formRealFlights = [
    {
      title: 'Database',
      placeholder: 'Select', 
      type: 'database',
      min: 0,
      max: 0,
      formControlName:'database',
      required: true,
    },
    {
      title: 'Departure Airport (ICAO)',
      placeholder: 'KJFK', 
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName:'depAirport',
      required: false,
    },
    {
      title: 'Arrival Airport (ICAO)',
      placeholder: 'KSFO', 
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName:'arrAirport',
      required: false,
    },
    {
      title: 'Continous Flights',
      placeholder: 'continous', 
      type: 'number',
      min: 1,
      max: 10,
      formControlName:'quantity',
      required: false,
    },
    {
      title: 'Distance Range (NM)',
      placeholder: 'range', 
      type: 'distance',
      min: 1,
      max: 10000,
      formControlName:'minDistance',
      required: true,
    },
  ]

  constructor(private router: Router, private flightService: FlightsService ) {}

  ngOnInit(): void {
    if(this.router.url == '/flights/randomdatabase'){
      this.subtitle = 'Random Database';
      this.selectedForm = this.formRandomDb;
    }
    else{
      this.subtitle = 'Real Flights Data';
      this.selectedForm = this.formRealFlights;
    }

    this.flightService.requestData$.subscribe((responseData) => {
      // Lógica para processar os dados do formulário e atualizar tableRows
      // ...

      // Exemplo: Adicione uma nova linha à tabela
      console.log(responseData);
    });
  }
  
}
