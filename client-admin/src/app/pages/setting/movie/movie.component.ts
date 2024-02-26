import {Component, OnInit} from '@angular/core';
import {BreadcrumbItem} from "../../commons/breadcrumb/breadcrumb.component";
import {Category, Country, Movie} from "../setting.model";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {VOIDED_CHOICE} from "../../../utils/ConstUtil";
import {MovieSearch} from "../../../utils/search-object";
import {TranslateService} from "@ngx-translate/core";
import {MovieService} from "./movie.service";
import {CategoryService} from "../category/category.service";
import {CountryService} from "../country/country.service";
import {NzUploadFile} from "ng-zorro-antd/upload";
import {ApplicationConfigService} from "../../../services/application-config.service";
import {FileService} from "../../../services/file.service";

@Component({
  selector: 'thd-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.scss']
})
export class MovieComponent implements OnInit {

  breadcrumbs: BreadcrumbItem[] = [
    {link: null, name: this.translate.instant("settingModule.movie")}
  ]
  deleting: boolean = false
  isVisible = false;
  entity: Movie;
  formGroup: FormGroup;
  voidedList = VOIDED_CHOICE
  searchObject: MovieSearch = {
    pageIndex: 1,
    pageSize: 10,
  }
  totalElement = 0;
  pageElements: Movie[] = [];
  categories: Category[] = [];
  countries: Country[] = [];
  uploading : boolean = false;
  serverUrl: string = "";
  previewVisible: boolean = false;
  previewImage: string;
  years: number[] = [];
  fileList : NzUploadFile[] = [];
  constructor(private movieService: MovieService,
              private countryService: CountryService,
              private categoryService: CategoryService,
              private configService: ApplicationConfigService,
              private fileService: FileService,
              private translate: TranslateService) {
    this.serverUrl = this.configService.apiBaseUrl;
    for (let i = new Date().getFullYear();i>=1990;i--) {
      this.years.push(i);
    }
  }

  ngOnInit(): void {
    this.categoryService.getAll().subscribe(data => {
      this.categories = data?.body || [];
    })
    this.countryService.getAll().subscribe(data => {
      this.countries = data?.body || [];
    })
  }

  submitSearch() {
    this.searchObject.pageIndex = 1;
    this.loadTable();
  }

  loadTable() {
    let search = {...this.searchObject}
    search.pageIndex = search.pageIndex - 1;
    this.movieService.search(search).subscribe(data => {
      if (data.body) {
        this.pageElements = data.body.content;
        this.totalElement = data.body.totalElements;
      }
    })
  }

  onCreateOrUpdate(data: any) {
    this.isVisible = true;
    this.entity = data ? data : {}
    this.formGroup = new FormGroup({
      id: new FormControl(this.entity.id),
      name: new FormControl(this.entity.name, [Validators.required]),
      code: new FormControl(this.entity.code, [Validators.required]),
      publishYear: new FormControl(this.entity.publishYear, [Validators.required]),
      otherName: new FormControl(this.entity.otherName),
      country: new FormControl(this.entity.country, [Validators.required]),
      description: new FormControl(this.entity.description, [Validators.required]),
      file: new FormControl(this.entity.file, [Validators.required]),
      categories: new FormControl(this.entity.categories, [Validators.required]),
      voided: new FormControl(this.entity.voided ? this.entity.voided : false),
    })
    if(this.entity.file){
      this.fileList = [{
        url: this.serverUrl+'/api/v1/publish/files/'+this.entity.file?.id,
        status: 'done',
        name: this.entity.file.name,
        filename: this.entity.file.name,
        uid: this.entity.file?.id.toString()
      }]
    }
  }

  getErrorMessage(control: string): string {
    if (this.formGroup && control) {
      if (this.formGroup.get(control).errors?.['serverError'] ||
        this.formGroup.get(control).errors?.['serverErrorMess']) {
        return this.formGroup.get(control).errors?.['serverErrorMess'];
      }
      if (this.formGroup.get(control).errors?.['required']) {
        return this.translate.instant("common.fieldRequired");
      }
    }
    return "";
  }

  onSubmit() {
    this.movieService.save(this.formGroup.getRawValue()).subscribe(data => {
      if (data.code == 400) {
        if (data.body) {
          Object.keys(data.body).forEach(key => {
            this.formGroup.controls[key]?.setErrors({'serverError': true, 'serverErrorMess': data.body[key]});
          });
          return;
        }
      } else {
        this.isVisible = false;
        this.submitSearch();
      }

    })
  }

  pageChange(page: any) {
    this.loadTable();
  }

  delete(id: number) {
    this.deleting = true;
    this.movieService.delete(id).subscribe(data => {
      this.deleting = false;
      this.loadTable();
    })
  }
  handleChangeFileUpload(info: { file: NzUploadFile }): void {
    switch (info.file.status) {
      case 'uploading':
        this.uploading = true;
        break;
      case 'done':
        this.uploading = false;
        this.formGroup.get("file")?.setValue(info.file.response?.body);
        break;
      case 'error':
        this.uploading = false;
        break;
      case "removed":
        this.formGroup.get("file")?.setValue(null);
        break;
      case "success":
        this.uploading = false;
        break;
    }
  }
  handlePreview = async (file: NzUploadFile): Promise<void> => {
    this.previewImage = this.serverUrl+'/api/v1/publish/files/'+(file.response?.body?.id || file.uid);
    this.previewVisible = true;
    console.log(this.formGroup);
  };
  handleDownload = async (file: NzUploadFile): Promise<void> => {
    this.fileService.get(file.response?.body?.id || file.uid).subscribe(data =>{
      const a = document.createElement('a');
      const objectUrl = URL.createObjectURL(data);
      a.href = objectUrl;
      a.download = file.name;
      a.click();
      URL.revokeObjectURL(objectUrl);
    })
  };
}
