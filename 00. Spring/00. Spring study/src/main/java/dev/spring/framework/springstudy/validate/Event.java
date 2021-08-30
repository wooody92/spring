package dev.spring.framework.springstudy.validate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    Integer id;

    @NotEmpty
    String title;

    @Min(0)
    Integer limit;

    @Email
    String email;
}
