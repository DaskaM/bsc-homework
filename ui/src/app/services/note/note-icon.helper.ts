import {faCheck, IconDefinition} from '@fortawesome/free-solid-svg-icons';


import {faTimes} from '@fortawesome/free-solid-svg-icons/faTimes';

export class NoteIconHelper {

  public static getFinishedStatus(isFinished: boolean): IconDefinition {
    return isFinished ? faCheck : faTimes;
  }
}
