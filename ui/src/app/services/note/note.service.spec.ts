import {getTestBed, TestBed} from '@angular/core/testing';

import {NoteService} from './note.service';
import {NoteModule} from '../../pages/notes/note.module';
import {HttpClientTestingModule, HttpTestingController, TestRequest} from '@angular/common/http/testing';
import {Note} from './note';
import {TranslateModule} from '@ngx-translate/core';

describe('NoteService', () => {
  let injector: TestBed;
  let noteService: NoteService;
  let httpClientMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        NoteModule,
        HttpClientTestingModule,
        TranslateModule.forRoot()
      ],
    });

    injector = getTestBed();
    noteService = injector.get(NoteService);
    httpClientMock = injector.get(HttpTestingController);
  });

  it('should be created', () => {
    const service: NoteService = TestBed.get(NoteService);
    expect(service).toBeTruthy();
  });

  it('should be initialized', () => {
    noteService.getNotesSubscription().subscribe(data => {
      expect([fakeResponse1, fakeResponse2]).toEqual(data);
    });

    const request: TestRequest = httpClientMock.expectOne('http://localhost:8080/notes');
    request.flush([fakeResponse1, fakeResponse2]);
    expect(request.request.method).toBe('GET');
    httpClientMock.verify();
  });

  it('should test method getById', () => {
    noteService.getNoteById(fakeResponse1.id).subscribe(data => {
      expect(data).toEqual(expectedNote);
    });

    const expectedNote = new Note().deserialize(fakeResponse1);

    const request: TestRequest = httpClientMock.expectOne('http://localhost:8080/notes/' + fakeResponse1.id);
    expect(request.request.method).toBe('GET');
    request.flush(fakeResponse1);

  });

  const fakeResponse1: Note = new Note().deserialize({
    id: 5,
    title: 'faked title',
    finished: true,
    created: new Date(),
    lastUpdated: new Date()
  });

  const fakeResponse2: Note = new Note().deserialize({
    id: 96,
    title: 'faked title 2',
    finished: false,
    created: new Date(),
    lastUpdated: new Date()
  });
});
