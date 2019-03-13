import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NoteModalComponent} from './note-modal.component';
import {SharedModule} from '../../../components/shared.module';
import {TranslateModule} from '@ngx-translate/core';
import {NoteService} from '../../../services/note/note.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';

describe('NoteModalComponent', () => {
  let component: NoteModalComponent;
  let fixture: ComponentFixture<NoteModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        SharedModule,
        TranslateModule.forRoot(),
        HttpClientTestingModule
      ],
      providers: [NoteService],
      declarations: [ NoteModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
