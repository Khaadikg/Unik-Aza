package backend.course.spring.unik.controller;

import backend.course.spring.unik.dto.request.FilmRequest;
import backend.course.spring.unik.entity.Film;
import backend.course.spring.unik.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/create")
    public String createFilm(@RequestBody FilmRequest filmRequest) {
        return filmService.createFilm(filmRequest);
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam MultipartFile multipartFile, @RequestParam @PathVariable Long id) {
        return filmService.uploadImageById(id, multipartFile);
    }

    @GetMapping("/all")
    public List<Film> getAll() {
        return filmService.getAll();
    }

    @GetMapping("get/{id}")
    public Film film(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/top")
    public List<Film> topRating() {
        return filmService.getTopRatings();
    }

    @GetMapping("/search")
    public List<Film> searchFilmByName(@RequestParam String name) {
        return filmService.searchFilmByName(name);
    }

    @GetMapping("/filter")
    public List<Film> filter(@RequestParam String genre) {
        return filmService.filterByGenreName(genre);
    }
}
