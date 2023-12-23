import { Component} from '@angular/core';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { SchedulesService } from 'src/app/services/schedules.service';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrl: './schedules.component.scss'
})
export class SchedulesComponent {
  activeForm: number = 1;
  error: boolean = false;
  constructor(private formService: ScheduleFormService,
    private scheduleService: SchedulesService,
    private scheduleForm: ScheduleFormService) {
      this.formService.formCompleted.subscribe(() => {
        this.nextForm();
      });
  }

  nextForm(): void {
    this.activeForm++;
    this.isFinished();
  }

  private isFinished(): void{
    if(this.activeForm == 5){
      const airlineName = this.scheduleForm.getAirlineName();
      const dataStorage = this.scheduleForm.getFormDataList();
      const existingDataIndex = dataStorage.findIndex(item => item.airlineName === airlineName);
      const data = dataStorage[existingDataIndex];

      this.getRequest(data);
    }
  }

  private getRequest(data: any):void{
    this.scheduleService.generateSchedules(data).subscribe(
      (blob: Blob) => {
        const filename = 'schedules.zip';

        this.scheduleService.downloadFile(blob, filename);
      },
      error => {
        this.error = true;
        console.error('An error occured:', error);
      }
    );
  }
}
