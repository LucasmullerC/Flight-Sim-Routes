import { Component, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-help-demands',
  templateUrl: './help-demands.component.html',
  styleUrl: './help-demands.component.scss'
})
export class HelpDemandsComponent {
  @Output() closePage: EventEmitter<void> = new EventEmitter<void>();

  onClosePage(){
    this.closePage.emit();
  }
}
