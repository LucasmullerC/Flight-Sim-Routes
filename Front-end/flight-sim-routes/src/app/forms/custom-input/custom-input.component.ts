import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-custom-input',
  templateUrl: './custom-input.component.html',
  styleUrl: './custom-input.component.scss'
})
export class CustomInputComponent {
  @Input() type: string = '';
  @Input() placeholder: string = '';
  @Input() required: boolean = false;
  @Input() min?: number = 0;
  @Input() max?: number = 0;
  @Input() uppercase: boolean = false;

  @Output() formControlChange = new EventEmitter<string>();

  onFormControlChange(event:any) {
    const value = ((this.uppercase) ? event.target.value.toUpperCase() : event.target.value)
    this.formControlChange.emit(value);
  }
}
