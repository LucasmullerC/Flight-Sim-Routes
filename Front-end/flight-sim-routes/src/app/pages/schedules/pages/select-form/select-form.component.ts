import { Component} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CountryService } from 'src/app/services/country.service';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-select-form',
  templateUrl: './select-form.component.html',
  styleUrl: './select-form.component.scss'
})
export class SelectFormComponent {
  countries: any[] = [];
  data!: VirtualAirline;
  dataList:VirtualAirline[]=[];
  constructor(private countryService: CountryService,
    private scheduleForm: ScheduleFormService, 
    private formBuilder: FormBuilder,) {}
  
    schedulesForm: FormGroup = this.formBuilder.group({
      airlineName: '',
      airline:'',
      country: ['', Validators.required],
      hubs: '',
    });

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
    this.dataList=this.scheduleForm.getFormDataList();
  }

  onSubmitSelectVirtual(): void {
    const dataStorage = this.scheduleForm.getFormDataList();
    const existingDataIndex = dataStorage.findIndex(item => item.airlineName === this.schedulesForm.value.airlineName);
    this.data = dataStorage[existingDataIndex];
    this.scheduleForm.setAirlineName(this.data.airlineName);
    this.scheduleForm.setFormData(this.data);
  }

  onSubmitCreateNew(): void {
    const newAirlineData = {
      airlineName:this.schedulesForm.value.airlineName,
      airline: this.schedulesForm.value.airline,
      baseCountry: this.schedulesForm.value.country,
      hubs: this.getHubs(),
    }
    this.scheduleForm.setFormData(newAirlineData);
    this.scheduleForm.setAirlineName(this.schedulesForm.value.airlineName);
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.schedulesForm.setControl(controlName, new FormControl(formControl));
  }

  getHubs():string[]{
    return this.schedulesForm.value.hubs.split(",");
  }
}
