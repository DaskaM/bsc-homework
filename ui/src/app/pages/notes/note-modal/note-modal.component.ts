import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ModalWindowComponent} from '../../../components/modal-window/modal-window.component';
import {NoteForm} from '../../../services/note/note-form';
import {FormBuilder, Validators} from '@angular/forms';
import {Note} from '../../../services/note/note';
import {NoteService} from '../../../services/note/note.service';


@Component({
  selector: 'app-note-modal',
  templateUrl: './note-modal.component.html',
  styleUrls: ['./note-modal.component.scss']
})
export class NoteModalComponent implements OnInit {

  @ViewChild('modalDialog')
  public modalDialog: ModalWindowComponent;

  @Input()
  public showModal = false;

  @Input()
  public edit = false;

  @Output()
  public onConfirmation: EventEmitter<NoteForm> = new EventEmitter<NoteForm>();

  public noteFormData: NoteForm = new NoteForm();

  public noteForm = this.formBuilder.group({
    title: [this.noteFormData.title, Validators.required],
    id: [this.noteFormData.id],
    finished: [this.noteFormData.finished]
  });

  constructor(private formBuilder: FormBuilder,
              private noteService: NoteService) {
  }

  ngOnInit() {
  }

  public openDialog(edit: boolean, note?: Note): void {
    if (edit && note) {
      this.edit = true;
      this.noteFormData.setFromNote(note);
      this.noteForm.setValue(this.noteFormData);
    }

    this.modalDialog.openModal<NoteForm>(this.noteFormData);
  }

  public onNoteClose(): void {
    this.noteForm.reset();
    this.noteFormData = new NoteForm();
  }

  public noteSubmit(): void {
    this.noteFormData = this.noteForm.getRawValue();

    if (this.edit) {
      this.noteService.updateNote(this.noteFormData)
        .subscribe(() => this.closeModal());
    } else {
      this.noteService.createNote(this.noteFormData)
        .subscribe(() => this.closeModal());
    }
  }

  private closeModal(): void {
    this.modalDialog.closeModal();
    this.noteForm.reset();
    this.onConfirmation.emit(this.noteFormData);
  }
}
