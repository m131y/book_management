package com.my131.book_management.service;

import com.my131.book_management.model.Book;
import com.my131.book_management.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {
    //BookRepository를 사용해서 저장소에 접근
    private final BookRepository bookRepository;

    //모든 Book 객체를 저장소로부터 가져옴
    //저장된 책들을 List<Book> 형태로 반환
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    //ID로 Book을 조회
    public Book getById(Integer id) {
        //Optional 처리 방식 (orElseThrow)으로 NPE 방지
        //없을 경우 NoSuchElementException을 던짐
        return bookRepository.findById(id).orElseThrow(()->new NoSuchElementException("책 없음"));
    }

    //새로운 Book 객체를 저장소에 저장
    //저장된 Book 객체를 반환
    public Book create(Book book) {
        return bookRepository.save(book);
    }

//    public Book update(Integer id, Book upatedBook) {
//        return bookRepository.update(id, upatedBook);
//    }

    //지정된 ID의 Book 정보를 수정함
    //수정 후 갱신된 Book 객체 반환
    public Book update(Integer id, Book updatedBook) {
        //오류 없이 실행되면 해당 id의 book 객체가 있다는 뜻
        getById(id);
        //수정 정보를 담은 Book객체에 수정을 원하는 책의 id를 설정해 저장시킴
        updatedBook.setId(id);

        return bookRepository.save(updatedBook);
    }

    //지정된 ID에 해당하는 Book 데이터를 삭제
    public void delete(Integer id) {
        bookRepository.delete(id);
    }
}
