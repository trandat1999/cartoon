export interface BaseObject {
  name?:string;
  code?:string;
  description?:string;
  id?:number;
  voided?:boolean;
}
export interface Category extends BaseObject{
}
export interface Country extends BaseObject{
}
export interface Movie extends BaseObject{
  publishYear?:number;
  country?:Country;
  file?:File;
  otherName?:string;
  categories?:Category[];
}
export interface File extends BaseObject{
  size?:number;
  path?:string;
}
export interface MovieEpisode extends BaseObject{
  movie?:Movie;
  episode?:number;
  previousEpisode?:string;
  part?:number;
  links?:MovieLink[];
}
export interface MovieLink extends BaseObject{
  orderNumber?:number;
  embeddedLink?:string;
  file?:File;
}
