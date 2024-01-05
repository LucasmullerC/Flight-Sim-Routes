import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightsService } from 'src/app/services/flights.service';
import { FormFlightsModel } from 'src/app/form-flights-model';
import { UnixTimeService } from 'src/app/services/unix-time.service';
import { TableService } from 'src/app/services/table.service';

@Component({
  selector: 'app-generate-flights',
  templateUrl: './generate-flights.component.html',
  styleUrls: ['./generate-flights.component.scss'],
})
export class GenerateFlightsComponent {
  title: string = 'Flights';
  subtitle: string = '';
  flightsForm!: FormGroup;
  selectedForm: FormFlightsModel[] = [];
  columns: string[] = [];
  rows: any;
  error_table: boolean = false;

  constructor(private router: Router, 
    private flightService: FlightsService,
    private formBuilder: FormBuilder,
    private unixTime: UnixTimeService,
    private table: TableService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    if (this.router.url == '/flights/randomdatabase') {
      this.subtitle = 'Random Database';
      this.selectedForm = this.formRandomDb;
      this.flightsForm = this.randomDB
    } else {
      this.subtitle = 'Real Flights Data';
      this.selectedForm = this.formRealFlights;
      this.flightsForm = this.realFlightsDB;
    }
    
    this.flightService.requestData$.subscribe((formData) => {
      if(formData != undefined){
        if(formData.status == undefined){
          this.columns = this.table.buildColumns(formData);
          this.rows = this.table.buildRows(formData);
        }
        else{
          this.error_table = true;
        }
      }
    });

    this.route.url.subscribe(url => {
      this.error_table = false;
      this.rows = undefined;
    });
  }

  ngOnDestroy(): void {
    this.columns = [];
    this.rows = [];
  }

  randomDB = this.formBuilder.group({
    quantity: 1,
    depCountry: [''],
    arrCountry: [''],
    depAirport: ['',[
      Validators.maxLength(4),
      Validators.minLength(4)]
    ],
    arrAirport: ['',[
      Validators.maxLength(4),
      Validators.minLength(4)]
    ],
    minDistance: 1,
    maxDistance: 9999,
    continuous: false,
  });

  realFlightsDB = this.formBuilder.group({
    database:['',[Validators.required]],
    quantity: 1,
    depAirport: ['',[
      Validators.maxLength(4),
      Validators.minLength(4)]
    ],
    arrAirport: ['',[
      Validators.maxLength(4),
      Validators.minLength(4)]
    ],
    //beginTime:1698595826,
    //endTime:1698599426,
    beginTime:this.unixTime.getUnixYesterday(),
    endTime:this.unixTime.getUnixYesterdayPlusTwoHours(),
    minDistance: 1,
    maxDistance: 9999,
    continuous: false,
  });

  formRandomDb = [
    {
      title: 'Departure Airport (ICAO)',
      placeholder: 'KJFK',
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName: 'depAirport',
      required: false,
    },
    {
      title: 'Arrival Airport (ICAO)',
      placeholder: 'KSFO',
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName: 'arrAirport',
      required: false,
    },
    {
      title: 'Departure Country',
      placeholder: 'Select',
      type: 'country',
      min: 0,
      max: 0,
      formControlName: 'depCountry',
      required: false,
    },
    {
      title: 'Arrival Country',
      placeholder: 'Select',
      type: 'country',
      min: 0,
      max: 0,
      formControlName: 'arrCountry',
      required: false,
    },
    {
      title: 'Continous Flights',
      placeholder: 'continous',
      type: 'number',
      min: 1,
      max: 10,
      formControlName: 'quantity',
      required: false,
    },
    {
      title: 'Distance Range (NM)',
      placeholder: 'range',
      type: 'distance',
      min: 1,
      max: 10000,
      formControlName: 'minDistance',
      required: true,
    },
  ];

  formRealFlights = [
    {
      title: 'Database',
      placeholder: 'Select',
      type: 'database',
      min: 0,
      max: 0,
      formControlName: 'database',
      required: true,
    },
    {
      title: 'Departure Airport (ICAO)',
      placeholder: 'KJFK',
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName: 'depAirport',
      required: false,
    },
    {
      title: 'Arrival Airport (ICAO)',
      placeholder: 'KSFO',
      type: 'textarea',
      min: 0,
      max: 0,
      formControlName: 'arrAirport',
      required: false,
    },
    {
      title: 'Continous Flights',
      placeholder: 'continous',
      type: 'number',
      min: 1,
      max: 10,
      formControlName: 'quantity',
      required: false,
    },
    {
      title: 'Distance Range (NM)',
      placeholder: 'range',
      type: 'distance',
      min: 1,
      max: 10000,
      formControlName: 'minDistance',
      required: true,
    },
  ];
}
