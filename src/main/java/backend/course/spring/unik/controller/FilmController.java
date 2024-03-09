package backend.course.spring.unik.controller;

import backend.course.spring.unik.dto.request.FilmRequest;
import backend.course.spring.unik.entity.Film;
import backend.course.spring.unik.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/films")
@RequiredArgsConstructor
@Tag(name = "Film controller", description = "Controller for films")
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/create")
    @Operation(summary = "Admin endpoint", description = "For create new film!")
    public String createFilm(@RequestBody FilmRequest filmRequest) {
        return filmService.createFilm(filmRequest);
    }

    @PostMapping("/upload")
    @Operation(summary = "Admin endpoint", description = "For upload image for film!")
    public String uploadImage(@RequestParam MultipartFile multipartFile, @RequestParam Long id) {
        return filmService.uploadImageById(id, multipartFile);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all films", description = "Endpoint for get all films!")
    public List<Film> getAll() {
        return filmService.getAll();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get film by id", description = "Endpoint for get film by id!")
    public Film film(@PathVariable Long id) {
        return filmService.getFilmById(id);
    }

    @GetMapping("/top")
    @Operation(summary = "For get top films", description = "Endpoint for get top films!")
    public List<Film> topRating() {
        return filmService.getTopRatings();
    }

    @GetMapping("/search")
    @Operation(summary = "Search", description = "Endpoint for search film by (film)name!")
    public List<Film> searchFilmByName(@RequestParam String name) {
        return filmService.searchFilmByName(name);
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter", description = "To filter films by genre")
    public List<Film> filter(@RequestParam String genre) {
        return filmService.filterByGenreName(genre);
    }
}
