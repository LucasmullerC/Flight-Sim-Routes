import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {

  constructor(private router: Router) {}

  menuActivated = window.innerWidth >= 1110;

  isRouteActive(routePaths: string[]): boolean {
    const currentUrl = this.router.url;

    return routePaths.some(routePath =>
      currentUrl.startsWith(routePath + '/') || currentUrl === routePath
    );
  }

  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    this.updateMenu();
  }

  private updateMenu(): void {
    this.menuActivated = window.innerWidth >= 1110;
  }

  toggleMenu() {
    this.menuActivated = !this.menuActivated;

    if(this.menuActivated == false){
      document.body.style.overflow ='auto';
    }
    else{
      document.body.style.overflow ='hidden';
    }
  }
}
