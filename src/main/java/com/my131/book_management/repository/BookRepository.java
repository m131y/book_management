package com.my131.book_management.repository;

import com.my131.book_management.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class BookRepository {
    private final Map<Integer, Book> store = new LinkedHashMap<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    public List<Book> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(store.get(id));
    }

    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(seq.incrementAndGet());
        }

        store.put(book.getId(), book);

        return book;
    }

    public Book update(Integer id, Book updatedBook) {
        if(!store.containsKey(id)) {
            throw new NoSuchElementException(id + "의 책이 없습니다.");
        }

        updatedBook.setId(id);
        store.put(id,updatedBook);

        return updatedBook;
    }

    public void delete(Integer id) {
        store.remove(id);
    }
}
