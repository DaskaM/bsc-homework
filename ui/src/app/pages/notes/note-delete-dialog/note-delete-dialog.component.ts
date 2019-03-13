import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ModalWindowComponent} from '../../../components/modal-window/modal-window.component';
import {Note} from '../../../services/note/note';
import {NoteService} from '../../../services/note/note.service';

@Component({
  selector: 'app-note-delete-dialog',
  templateUrl: './note-delete-dialog.component.html',
  styleUrls: ['./note-delete-dialog.component.scss']
})
export class NoteDeleteDialogComponent implements OnInit {

  @ViewChild('deleteDialog')
  public deleteDialog: ModalWindowComponent;


  @Input()
  public noteToDelete: Note;

  @Output()
  public onDeleteConfirmation: EventEmitter<Note> = new EventEmitter();

  constructor(private noteService: NoteService) {
  }

  ngOnInit() {
  }

  public openModal(note: Note): void {
    this.deleteDialog.openModal<Note>(note);
  }

  public onModalConfirmation(note: Note): void {
    this.noteService.deleteNoteById(note.id).subscribe(data => {
      this.deleteDialog.closeModal();
      this.onDeleteConfirmation.emit(note);
    });
  }
}
