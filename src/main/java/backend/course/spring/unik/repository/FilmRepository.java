package backend.course.spring.unik.repository;

import backend.course.spring.unik.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Optional<Film> findByName(String name);
    @Query(value = "SELECT * FROM films ORDER BY rating DESC", nativeQuery = true)
    List<Film> getTopRating();

    @Query(value = "SELECT * FROM films a WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', ?1,'%')) ", nativeQuery = true)
    List<Film> searchFilmByName(String name);

    @Query(value = "SELECT f.* FROM films f JOIN films_genres fg ON f.id = fg.film_id " +
                                         "JOIN genres g ON fg.genres_id = g.id " +
                                         "WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', ?1, '%'))", nativeQuery = true)
    List<Film> filterByGenre(String name);
}
