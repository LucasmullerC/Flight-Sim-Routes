import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Input() title: string  = '';
  @Input() subtitle: string  = '';
  @Input() imageUrl: string = '';

  isSubtitleActive(): boolean{
    if(this.subtitle == ''){
      return false;
    }
    else{
      return true;
    }
  }
}
