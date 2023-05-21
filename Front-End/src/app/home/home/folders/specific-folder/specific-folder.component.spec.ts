import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpecificFolderComponent } from './specific-folder.component';

describe('SpecificFolderComponent', () => {
  let component: SpecificFolderComponent;
  let fixture: ComponentFixture<SpecificFolderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpecificFolderComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpecificFolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
