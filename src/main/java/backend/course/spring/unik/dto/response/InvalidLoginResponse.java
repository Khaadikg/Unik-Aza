package backend.course.spring.unik.dto.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    String name;

    public InvalidLoginResponse() {
        this.name = "Вы не авторизованы!";
    }
}
