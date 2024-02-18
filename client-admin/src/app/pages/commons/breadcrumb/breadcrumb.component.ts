import {Component, Input, OnInit} from '@angular/core';

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

  @Input()
  items :BreadcrumbItem[] = [];
  @Input()
  autoGenerate: boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

}
