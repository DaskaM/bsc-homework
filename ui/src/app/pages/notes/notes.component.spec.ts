import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NotesComponent} from './notes.component';
import {NoteService} from '../../services/note/note.service';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {RouterTestingModule} from '@angular/router/testing';
import {TranslateModule} from '@ngx-translate/core';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {NoteModule} from './note.module';

describe('NotesComponent', () => {
  let component: NotesComponent;
  let fixture: ComponentFixture<NotesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FontAwesomeModule,
        RouterTestingModule,
        TranslateModule.forRoot(),
        HttpClientTestingModule,
        NoteModule
      ],
      providers: [
        NoteService,
      ],
      declarations: [
      ],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
