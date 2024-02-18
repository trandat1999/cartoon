import {APP_INITIALIZER, inject, LOCALE_ID, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {en_US, NZ_I18N, vi_VN} from 'ng-zorro-antd/i18n';
import {registerLocaleData} from '@angular/common';
import en from '@angular/common/locales/en';
import vi from '@angular/common/locales/vi';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from "./pages/layout/layout.module";
import {AuthModule} from "./pages/auth/auth.module";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {ApplicationConfigService} from "./services/application-config.service";
import {NgxSpinnerModule} from "ngx-spinner";
import {ToastrModule} from "ngx-toastr";
import {AuthInterceptor} from "./guards/auth.interceptor";

registerLocaleData(en);
registerLocaleData(vi);
export function rootLoaderI18n(http: HttpClient) {
  return new TranslateHttpLoader(http, "assets/i18n/", ".json");
}
@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    AuthModule,
    TranslateModule.forRoot({
      defaultLanguage: "en",
      loader: {
        provide: TranslateLoader,
        useFactory: rootLoaderI18n,
        deps: [HttpClient]
      }
    }),
    NgxSpinnerModule.forRoot({
        type: "ball-spin-clockwise"
      }
    ),
    ToastrModule.forRoot({
      timeOut: 10000,
      positionClass: 'toast-top-right',
      closeButton: true,
      progressBar: true,
      progressAnimation: "decreasing",
      enableHtml: true,
      tapToDismiss: true,
    }),
  ],
  providers: [
    {
      provide: NZ_I18N, useFactory: () => {
        const localId = inject(LOCALE_ID);
        switch (localId) {
          case 'en':
            return en_US;
          case 'vi':
            return vi_VN;
          default:
            return en_US;
        }
      }
    },
    {
      provide: APP_INITIALIZER,
      multi: true,
      deps: [ApplicationConfigService],
      useFactory: (applicationConfigService: ApplicationConfigService) => () => applicationConfigService.loadAppConfig()
    },
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
