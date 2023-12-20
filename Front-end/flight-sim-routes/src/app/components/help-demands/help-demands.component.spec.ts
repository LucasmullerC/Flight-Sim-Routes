import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HelpDemandsComponent } from './help-demands.component';

describe('HelpDemandsComponent', () => {
  let component: HelpDemandsComponent;
  let fixture: ComponentFixture<HelpDemandsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HelpDemandsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HelpDemandsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
