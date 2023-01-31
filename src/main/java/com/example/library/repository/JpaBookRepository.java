package com.example.library.repository;

import com.example.library.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaBookRepository implements BookRepository {

    private final EntityManager em;

    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(Long id) {
        Book book = em.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return em.createQuery("select b from Book b where b.title = :title", Book.class)
                .setParameter("title", title)
                .getResultList();
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    public List<Book> deleteBookById(Long id) {
        return em.createQuery("delete from Book b where b.id = :id", Book.class)
                .setParameter("id", id)
                .getResultList();
    }


    @Override
    public List<Book> getFixByTitle(Book book) {
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

}
