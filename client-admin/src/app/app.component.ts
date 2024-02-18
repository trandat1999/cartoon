import {AfterViewInit, Component, OnInit} from '@angular/core';
import {PreloaderService} from "./services/preloader.service";

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
  ) {
  }
  ngOnInit(): void {
  }
}
