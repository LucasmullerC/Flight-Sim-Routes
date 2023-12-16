import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { CountryService } from 'src/app/services/country.service';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-subfleets-form',
  templateUrl: './subfleets-form.component.html',
  styleUrl: './subfleets-form.component.scss'
})
export class SubfleetsFormComponent {
  dataList:VirtualAirline[]=[];
  countries: any[] = [];
  airlineName:string = '';

  constructor(private countryService: CountryService,
    private scheduleForm: ScheduleFormService,
    private formBuilder: FormBuilder) {}

  subfleetsForm: FormGroup = this.formBuilder.group({
  });

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
    this.airlineName = this.scheduleForm.getAirlineName();
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.subfleetsForm.setControl(controlName, new FormControl(formControl));
  }
}
