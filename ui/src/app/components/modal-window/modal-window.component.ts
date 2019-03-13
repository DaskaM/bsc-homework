import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';
import {IconDefinition} from '@fortawesome/fontawesome-svg-core';

@Component({
  selector: 'app-modal-window',
  templateUrl: './modal-window.component.html',
  styleUrls: ['./modal-window.component.scss']
})
export class ModalWindowComponent implements OnInit {

  @Input()
  public headerMessage = 'modalWindow.defaultTitle';

  @Input()
  public headerColor = '#007bff';

  @Input()
  public showModal = false;

  @Input()
  public showConfirmation = false;

  @Input()
  public confirmationSettings: any;

  @Output()
  public modalWindowClose: EventEmitter<boolean> = new EventEmitter();

  @Output()
  public modalWindowConfirmation: EventEmitter<any> = new EventEmitter();

  public closeIcon: IconDefinition = faTimes;

  private dialogData: any;

  constructor() { }

  ngOnInit() {
  }

  public openModal<T>(dialogData: T): void {
    this.dialogData = dialogData;
    this.showModal = true;
  }

  public closeModal(): void {
    this.showModal = false;
    this.modalWindowClose.emit(this.showModal);
  }

  public confirmModal(): void {
    this.modalWindowConfirmation.emit(this.dialogData);
  }
}
