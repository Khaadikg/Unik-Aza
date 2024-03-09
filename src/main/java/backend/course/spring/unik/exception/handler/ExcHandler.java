package backend.course.spring.unik.exception.handler;

import backend.course.spring.unik.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse unauthorizedException(UnauthorizedException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(NotFoundException e) {
        return new ExceptionResponse(HttpStatus.NOT_FOUND, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ExceptionResponse notFoundException(UserAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.FOUND, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(IncorrectDataException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse notFoundException(IncorrectDataException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(TokenExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse notFoundException(TokenExpiredException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(EmptyFileException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse notFoundException(EmptyFileException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }

    @ExceptionHandler(FilmAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse notFoundException(FilmAlreadyExistException e) {
        return new ExceptionResponse(HttpStatus.UNAUTHORIZED, e.getClass().getName(), e.getMessage());
    }
}
