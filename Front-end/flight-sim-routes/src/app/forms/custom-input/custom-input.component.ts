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

  @Output() formControlChange = new EventEmitter<string>();

  onFormControlChange(event:any) {
    this.formControlChange.emit(event.target.value);
  }
}
