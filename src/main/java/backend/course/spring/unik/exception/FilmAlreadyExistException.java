package backend.course.spring.unik.exception;

public class FilmAlreadyExistException extends RuntimeException{
    public FilmAlreadyExistException(String message) {
        super(message);
    }
}
