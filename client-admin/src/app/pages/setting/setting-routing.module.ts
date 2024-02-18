import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CategoryComponent} from "./category/category.component";
import {CountryComponent} from "./country/country.component";
import {MovieComponent} from "./movie/movie.component";

const routes: Routes = [
  {
    path: "category", component: CategoryComponent, data: {
      breadcrumb: 'category'
    },
  },
  {
    path: "country", component: CountryComponent, data: {
      breadcrumb: 'country'
    },
  },
  {
    path: "movie", component: MovieComponent, data: {
      breadcrumb: 'movie'
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SettingRoutingModule {
}
