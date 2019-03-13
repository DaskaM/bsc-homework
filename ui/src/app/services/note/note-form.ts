import {Note} from './note';


export class NoteForm {
  id: number;
  title: string;
  finished: boolean;

  setFromNote(note: Note): this {
    this.title = note.title;
    this.id = note.id;
    this.finished = note.finished;

    return this;
  }
}
