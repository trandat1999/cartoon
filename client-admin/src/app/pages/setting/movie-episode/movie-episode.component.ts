import {Component, OnInit, ViewChild} from '@angular/core';
import {BreadcrumbItem} from "../../commons/breadcrumb/breadcrumb.component";
import {Movie, MovieEpisode, MovieLink} from "../setting.model";
import {
  AbstractControl,
  FormArray,
  FormControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {VOIDED_CHOICE} from "../../../utils/ConstUtil";
import {MovieSearch} from "../../../utils/search-object";
import {NzUploadFile} from "ng-zorro-antd/upload";
import {MovieService} from "../movie/movie.service";
import {ApplicationConfigService} from "../../../services/application-config.service";
import {FileService} from "../../../services/file.service";
import {TranslateService} from "@ngx-translate/core";
import {MovieEpisodeService} from "./movie-episode.service";

@Component({
  selector: 'thd-movie-episode',
  templateUrl: './movie-episode.component.html',
  styleUrls: ['./movie-episode.component.scss']
})
export class MovieEpisodeComponent implements OnInit {

  breadcrumbs: BreadcrumbItem[] = [
    {link: null, name: this.translate.instant("settingModule.movieEpisode")}
  ]
  deleting: boolean = false
  isVisible = false;
  entity: MovieEpisode;
  formGroup: FormGroup;
  voidedList = VOIDED_CHOICE
  searchObject: MovieSearch = {
    pageIndex: 1,
    pageSize: 10,
  }
  totalElement = 0;
  pageElements: MovieEpisode[] = [];
  uploading: boolean = false;
  serverUrl: string = "";
  fileList: NzUploadFile[] = [];
  movies: Movie[] = [];

  constructor(private movieService: MovieService,
              private movieEpisodeService: MovieEpisodeService,
              private configService: ApplicationConfigService,
              private fileService: FileService,
              private translate: TranslateService) {
    this.serverUrl = this.configService.apiBaseUrl;
    this.getAllMovie()
  }

  getAllMovie() {
    this.movieService.getAll().subscribe(data => {
      this.movies = data.body || [];
    })
  }

  ngOnInit(): void {
  }

  submitSearch() {
    this.searchObject.pageIndex = 1;
    this.loadTable();
  }

  loadTable() {
    let search = {...this.searchObject}
    search.pageIndex = search.pageIndex - 1;
    this.movieEpisodeService.search(search).subscribe(data => {
      if (data.body) {
        this.pageElements = data.body.content;
        this.totalElement = data.body.totalElements;
      } else {
        this.pageElements = [];
        this.totalElement = 0;
      }
    })
  }

  onCreateOrUpdate(data: any) {
    if (this.movies.length == 0) {
      this.getAllMovie();
    }
    this.isVisible = true;
    this.entity = data ? data : {}
    this.formGroup = new FormGroup({
      id: new FormControl(this.entity.id),
      name: new FormControl(this.entity.name, [Validators.required]),
      code: new FormControl(this.entity.code, [Validators.required]),
      movie: new FormControl(this.entity.movie, [Validators.required]),
      episode: new FormControl(this.entity.episode, [Validators.required]),
      previousEpisode: new FormControl(this.entity.previousEpisode),
      part: new FormControl(this.entity.part),
      links: new FormArray([], [Validators.required]),
      voided: new FormControl(this.entity.voided ? this.entity.voided : false, [Validators.required]),
    });
    if (this.entity.links && this.entity.links.length) {
      for (let item of this.entity.links) {
        this.links.push(this.initMovieLinkGroup(item))
      }
    }
  }
  checkValidatorsExistValue():ValidatorFn{
    return (control: AbstractControl): ValidationErrors | null => {
      const file = control.get('file')?.value;
      let errors = control.get('embeddedLink')?.errors;
      if (!file) {
        control.get('embeddedLink')?.setValidators([Validators.required]);
        if(!control.get('embeddedLink')?.value){
          control.get('embeddedLink')?.setErrors({...errors,required:true});
        }
      }else{
        control.get('embeddedLink')?.setValidators(null);
        control.get('embeddedLink')?.setErrors(null);
      }
      return null;
    }
  }

  get links(): FormArray {
    return this.formGroup?.get("links") as FormArray;
  }

  initMovieLinkGroup(item: MovieLink) {
    let fileList: NzUploadFile[] = [];
    if(item.file){
      fileList.push({
        status: "done",
        uid: item.file.id.toString(),
        url: this.serverUrl+"/api/v1/files/"+item.file.id.toString(),
        name: item.file.name,
        size: item.file.size,
        response: item.file
      })
    }
    return new FormGroup({
      id: new FormControl(item.id),
      name: new FormControl(item.name, [Validators.required]),
      orderNumber: new FormControl(item.orderNumber, [Validators.required]),
      embeddedLink: new FormControl(item.embeddedLink),
      file: new FormControl(item.file),
      listFile: new FormControl(fileList)
    },this.checkValidatorsExistValue())

  }
  addLink() {
    this.links.push(this.initMovieLinkGroup({}));
  }

  get dataTable() {
    return [...this.links.controls]
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
    this.movieEpisodeService.save(this.formGroup.getRawValue()).subscribe(data => {
      if (data.code == 400) {
        if (data.body) {
          Object.keys(data.body).forEach(key => {
            let field = key.replace("]","");
            field = field.replace("[",".");
            this.formGroup.get(field)?.setErrors({'serverError': true, 'serverErrorMess': data.body[key]});
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
    this.movieEpisodeService.delete(id).subscribe(data => {
      this.deleting = false;
      this.loadTable();
    })
  }

  handleChangeFileUpload(info: { file: NzUploadFile },index : number): void {
    switch (info.file.status) {
      case 'uploading':
        this.uploading = true;
        break;
      case 'done':
        this.uploading = false;
        this.formGroup.get("links."+index+".file")?.setValue(info.file.response?.body);
        break;
      case 'error':
        this.uploading = false;
        break;
      case "removed":
        this.formGroup.get("links."+index+".file")?.setValue(null);
        break;
      case "success":
        this.uploading = false;
        break;
    }
  }

  handleDownload = async (file: NzUploadFile): Promise<void> => {
    this.fileService.get(file.response?.body?.id || file.uid).subscribe(data => {
      const a = document.createElement('a');
      const objectUrl = URL.createObjectURL(data);
      a.href = objectUrl;
      a.download = file.name;
      a.click();
      URL.revokeObjectURL(objectUrl);
    })
  };
}
