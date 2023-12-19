import { Component } from '@angular/core';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';

@Component({
  selector: 'app-airports-form',
  templateUrl: './airports-form.component.html',
  styleUrl: './airports-form.component.scss'
})
export class AirportsFormComponent {
  airlineName:string = '';
  columns: string[] = ['Airport ICAO'];
  rows: string[] = [];

  constructor(private scheduleForm: ScheduleFormService){}

  ngOnInit(): void {
    this.airlineName = this.scheduleForm.getAirlineName();
  }
}
