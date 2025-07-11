package com.my131.book_management.repository;

import com.my131.book_management.model.Author;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AuthorRepository {
    private final Map<Integer, Author> store = new LinkedHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    //store Map의 값을 list<Author>로 반환시킴,
    //저장된 모든 저자들을 list 형태로 제공
    public List<Author> findAll() {
        return new ArrayList<>(store.values());
    }
    //주어진 id에 해당하는 Author를 찾아 Optional<Author>로 감싸 반환
    public Optional<Author> findById(Integer id) {
        //store에서 id로 값을 조회
        //ofNullable : 값이 null이면 Optional.empty()를 반환, 값이 존재하면 Optional.of(value)로 감쌈
        return Optional.ofNullable(store.get(id));
    }

    //새 Author 객체를 저장하거나 기존 Author의 id를 재사용하여 저장
    public Author save(Author author) {
        //전달된 author 객체에 ID가 없다면, 새 ID를 만들어 설정
        if (author.getId() == null) {
            //incrementAndGet()은 AtomicInteger의 메서드
            author.setId(seq.incrementAndGet());
        }
        //Map store에 ID를 키로 하여 author를 저장
        store.put(author.getId(), author);
        //저장된 author 객체를 반환
        return author;
    }

//    //지정된 ID에 해당하는 Author 객체를 updatedAuthor의 정보로 업데이트
//    public Author update(Integer id, Author updatedAuthor) {
//        //해당 ID가 존재하지 않으면 NoSuchElementException으로 예외 발생
//        if(!store.containsKey(id)) {
//            throw new NoSuchElementException(id + "의 저자가 없습니다.");
//        }
//        //업데이트할 객체에 기존 ID를 설정합니다. (중복 방지)
//        updatedAuthor.setId(id);
//        //기존 ID 위치에 새로운 author 객체를 덮어씌움.
//        store.put(id, updatedAuthor);
//        //업데이트된 author 객체를 반환
//        return updatedAuthor;
//    }
    //지정된 ID에 해당하는 Author 객체를 저장소에서 삭제, 삭제는 반환X(void)
    public void delete(Integer id) {
        //Map store에서 해당 ID를 제거
        store.remove(id);
    }
}
