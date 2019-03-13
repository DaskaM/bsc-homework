import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NoteDetailComponent} from './note-detail.component';
import {NoteService} from '../../../services/note/note.service';
import {ActivatedRoute} from '@angular/router';
import {RouterTestingModule} from '@angular/router/testing';
import {Note} from '../../../services/note/note';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {Observable} from 'rxjs';
import {SharedModule} from '../../../components/shared.module';
import {NoteDeleteDialogComponent} from '../note-delete-dialog/note-delete-dialog.component';
import {NoteModalComponent} from '../note-modal/note-modal.component';
import {TranslateModule} from '@ngx-translate/core';
import 'rxjs-compat/add/observable/of';

describe('NoteDetailComponent', () => {
  let component: NoteDetailComponent;
  let fixture: ComponentFixture<NoteDetailComponent>;
  let noteService: NoteService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule,
        SharedModule,
        TranslateModule.forRoot()
      ],
      providers: [
        NoteService,
        {provide: ActivatedRoute, useValue: fakeActivatedRoute},
      ],
      declarations: [NoteDetailComponent, NoteDeleteDialogComponent, NoteModalComponent],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteDetailComponent);
    component = fixture.componentInstance;
    noteService = fixture.debugElement.injector.get(NoteService);

    spyOn(noteService, 'getNoteById').and.returnValue(Observable.of(note1));

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an id', () => {
    const route: ActivatedRoute = fixture.debugElement.injector.get(ActivatedRoute);
    expect(route.snapshot.params['id']).toBe(6);
  });

  it('should have selectedNote set', () => {
    const noteData = component.selectedNote;
    expect(noteData).toBe(note1);
  });
});

const note1: Note = new Note().deserialize({
  id: 6,
  title: 'Faked Note',
  finished: true,
  created: new Date(),
  lastUpdated: new Date()
});

const fakeActivatedRoute = {
  snapshot : {
    params : {
      id: 6
    }
  },
};
