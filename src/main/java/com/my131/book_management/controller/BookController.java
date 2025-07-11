package com.my131.book_management.controller;

import com.my131.book_management.dto.BookDto;
import com.my131.book_management.model.Book;
import com.my131.book_management.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    //비즈니스 로직을 실행할 BookService 객체
    private final BookService bookService;

    @GetMapping
    //전체 Book List를 JSON으로 반환(반환 타입은 list<Book>이나 Spring이 자동으로 JSON으로 직렬화)
    public List<Book> list() {
        return bookService.getAll();
    }

    //@PathVariable: URL 경로에서 id를 추출
    //id를 이용해 특정 Book 객체 조회
    @GetMapping("/{id}")
    public Book get(@PathVariable Integer id) {
        return bookService.getById(id);
    }

    //저장
    @PostMapping
    //AuthorDto를 JSON으로 받고, 유효성 검사(@Valid) 수행
    public Book create(@Valid @RequestBody BookDto bookDto) {
        Book book = new Book();
        //Book 객체의 데이터를 요청받은 BookDto의 데이터로 설정.
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthorId(bookDto.getAuthorId());
        //Book 객체 저장 후 저장된 Book 객체 반환
        return bookService.create(book);
    }

    //수정
    @PutMapping("/{id}")
    public Book update(
            @PathVariable Integer id,
            @Valid @RequestBody BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthorId(bookDto.getAuthorId());

        return bookService.update(id, book);
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
