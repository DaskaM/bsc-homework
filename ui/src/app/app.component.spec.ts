import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {RouterTestingModule} from '@angular/router/testing';
import {AppComponent} from './app.component';
import {TranslateModule, TranslateService} from '@ngx-translate/core';
import {MenuComponent} from './components/menu/menu.component';
import {Languages} from './languages.eum';
import {LocalStorageModule, LocalStorageService} from 'angular-2-local-storage';
import {LOCAL_STORAGE_PREFIX} from './constants/local-storage.constants';
import {NoteModule} from './pages/notes/note.module';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {SharedModule} from './components/shared.module';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        TranslateModule.forRoot(),
        LocalStorageModule.forRoot({prefix: LOCAL_STORAGE_PREFIX}),
        NoteModule,
        SharedModule,
        HttpClientTestingModule,
      ],
      providers: [
      ],
      declarations: [
        AppComponent,
        MenuComponent
      ],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    const localStorageService: LocalStorageService = fixture.debugElement.injector.get(LocalStorageService);
    localStorageService.clearAll();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it(`should have the default language set to English'`, () => {
    const translateService: TranslateService = fixture.debugElement.injector.get(TranslateService);
    expect(translateService.defaultLang).toBe(Languages.EN);
  });
});
