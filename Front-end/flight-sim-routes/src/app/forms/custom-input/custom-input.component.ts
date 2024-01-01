import { Component, EventEmitter, Input, Output} from '@angular/core';

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
  @Input() icaoInput: boolean = false;

  @Input() icaoList: string[] = [];
  currentInput: string = '';

  @Output() formControlChange = new EventEmitter<string>();

  onFormControlChange(event:any) {
    if(this.icaoInput){
      this.icaoFormat(event);
      this.formControlChange.emit(this.icaoList.toString());
    }
    else{
      const value = ((this.uppercase) ? event.target.value.toUpperCase() : event.target.value)
      this.formControlChange.emit(value);
    }
  }

  icaoFormat(event: any) {
    const inputValue = event.target.value.toUpperCase();
    const icaoRegex = /^[A-Z]{4}$/;

    if (icaoRegex.test(inputValue) || inputValue === '') {
      this.icaoList.push(inputValue);
      this.currentInput = '';
    }
  }
}
