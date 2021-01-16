package com.ysh.projectY.service;

import com.ysh.projectY.dao.BookDao;
import com.ysh.projectY.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public List<Book> findAll() {
        return bookDao.findAll();
    }

    public Optional<Book> findById(Integer id) {
        return bookDao.findById(id);
    }

    public void deleteById(Integer id) {
        bookDao.deleteById(id);
    }

    public void deleteAll() {
        bookDao.deleteAll();
    }

    public Book save(Book book) {
        return bookDao.saveAndFlush(book);
    }

    public Page<Book> getBookByPage(Pageable pageable) {
        return bookDao.findAll(pageable);
    }

    public List<Book> getBooksByAuthorStartingWith(String author) {
        return bookDao.getBooksByAuthorStartingWith(author);
    }

    public List<Book> getBooksByPriceGreaterThan(Float price) {
        return bookDao.getBooksByPriceGreaterThan(price);
    }

    public Book getMaxIdBook() {
        return bookDao.getMaxIdBook();
    }

    public List<Book> getBookByIdAndAuthor(String author, Integer id) {
        return bookDao.getBookByIdAndAuthor(author, id);
    }

    public List<Book> getBooksByIdAndName(String name, Integer id) {
        return bookDao.getBooksByIdAndName(name, id);
    }

}
