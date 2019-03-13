import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NotesTableComponent} from './notes-table.component';
import {NoteService} from '../../../services/note/note.service';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {RouterTestingModule} from '@angular/router/testing';
import {TranslateModule} from '@ngx-translate/core';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {SharedModule} from '../../../components/shared.module';
import {NoteDeleteDialogComponent} from '../note-delete-dialog/note-delete-dialog.component';

describe('NotesTableComponent', () => {
  let component: NotesTableComponent;
  let fixture: ComponentFixture<NotesTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FontAwesomeModule,
        RouterTestingModule,
        TranslateModule.forRoot(),
        HttpClientTestingModule,
        SharedModule
      ],
      providers: [NoteService],
      declarations: [
        NotesTableComponent,
        NoteDeleteDialogComponent
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    fixture.detectChanges();
    expect(component).toBeTruthy();
  });
});
