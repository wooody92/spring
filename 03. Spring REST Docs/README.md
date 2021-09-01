# Spring REST Docs

Spring REST Docs 데모 프로젝트



## Note

- jar 실행 후 `http://localhost:8080/docs/index.html` 접속

  ```
  ./gradlew build
  java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
  ```

- 아래 설정으로 인해 터미널 환경으로 실행 할 때만 html5 파일이 `/build/asciidoc/html5` 경로에 생성된다.

  - IDE로 실행 시 생성되지 않음

  ```
  bootJar {
      dependsOn asciidoctor
      from("${asciidoctor.outputDir}/html5") {
          into 'static/docs'
      }
  }
  ```

- asciidoctor plugin 경우 gradlew version 7.x.x 에서 호환 에러 발생하여 6.x.x 다운그레이드 진행

  ```
  ./gradlew --version
  ./gradlew wrapper --gradle-version 6.4.1
  ```

  

## References

- wan 블로그 : [https://wan-blog.tistory.com/73](https://wan-blog.tistory.com/73)
- 우아한 형제들 블로그 : [https://techblog.woowahan.com/2597](https://techblog.woowahan.com/2597/)

