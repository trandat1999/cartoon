import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth/auth.service";
import {Router} from "@angular/router";
import {StorageService} from "../../services/storage.service";
import {TranslateService} from "@ngx-translate/core";
import {TranslateConfigService} from "../../services/translate.service";

@Component({
  selector: 'thd-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  isCollapsed = false;
  currentLanguage = "en";
  constructor(private authService:AuthService,
              private translateService:TranslateConfigService,
              private router:Router,
              private translate: TranslateService,
              private storage : StorageService) {
    this.currentLanguage = this.translateService.getLanguage();
  }

  ngOnInit(): void {

  }
  logout(): void {
    this.authService.logout().subscribe(data => {
    });
    this.storage.signOut();
    this.router.navigate(['/login'])
  }
  changeLanguage(lang: string){
    this.translateService.changeLanguage(lang);
  }
  translateFn = (key:string) => {
    if(key){
      return this.translate.instant(""+ key);
    }else{
      return "";
    }
  }
}
