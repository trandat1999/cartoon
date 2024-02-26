import { Injectable } from '@angular/core';
import {BaseService} from "../../../services/base.service";
import {MovieSearch} from "../../../utils/search-object";
import {Category} from "../setting.model";

@Injectable({
  providedIn: 'root'
})
export class MovieEpisodeService {
  private readonly URL = "/api/v1/movie-episodes"
  constructor(private base : BaseService) {
  }
  search(search: MovieSearch) {
    return this.base.post(this.URL+"/pages", search);
  }
  get(id:number){
    return this.base.get(this.URL+"/"+id);
  }
  save(object:any){
    return this.base.post(this.URL,object);
  }
  delete(id:number){
    return this.base.delete(this.URL+"/"+id);
  }
}
