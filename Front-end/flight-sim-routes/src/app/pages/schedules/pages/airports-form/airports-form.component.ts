import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-airports-form',
  templateUrl: './airports-form.component.html',
  styleUrl: './airports-form.component.scss'
})
export class AirportsFormComponent {
  airlineName:string = '';
  extremeDemand: string[] = [];
  bigDemand: string[] = [];
  mediumDemand: string[] = [];
  columns: string[] = ['Airport ICAO'];
  rows:any = 
    {
      extremeDemand:[],
      bigDemand:[],
      mediumDemand:[]
    };
  data!: VirtualAirline;
  demands: String[] = ['Extreme Demand',"Big Demand","Medium Demand"];

  constructor(private scheduleForm: ScheduleFormService,
    private formBuilder: FormBuilder){}

  airportsForm: FormGroup = this.formBuilder.group({
    icao:['', [Validators.maxLength(4),
    Validators.minLength(4),]],
    demands:[''],
  });

  public validationMessages = {
    'icao': [
      { type: 'maxlength', message: 'Maximum length allowed is 4 characters.' },
      { type: 'minlength', message: 'Minimum length required is 4 characters.' }
    ],
  }

  ngOnInit(): void {
    this.airlineName = this.scheduleForm.getAirlineName();
    const dataStorage = this.scheduleForm.getFormDataList();
    const existingDataIndex = dataStorage.findIndex(item => item.airlineName === this.airlineName);
    this.data = dataStorage[existingDataIndex];
    this.addToAirportsWithData(this.data?.extremeDemand,'Extreme Demand');
    this.addToAirportsWithData(this.data?.bigDemand,'Big Demand');
    this.addToAirportsWithData(this.data?.mediumDemand,'Medium Demand');
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.airportsForm.controls[controlName].setValue(formControl);
  }

  onRowChange(type: string, row: any){
    let rowIndex = 0;
    let demandindex = 0;
    switch(type){
      case 'Extreme Demand':
        rowIndex = this.rows.extremeDemand.indexOf(row, 0);
        demandindex = this.extremeDemand.indexOf(row.airport, 0);
        if (rowIndex > -1 && demandindex > -1) {
          this.rows.extremeDemand.splice(rowIndex, 1);
          this.extremeDemand.splice(demandindex, 1);
        }
        break;
      case 'Big Demand':
        rowIndex = this.rows.bigDemand.indexOf(row, 0);
        demandindex = this.bigDemand.indexOf(row.airport, 0);
        if (rowIndex > -1 && demandindex > -1) {
          this.rows.bigDemand.splice(rowIndex, 1);
          this.bigDemand.splice(demandindex, 1);
        }
        break;
      case 'Medium Demand':
        rowIndex = this.rows.mediumDemand.indexOf(row, 0);
        demandindex = this.mediumDemand.indexOf(row.airport, 0);
        if (rowIndex > -1 && demandindex > -1) {
          this.rows.mediumDemand.splice(rowIndex, 1);
          this.mediumDemand.splice(demandindex, 1);
        }
        break;
    }
  }
  

  onSubmitNext():void{
    if(this.isNextActive()){
      const airportData = { ...this.data, extremeDemand: [...this.extremeDemand],bigDemand:[...this.bigDemand],mediumDemand:[...this.mediumDemand] };
      this.scheduleForm.setFormData(airportData);
    }
  }

  addNewSubmit():void{
    if(this.airportsForm.valid){
      this.addToAirports();
    }
  }

  private addToAirports(){
    switch(this.airportsForm.value.demands){
      case 'Extreme Demand':
        this.rows.extremeDemand.push({airport:this.airportsForm.value.icao});
        this.extremeDemand.push(this.airportsForm.value.icao);
        break;
      case 'Big Demand':
        this.rows.bigDemand.push({airport:this.airportsForm.value.icao});
        this.bigDemand.push(this.airportsForm.value.icao);
        break;
      case 'Medium Demand':
        this.rows.mediumDemand.push({airport:this.airportsForm.value.icao});
        this.mediumDemand.push(this.airportsForm.value.icao);
        break;
    }
  }

  private addToAirportsWithData(airports:any[],demand: string){
    if(airports!=null){
    for(let key in airports){
      let airport = airports[key];
      switch(demand){
        case 'Extreme Demand':
          this.rows.extremeDemand.push({airport});
          this.extremeDemand.push(airport);
          break;
        case 'Big Demand':
          this.rows.bigDemand.push({airport});
          this.bigDemand.push(airport);
          break;
        case 'Medium Demand':
          this.rows.mediumDemand.push({airport});
          this.mediumDemand.push(airport);
          break;
      }
    }
  }
  }

  isNextActive():boolean{
    if(this.rows.extremeDemand.length == 0 && this.rows.bigDemand.length == 0 && this.rows.mediumDemand.length == 0){
      return false;
    }
    else{
      return true;
    }
  }
}
