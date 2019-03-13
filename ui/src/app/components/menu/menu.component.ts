import {Component, OnInit} from '@angular/core';
import {Languages} from '../../languages.eum';
import {TranslateService} from '@ngx-translate/core';
import {LocalStorageService} from 'angular-2-local-storage';
import {DEFAULT_LANGUAGE} from '../../constants/local-storage.constants';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {
  public selectedLanguage: Languages;

  public languageDropDown = false;

  public definedLanguages = [
    {language: Languages.EN, title: 'English'},
    {language: Languages.CS, title: 'Čeština'},
  ];

  constructor(private translate: TranslateService,
              private localStorageService: LocalStorageService) { }

  ngOnInit() {
    this.selectedLanguage = this.localStorageService.get(DEFAULT_LANGUAGE);
  }

  public changeLanguage(language: Languages): void {
    this.saveLanguageSelection(language);
    this.translate.use(language);
    this.selectedLanguage = language;
    this.languageDropDown = false;
  }

  private saveLanguageSelection(language: Languages): void {
    this.localStorageService.set(DEFAULT_LANGUAGE, language);
  }
}
