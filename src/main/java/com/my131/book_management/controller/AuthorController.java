package com.my131.book_management.controller;

import com.my131.book_management.dto.AuthorDto;
import com.my131.book_management.model.Author;
import com.my131.book_management.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {
    //비즈니스 로직을 위임할 AuthorService 객체
    private final AuthorService authorService;

    //ResponseEntity로 받을 수도 있음
//    @GetMapping
//    public ResponseEntity<List<Author>> list() {
//        List<Author> authors = authorService.getAll();
//
//        return ResponseEntity.ok(authors);
//    }

    @GetMapping
    //전체 Author 리스트를 JSON으로 반환(반환 타입은 list<Author>이나 Spring이 자동으로 JSON으로 직렬화)
    public List<Author> list() {
        return authorService.getAll();
    }

    //ResponseEntity로 받을 수도 있음
//    @GetMapping("/{id}")
//    public ResponseEntity<Author> get(@PathVariable Integer id) {
//        Author author = authorService.getById(id);
//
//        return ResponseEntity.ok(author);
//    }

    //@PathVariable: URL 경로에서 id를 추출
    @GetMapping("/{id}")
    public Author get(@PathVariable Integer id) {
        return authorService.getById(id);
    }

    //ResponseEntity로 받을 수도 있음
//    @PostMapping
//    public ResponseEntity<Author> create(@Valid @RequestBody AuthorDto authorDto) {
//        Author author = new Author();
//        author.setName(authorDto.getName());
//
//        Author saved = authorService.create(author);
//
//        return ResponseEntity.created(URI.create("/api/authors/" + saved.getId())).body(saved);
//    }

    @PostMapping
    //AuthorDto를 JSON으로 받고, 유효성 검사(@Valid) 수행
    public Author create(@Valid @RequestBody AuthorDto authorDto) {
        Author author = new Author();
        //author객체의 name을 요청받은 authordto의 name으로 설정
        author.setName(authorDto.getName());
        //Author 객체 저장 후 저장된 Author 객체 반환
        return authorService.create(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(
            @PathVariable Integer id,
            @Valid @RequestBody AuthorDto authorDto
    ) {
        Author author = new Author();
        author.setName(authorDto.getName());

        Author updated = authorService.update(id, author);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    //Response로 이루어져있다?
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        authorService.delete(id);

        //ResponseEntity = 응답전문객체
        return ResponseEntity.noContent().build();
    }
}
