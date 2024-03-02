import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CategoryComponent} from "./category/category.component";
import {CountryComponent} from "./country/country.component";
import {MovieComponent} from "./movie/movie.component";
import {MovieEpisodeComponent} from "./movie-episode/movie-episode.component";

const routes: Routes = [
  {
    path: "category", component: CategoryComponent, data: {
      breadcrumb: 'breadcrumb.category',
    },
  },
  {
    path: "country", component: CountryComponent, data: {
      breadcrumb: 'breadcrumb.country'
    },
  },
  {
    path: "movie", component: MovieComponent, data: {
      breadcrumb: 'breadcrumb.movie'
    },
  },
  {
    path: "movie-episode", component: MovieEpisodeComponent, data: {
      breadcrumb: 'breadcrumb.movieEpisode'
    },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SettingRoutingModule {
}
