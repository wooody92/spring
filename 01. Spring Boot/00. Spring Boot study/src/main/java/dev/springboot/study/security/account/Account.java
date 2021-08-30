package dev.springboot.study.security.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String username;

    private String password;
}
