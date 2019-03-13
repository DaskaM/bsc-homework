import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {NoteService} from '../../../services/note/note.service';
import {Note} from '../../../services/note/note';
import {faTrash, IconDefinition} from '@fortawesome/free-solid-svg-icons';
import {Router} from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import 'rxjs-compat/add/operator/map';
import {NoteDeleteDialogComponent} from '../note-delete-dialog/note-delete-dialog.component';
import {NoteIconHelper} from '../../../services/note/note-icon.helper';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-notes-table',
  templateUrl: './notes-table.component.html',
  styleUrls: ['./notes-table.component.scss']
})
export class NotesTableComponent implements OnInit, OnDestroy {

  @ViewChild('table')
  public table;

  public notes: Note[];

  public binIcon: IconDefinition = faTrash;

  public noteToDelete: Note;

  @ViewChild('deleteDialog')
  public deleteDialog: NoteDeleteDialogComponent;

  private notesSubscription: Subscription;

  constructor(private noteService: NoteService,
              private translateService: TranslateService,
              private router: Router) {
  }

  ngOnInit() {
    this.notesSubscription = this.noteService
      .getNotesSubscription()
      .subscribe(data => this.notes = data);
  }

  ngOnDestroy(): void {
    this.notesSubscription.unsubscribe();
  }

  public onRowClick(note: Note): void {
    this.router.navigate(['detail', note.id]);
  }

  public getFinishedStatus(finished: boolean): IconDefinition {
    return NoteIconHelper.getFinishedStatus(finished);
  }

  public deleteNote(event: Event, note: Note): void {
    event.stopPropagation();
    this.noteToDelete = note;
    this.deleteDialog.openModal(note);
  }
}
