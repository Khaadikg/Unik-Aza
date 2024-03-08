package backend.course.spring.unik.dto.request;

import backend.course.spring.unik.entity.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilmRequest {
    String name;
    String description;
    double rating;
    String imageUrl;
    String filmLink;
    List<Genre> genres;
}
