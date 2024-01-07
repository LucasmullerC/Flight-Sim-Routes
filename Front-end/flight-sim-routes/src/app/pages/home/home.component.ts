import { AfterViewInit, Component, OnDestroy } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

declare global {
  interface Window {
    twttr: any;
  }
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnDestroy,AfterViewInit{
  reloadUpdates = false;
  navigationSubscription;
  constructor(private router: Router){
    this.navigationSubscription = this.router.events.subscribe();
  }
  ngOnDestroy() {
    if (this.navigationSubscription) {
      this.navigationSubscription.unsubscribe();
    }
  }

  ngAfterViewInit() {
    if (window.twttr) {
      window.twttr.widgets.load();
    }
  }

}
