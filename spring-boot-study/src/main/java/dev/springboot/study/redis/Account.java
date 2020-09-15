package dev.springboot.study.redis;

import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("accounts")
@Getter
@Setter
public class Account {

    @Id
    private String id;

    private String username;

    private String email;
}
