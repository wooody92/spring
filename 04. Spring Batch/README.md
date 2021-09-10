# Spring Batch

Spring Batch에 대해 정리합니다.



## # 설정

- 로컬 테스트를 위한 설정입니다.

### 사전 준비

- DB 설정

  ```
  docker-compose up -d
  ```

- DB 연결 후 spring-batch 관련 메타 테이블 생성 (쿼리로 직접 생성)

  ```yaml
  # schema는 org.springframework.boot:spring-boot-starter-batch로 부터 받아온 아래 경로에 있다.
  /org/springframework/batch/core/schema-mysql.sql
  ```

  



## References

- jojoldu 블로그 : [https://jojoldu.tistory.com/325?category=902551](https://jojoldu.tistory.com/325?category=902551)

