import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
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
  demands: String[] = ['Extreme Demand',"Big Demand","Medium Demand","Low Demand"];

  constructor(private scheduleForm: ScheduleFormService,private formBuilder: FormBuilder){}

  airportsForm: FormGroup = this.formBuilder.group({
    icao:['', Validators.required],
    demands:['', Validators.required],
  });

  ngOnInit(): void {
    this.airlineName = this.scheduleForm.getAirlineName();
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.airportsForm.setControl(controlName, new FormControl(formControl));
  }
}
