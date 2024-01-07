import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { SchedulesService } from 'src/app/services/schedules.service';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrl: './schedules.component.scss',
})
export class SchedulesComponent implements OnDestroy {
  activeForm: number = 1;
  error: boolean = false;
  navigationSubscription;
  constructor(
    private formService: ScheduleFormService,
    private scheduleService: SchedulesService,
    private scheduleForm: ScheduleFormService,
    private router: Router
  ) {
    this.formService.formCompleted.subscribe(() => {
      this.nextForm();
      console.log(this.activeForm)
    });

    this.navigationSubscription = this.router.events.subscribe((e: any) => {
      if (e instanceof NavigationEnd) {
        this.initialise();
      }
    });
  }

  initialise() {
    this.activeForm = 1;
  }

  ngOnDestroy() {
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  nextForm(): void {
    this.activeForm++;
    this.isFinished();
  }

  private isFinished(): void {
    if (this.activeForm == 5) {
      const airlineName = this.scheduleForm.getAirlineName();
      const dataStorage = this.scheduleForm.getFormDataList();
      const existingDataIndex = dataStorage.findIndex(
        (item) => item.airlineName === airlineName
      );
      const data = dataStorage[existingDataIndex];

      this.getRequest(data);
    }
  }

  private getRequest(data: any): void {
    this.scheduleService.generateSchedules(data).subscribe(
      (blob: Blob) => {
        const filename = 'schedules.zip';

        this.scheduleService.downloadFile(blob, filename);
      },
      (error) => {
        this.error = true;
        console.error('An error occured:', error);
      }
    );
  }
}
