import {AbstractControl} from '@angular/forms';

export class SelectValidator {

  public static validate(abstractControl: AbstractControl) {
    const selectedValue = abstractControl.value;

    if (selectedValue === null || selectedValue === '-1') {
      return {emptySelection: true};
    }

    return null;
  }

}
