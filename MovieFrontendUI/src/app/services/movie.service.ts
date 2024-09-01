import { computed, Injectable, Signal, signal } from '@angular/core';
import { Movie } from '../models/movie';
import { HttpService } from './http.service';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root',
})
export class MovieService {
  movies = signal<Movie[]>([]);
  imdbId = signal<string>('');
  constructor(
    private sanitizer: DomSanitizer,
    private httpService: HttpService
  ) {}

  getVideoId(url: string) {
    return url.substring(url.indexOf('?v=') + 3);
  }

  embedTrailerLink = computed(() => {
    if (this.selectedmovie()) {
      const videoId = this.getVideoId(this.selectedmovie()!?.trailerLink);

      return this.sanitizer.bypassSecurityTrustResourceUrl(
        `https://www.youtube.com/embed/${videoId}?autoplay=1&controls=0&showinfo=0`
      );
    }
    return this.sanitizer.bypassSecurityTrustResourceUrl('');
  });

  getMovies() {
    this.httpService.getMovies().subscribe((movies: any) => {
      this.movies.set(movies);
    });
  }
  selectedmovie: Signal<Movie | null> = computed(() => {
    const selectedMovie = this.movies().find(
      (movie) => movie.imdbId === this.imdbId()
    );
    if (selectedMovie) {
      return selectedMovie;
    }
    return null;
  });
}
