import {Component, Input, OnInit} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

export interface BreadcrumbItem {
  link?: string;
  name?: string;
}
@Component({
  selector: 'thd-breadcrumb',
  templateUrl: './breadcrumb.component.html',
  styleUrls: ['./breadcrumb.component.scss']
})
export class BreadcrumbComponent implements OnInit {

  @Input() items :BreadcrumbItem[] = [];
  @Input() autoGenerate: boolean = false;
  @Input() routeLabel: string = 'breadcrumb';
  @Input() routeLabelFn: (label: string) => string = label => label;

  constructor(
    private translateService: TranslateService
  ) { }

  ngOnInit(): void {
  }

}
