package com.my131.book_management.controller;

import com.my131.book_management.model.Author;
import com.my131.book_management.service.AuthorService;
import com.my131.book_management.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DashboardController {
    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping("/api/dashboard/authors")
    public Map<Author, Long> countByAuthor() {
        //authorService.getAll()의 반환값은 List<Author>, Map<Author,Long>으로 바꿔야함
        return authorService.getAll().stream()
                .collect(
                        //Map으로 바꾸겠다
                        Collectors.toMap(
                                //Key 값을 author객체의 author로 지정,
                                author -> author,
                                //Value 값을 author객체에서 long으로 지정하기위한 여정..
                                //bookService.getAll()의 반환값은 List<Book>
                                //author.getId()를 써서 필터 조건을 만들고 싶으니까,
                                //value를 만들 때 author ->라는 형태로 author를 입력받는 함수를 씀
                                author -> bookService.getAll().stream()
                                        //author의 AuthorID와 동일한 AuthorId을 값는 book만 필터링
                                        .filter(book -> book.getAuthorId().equals(author.getId()))
                                        //필터링한 book의 수를 셈 -> long
                                        .count()
                        )
                );
    }
}
