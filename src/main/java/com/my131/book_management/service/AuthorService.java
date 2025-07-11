package com.my131.book_management.service;

import com.my131.book_management.model.Author;
import com.my131.book_management.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorService {
    //AuthorRepository를 사용해서 저장소에 접근
    private final AuthorRepository authorRepository;

    //모든 Author 객체를 저장소로부터 가져옴
    //저장된 저자들을 List<Author> 형태로 반환
    public List<Author> getAll() { return authorRepository.findAll(); }

    //ID로 Author를 조회
    public Author getById(Integer id) {
        //Optional 처리 방식 (orElseThrow)으로 NPE 방지
        //없을 경우 NoSuchElementException을 던짐
        return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("저자 없음"));
    }

    //새로운 Author 객체를 저장소에 저장
    //저장된 Author 객체를 반환
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    //지정된 ID의 Author 정보를 수정함
    //수정 후 갱신된 Author 객체 반환
    public Author update(Integer id, Author updatedAuthor) {
        return authorRepository.update(id, updatedAuthor);
    }

    //지정된 ID에 해당하는 Author 데이터를 삭제
    public void delete(Integer id) {
        authorRepository.delete(id);
    }

}
