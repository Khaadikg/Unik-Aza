package backend.course.spring.unik.service;

import backend.course.spring.unik.dto.request.FilmRequest;
import backend.course.spring.unik.entity.Film;
import backend.course.spring.unik.exception.NotFoundException;
import backend.course.spring.unik.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final ImageUploadService service;

    public String createFilm(FilmRequest filmRequest) {
        Film film = Film.builder()
                .name(filmRequest.getName())
                .filmLink(filmRequest.getFilmLink())
                .description(filmRequest.getDescription())
                .genres(filmRequest.getGenres())
                .rating(filmRequest.getRating())
                .build();
        filmRepository.save(film);

        return "Фильм успешно добавлен!";
    }

    public String uploadImageById(Long id, MultipartFile multipartFile) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Фильм не найден!"));

        film.setImageUrl(service.saveImage(multipartFile));
        filmRepository.save(film);

        return "Изображение успешно добавлено!";
    }

    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new NotFoundException("Фильм не найден!"));
    }

    public List<Film> getTopRatings() {
        return filmRepository.getTopRating();
    }

    public List<Film> searchFilmByName(String name) {
        return filmRepository.searchFilmByName(name);
    }

    public List<Film> filterByGenreName(String genre) {
        return filmRepository.filterByGenre(genre);
    }

}
