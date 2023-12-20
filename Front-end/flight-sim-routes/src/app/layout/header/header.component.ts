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
  @Input() help:boolean = false;

  helpPopup:boolean = false;

  isSubtitleActive(): boolean{
    if(this.subtitle == ''){
      return false;
    }
    else{
      return true;
    }
  }
  isImgActive():boolean{
    if(this.imageUrl == ''){
      return false;
    }
    else{
      return true;
    }
  }

  isHelpPopupActive(){
    if(this.helpPopup==false){
      this.helpPopup = true;
    }
    else{
      this.helpPopup = false;
    }
  }
}
