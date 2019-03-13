import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ModalWindowComponent} from './modal-window/modal-window.component';
import {SpinnerComponent} from './spinner/spinner.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
  imports: [
    CommonModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    TranslateModule
  ],
  declarations: [
    ModalWindowComponent,
    SpinnerComponent,
  ],
  exports: [
    SpinnerComponent,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    ModalWindowComponent
  ],
})
export class SharedModule {
}
