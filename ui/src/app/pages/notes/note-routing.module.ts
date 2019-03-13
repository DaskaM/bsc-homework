import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {NoteDetailComponent} from './note-detail/note-detail.component';
import {NotesComponent} from './notes.component';
import {NotesTableComponent} from './notes-table/notes-table.component';

const routes: Routes = [
  {
    path: 'detail/:id',
    component: NoteDetailComponent
  },
  {
    path: 'aa',
    component: NotesTableComponent
  },
  {
    path: '',
    component: NotesComponent
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NoteRoutingModule {

}

export const routedComponents = [
  NotesComponent,
  NoteDetailComponent,
  NotesTableComponent
];
