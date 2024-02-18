import {Injectable} from '@angular/core';
import {BaseService} from "../../../services/base.service";
import {MovieSearch} from "../../../utils/search-object";
import {Category} from "../setting.model";

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private readonly URL = "/api/v1/movies"
  constructor(private base : BaseService) {
  }
  getAll() {
    return this.base.get(this.URL);
  }
  search(search: MovieSearch) {
    return this.base.post(this.URL+"/pages", search);
  }
  get(id:number){
    return this.base.get(this.URL+"/"+id);
  }
  getByCode(code:string){
    return this.base.get(this.URL+"/code/"+code);
  }
  save(category:Category){
    return this.base.post(this.URL,category);
  }
  update(category:Category,id:number){
    return this.base.put(this.URL+"/"+id,category);
  }
  delete(id:number){
    return this.base.delete(this.URL+"/"+id);
  }
}
