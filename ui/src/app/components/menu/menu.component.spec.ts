import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MenuComponent} from './menu.component';
import {TranslateModule, TranslateService} from '@ngx-translate/core';
import {Languages} from '../../languages.eum';
import {LocalStorageModule} from 'angular-2-local-storage';
import {LOCAL_STORAGE_PREFIX} from '../../constants/local-storage.constants';
import {RouterTestingModule} from '@angular/router/testing';

describe('MenuComponent', () => {
  let component: MenuComponent;
  let fixture: ComponentFixture<MenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        TranslateModule.forRoot(),
        LocalStorageModule.forRoot({prefix: LOCAL_STORAGE_PREFIX})
      ],
      declarations: [ MenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should change the language', () => {
    const translateService = fixture.debugElement.injector.get(TranslateService);
    component.changeLanguage(Languages.CS);
    expect(translateService.currentLang).toBe(Languages.CS);
  });
});
