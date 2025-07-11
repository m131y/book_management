package com.my131.book_management.repository;

import com.my131.book_management.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookRepository {
    //Book 객체를 저장할 메모리 기반의 저장소. LinkedHashMap을 사용함으로써 삽입 순서 유지
    private final Map<Integer, Book> store = new LinkedHashMap<>();
    //ID 생성을 위한 카운터. 멀티스레드 환경에서도 안전하게 증가하는 ID를 만들 수 있게 도와주는 객체
    private final AtomicInteger seq = new AtomicInteger(0);

    //store Map의 값을 list<Book>로 반환시킴,
    //저장된 모든 책들을 list 형태로 제공
    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }


    //주어진 id(key)에 해당하는 Value(Book)를 찾아 Optional<Author>로 감싸 반환
    public Optional<Book> findById(Integer id) {
        //store에서 id로 값을 조회
        //ofNullable : 값이 null이면 Optional.empty()를 반환, 값이 존재하면 Optional.of(value)로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    //새 Book 객체를 저장하거나 기존 Book의 id를 재사용하여 저장
    //book 객체에 id 가 없을 시) 생성 : seq로 id를 생성해 설정
    //book 객체에 id 가 있을 시) 수정 : id에 book 정보 덮어씌우기
    public Book save(Book book) {
        //전달된 book 객체에 ID가 없다면, 새 ID를 만들어 설정
        if (book.getId() == null) {
            //incrementAndGet()은 AtomicInteger의 메서드
            book.setId(seq.incrementAndGet());
        }
        //Map store에 ID를 키로 하여 book을 저장
        store.put(book.getId(), book);
        //저장된 book 객체를 반환
        return book;
    }

//    public Book update(Integer id, Book updatedBook) {
//        if(!store.containsKey(id)) {
//            throw new NoSuchElementException(id + "의 책이 없습니다.");
//        }
//
//        updatedBook.setId(id);
//        store.put(id,updatedBook);
//
//        return updatedBook;
//    }
    //지정된 ID에 해당하는 Book 객체를 저장소에서 삭제, 삭제는 반환X(void)
    public void delete(Integer id) {
        store.remove(id);
    }
}
