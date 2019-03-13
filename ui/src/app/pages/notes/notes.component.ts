import {Component, OnInit, ViewChild} from '@angular/core';
import {NoteModalComponent} from './note-modal/note-modal.component';

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.scss']
})
export class NotesComponent implements OnInit {

  @ViewChild('noteModal')
  public noteModal: NoteModalComponent;

  constructor() { }

  ngOnInit() {
  }

  public createNote(): void {
     this.noteModal.openDialog(false);
  }
}
