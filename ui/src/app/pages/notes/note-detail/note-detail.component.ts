import {Component, OnInit, ViewChild} from '@angular/core';
import {NoteService} from '../../../services/note/note.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Note} from '../../../services/note/note';

import {faLongArrowAltLeft} from '@fortawesome/free-solid-svg-icons/faLongArrowAltLeft';
import {NoteDeleteDialogComponent} from '../note-delete-dialog/note-delete-dialog.component';
import {NoteModalComponent} from '../note-modal/note-modal.component';
import {NoteIconHelper} from '../../../services/note/note-icon.helper';
import {IconDefinition} from '@fortawesome/fontawesome-svg-core';
import {faPencilAlt, faTrash} from '@fortawesome/free-solid-svg-icons';
import {faCheck} from '@fortawesome/free-solid-svg-icons/faCheck';
import {NoteForm} from '../../../services/note/note-form';

@Component({
  selector: 'app-notes-detail',
  templateUrl: './note-detail.component.html',
  styleUrls: ['./note-detail.component.scss']
})
export class NoteDetailComponent implements OnInit {

  @ViewChild('deleteDialog')
  public deleteDialog: NoteDeleteDialogComponent;

  @ViewChild('noteModal')
  public noteModal: NoteModalComponent;

  public selectedNote: Note;
  public backIcon = faLongArrowAltLeft;
  public editIcon = faPencilAlt;
  public deleteIcon = faTrash;

  private noteId: number;

  constructor(private noteService: NoteService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.noteId  = +this.route.snapshot.params['id'];
    this.getNoteDetail();
  }

  private getNoteDetail(): void {
   this.noteService.getNoteById(this.noteId).subscribe(data => {
      this.selectedNote = data;
   }, error => {
     this.router.navigate(['/404']);
   });
  }

  public deleteNote(): void {
    this.deleteDialog.openModal(this.selectedNote);
  }

  public editNote(): void {
    this.noteModal.openDialog(true, this.selectedNote);
  }

  public onDeleteConfirmation(note: Note): void {
    this.router.navigate(['/']);
  }

  public onConfirmation(noteForm: NoteForm): void {
    this.getNoteDetail();
  }

  public getFinishedStatus(finished: boolean): IconDefinition {
    return NoteIconHelper.getFinishedStatus(finished);
  }

}
