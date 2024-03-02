import {AfterViewInit, Component, OnInit} from '@angular/core';
import {PreloaderService} from "./services/preloader.service";
import {TranslateService} from "@ngx-translate/core";
import {StorageService} from "./services/storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit,AfterViewInit{
  ngAfterViewInit(): void {
    this.preloader.hide();
  }
  constructor(
    private preloader: PreloaderService,
    private translate: TranslateService,
    private storeService: StorageService
  ) {
  }
  ngOnInit(): void {
    this.translate.use(this.storeService.getLanguage())
  }
}
