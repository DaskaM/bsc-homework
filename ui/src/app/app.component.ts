import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {environment} from '../environments/environment';
import {LocalStorageService} from 'angular-2-local-storage';
import {DEFAULT_LANGUAGE} from './constants/local-storage.constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {

  constructor(private translate: TranslateService,
              private localStorageService: LocalStorageService) {
    this.setDefaultLanguage();
  }

  private setDefaultLanguage(): void {
    const storedLanguage = this.localStorageService.get<string>(DEFAULT_LANGUAGE);

    if (storedLanguage) {
      this.translate.setDefaultLang(storedLanguage);
    } else {
      this.localStorageService.set(DEFAULT_LANGUAGE, environment.defaultLanguage);
      this.translate.setDefaultLang(environment.defaultLanguage);
    }
  }
}
