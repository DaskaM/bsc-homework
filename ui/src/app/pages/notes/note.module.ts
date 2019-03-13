import {NgModule} from '@angular/core';
import {NoteService} from '../../services/note/note.service';
import {NoteRoutingModule, routedComponents} from './note-routing.module';
import {NotesTableComponent} from './notes-table/notes-table.component';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';
import {SharedModule} from '../../components/shared.module';
import {NoteDeleteDialogComponent} from './note-delete-dialog/note-delete-dialog.component';
import {NoteModalComponent} from './note-modal/note-modal.component';

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    NoteRoutingModule,
    SharedModule
  ],
  declarations: [
    NotesTableComponent,
    ...routedComponents,
    NoteDeleteDialogComponent,
    NoteModalComponent,
  ],
  providers: [NoteService],
})
export class NoteModule {

}
