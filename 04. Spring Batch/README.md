# Spring Batch

Spring Batch 데모 프로젝트 입니다.



## # 환경 설정

- 로컬 테스트를 위한 환경을 구성합니다.

### 1. 사전 준비

- docker-compose로 mysql DB 설정

  ```
  docker-compose up -d
  ```

- DB 연결 후 spring-batch 관련 메타 테이블 생성 (쿼리로 직접 생성)

  ```yaml
  # schema는 org.springframework.boot:spring-boot-starter-batch로 부터 받아온 아래 경로에 있다.
  /org/springframework/batch/core/schema-mysql.sql
  ```

- 샘플 테이블 및 데이터 생성

  ```sql
  create table pay (
    id         bigint not null auto_increment,
    amount     bigint,
    tx_name     varchar(255),
    tx_date_time datetime,
    primary key (id)
  ) engine = InnoDB;
  
  insert into pay (amount, tx_name, tx_date_time) VALUES (1000, 'trade1', '2018-09-10 00:00:00');
  insert into pay (amount, tx_name, tx_date_time) VALUES (2000, 'trade2', '2018-09-10 00:00:00');
  insert into pay (amount, tx_name, tx_date_time) VALUES (3000, 'trade3', '2018-09-10 00:00:00');
  insert into pay (amount, tx_name, tx_date_time) VALUES (4000, 'trade4', '2018-09-10 00:00:00');
  
  create table statistic (
    id         bigint not null auto_increment,
    total_amount     bigint,
    primary key (id)
  ) engine = InnoDB;
  ```

### 2. 환경 변수 설정

- spring.batch.job.name 설정으로 실행시키고자 하는 job의 환경변수를 입력해줘야한다.

  ```yaml
  spring:
    batch:
      job:
        names: ${job.name:NONE}
  ```

  ```
  # 실제 운영 환경에서는 아래와 같이 원하는 batch job을 실행한다.
  $ java -jar batch-application.jar --job.name=processorConvertJob
  ```

  <img width="982" alt="job-name" src="https://user-images.githubusercontent.com/58318041/132943358-88045975-68d1-4baa-9c3f-47e60c42e3ce.png">



## References

- jojoldu 블로그 : [https://jojoldu.tistory.com/325?category=902551](https://jojoldu.tistory.com/325?category=902551)
- oh-yes 블로그 : [https://stylishc.tistory.com/125](https://stylishc.tistory.com/125)

