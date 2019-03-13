import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NoteDeleteDialogComponent} from './note-delete-dialog.component';
import {SharedModule} from '../../../components/shared.module';
import {NoteService} from '../../../services/note/note.service';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {TranslateModule} from '@ngx-translate/core';

describe('NoteDeleteDialogComponent', () => {
  let component: NoteDeleteDialogComponent;
  let fixture: ComponentFixture<NoteDeleteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        SharedModule,
        HttpClientTestingModule,
        TranslateModule.forRoot()
      ],
      providers: [NoteService],
      declarations: [NoteDeleteDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoteDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
