import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-general-form',
  templateUrl: './general-form.component.html',
  styleUrl: './general-form.component.scss'
})
export class GeneralFormComponent {
  airlineName:string = '';
  density: String[] = ['High Density',"Medium Density","Low Density","Sparse Density"];
  data!: VirtualAirline;
  
  constructor(private scheduleForm: ScheduleFormService,
    private formBuilder: FormBuilder){}

    generalForm: FormGroup = this.formBuilder.group({
      flight_number:['', Validators.required],
      routeDensity:['', Validators.required],
      international:[false],
      isRepetitive:[false]
    });
  
  ngOnInit(): void {
    this.airlineName = this.scheduleForm.getAirlineName();
    const dataStorage = this.scheduleForm.getFormDataList();
    const existingDataIndex = dataStorage.findIndex(item => item.airlineName === this.airlineName);
    this.data = dataStorage[existingDataIndex];
  }

  onSubmit():void{
    const routeDensity = this.getRouteDensity();
    const international = this.formatInternational();
    const generalData = { ...this.data, 
      flight_number: this.generalForm.value.flight_number,
      route_density: routeDensity,
      isRepetitive: this.generalForm.value.isRepetitive,
      intenational:international };
    console.log(generalData);
    this.scheduleForm.setFormData(generalData);
  }

  private formatInternational():number{
    if(this.generalForm.value.international == true){
      return 2;
    }
    else{
      return 3;
    }
  }

  private getRouteDensity():number{
    switch(this.generalForm.value.routeDensity){
      case 'High Density':
        return 4;
      case 'Medium Density':
        return 3;
      case 'Low Density':
        return 2;
      default:
        return 1;
    }
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.generalForm.setControl(controlName, new FormControl(formControl));
  }

}
