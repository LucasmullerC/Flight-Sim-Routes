import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-flights-form',
  templateUrl: './flights-form.component.html',
  styleUrls: ['./flights-form.component.scss']
})
export class FlightsFormComponent {
  @Input() formOptions = [
    {
      title: String,
      placeholder: String,
      type: String,
    }
  ];
}
