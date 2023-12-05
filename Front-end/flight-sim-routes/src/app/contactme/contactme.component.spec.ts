import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactmeComponent } from './contactme.component';

describe('ContactmeComponent', () => {
  let component: ContactmeComponent;
  let fixture: ComponentFixture<ContactmeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactmeComponent]
    });
    fixture = TestBed.createComponent(ContactmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
