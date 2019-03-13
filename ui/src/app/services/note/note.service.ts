import {Injectable} from '@angular/core';
import {Note} from './note';
import {Observable, ReplaySubject} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {NoteForm} from './note-form';
import {TranslateService} from '@ngx-translate/core';
import {environment} from '../../../environments/environment';

@Injectable()
export class NoteService {
  private readonly notesEndpoint: string = environment.apiBaseUrl + 'notes';

  private replaySubject: ReplaySubject<Note[]> = new ReplaySubject();

  constructor(private httpClient: HttpClient,
              private translateService: TranslateService) {
    this.getAllNotes();
  }

  public getNotesSubscription(): Observable<Note[]> {
    return this.replaySubject.asObservable();
  }

  private getAllNotes(): void {
    this.httpClient
      .get<Note[]>(this.notesEndpoint)
      .map(data => data.map(item => new Note().deserialize(item)))
      .subscribe((data: Note[]) => this.replaySubject.next(data));
  }

  public getNoteById(id: number): Observable<any> {
    return this.httpClient
      .get<Note>(this.notesEndpoint + '/' + id)
      .map(data => new Note().deserialize(data));
  }

  public createNote(noteForm: NoteForm): Observable<any> {
    return this.httpClient
      .post( this.notesEndpoint, noteForm)
      .map(data => this.getAllNotes());
  }

  public updateNote(noteForm: NoteForm): Observable<any> {
    return this.httpClient
      .put(this.notesEndpoint, noteForm)
      .map(data => this.getAllNotes());
  }

  public deleteNoteById(id: number): Observable<any> {
    return this.httpClient
      .delete(this.notesEndpoint + '/' + id)
      .map(() => this.getAllNotes());
  }

  public getFinishedStatusTitle(finished: boolean): Observable<string> {
    const key = 'noteStatus.' + (finished ? 'finished' : 'notFinished');
    return this.translateService.get(key);
  }
}
