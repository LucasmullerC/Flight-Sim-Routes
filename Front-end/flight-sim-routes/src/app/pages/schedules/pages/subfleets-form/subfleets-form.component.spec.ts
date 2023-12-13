import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubfleetsFormComponent } from './subfleets-form.component';

describe('SubfleetsFormComponent', () => {
  let component: SubfleetsFormComponent;
  let fixture: ComponentFixture<SubfleetsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubfleetsFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SubfleetsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
