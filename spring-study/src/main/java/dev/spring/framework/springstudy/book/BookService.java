package dev.spring.framework.springstudy.book;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book save(Book bookInput) {
        Book book = new Book();
        return bookRepository.save(book);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("===============");
        System.out.println("Hello");
    }
}
