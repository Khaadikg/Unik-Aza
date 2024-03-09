package backend.course.spring.unik.controller;

import backend.course.spring.unik.entity.Genre;
import backend.course.spring.unik.service.GenreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
@Tag(name = "Admin controller", description = "For create and get all genres!")
public class GenreController {
    private final GenreService genreService;

    @PostMapping("/create")
    public String createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    @GetMapping
    public List<Genre> getAll() {
        return genreService.getAll();
    }
}
