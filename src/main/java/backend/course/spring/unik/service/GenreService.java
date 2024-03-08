package backend.course.spring.unik.service;

import backend.course.spring.unik.entity.Genre;
import backend.course.spring.unik.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public String createGenre(Genre genre) {
        genreRepository.save(genre);

        return "Жанр успешно создан!";
    }

    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
}
