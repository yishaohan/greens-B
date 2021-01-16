package com.ysh.projectY.controller;

import com.ysh.projectY.entity.Book;
import com.ysh.projectY.utils.JsonResponse;
import com.ysh.projectY.service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * rest 风格 api
 * <p>
 * GET     /books        所有书单
 * GET     /books/{id}   获取一条书单
 * POST    /books        新建一条书单
 * PUT     /books/{id}   更新一条书单，提供全部信息
 * PATCH   /books/{id}   更新一条书单，提供部分信息
 * DELETE  /books/{id}   删除一条书单
 * DELETE  /books        删除所有书单
 *
 * @author YCZ
 * @version 1.0
 * @date 2021-01-05 21:59
 */
@RestController
@RequestMapping(path = "${projectY.api-base-path}")
public class BookController {

    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 获取所有书单
     * GET     /books        所有书单
     *
     * @return http 响应
     */
    @GetMapping("/books")
    public HttpEntity<?> books() {
        List<Book> books = bookService.findAll();
        if (books == null || books.isEmpty()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "指定图书不存在", null), HttpStatus.OK);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.valueOf("application/json;charset=utf-8"));
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "获取所有图书", books, ""), responseHeaders, HttpStatus.OK);
    }

    /**
     * 获取一个书单 * GET     /books/{id}   获取一条书单 * * @param id id
     *
     * @return http 响应
     */
    @GetMapping("/books/{id}")
    public HttpEntity<?> booksOne(@PathVariable Integer id) {
//        return new ResponseEntity<>(bookService.findById(id).get(), HttpStatus.OK);
        final Optional<Book> optional = bookService.findById(id);
        if (optional.isEmpty()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "指定图书不存在", null), HttpStatus.OK);
        }
        Book book = optional.get();
        return new ResponseEntity<>(JsonResponse.success(HttpStatus.OK.value(), "获取指定图书", book, ""), HttpStatus.OK);
    }

    /**
     * 添加一个书单
     * POST    /books        新建一条书单
     *
     * @param book 书单
     * @return http 响应
     */
    @PostMapping("/books")
    public HttpEntity<?> booksAdd(@RequestBody Book book) {
        book.setId(null);
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    /**
     * 更新一个书单,提供一个书单的全部信息
     * PUT     /books/{id}   更新一条书单，提供全部信息
     *
     * @param id   更新的id
     * @param book 更新后的书单
     * @return http 响应
     */
    @PutMapping("/books/{id}")
    public HttpEntity<?> booksPut(@PathVariable Integer id, @Valid @RequestBody Book book) {
        Optional<Book> exits = bookService.findById(id);
        if (exits.isEmpty()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "指定图书不存在", null), HttpStatus.OK);
        }
        Book b = exits.get();
        book.setId(b.getId());
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    /**
     * 更新一个书单,提供一个书单的部分信息
     * PATCH   /books/{id}   更新一条书单，提供部分信息
     *
     * @param id   更新的id
     * @param book 更新后的书单
     * @return http 响应
     */
    @PatchMapping("/books/{id}")
    public HttpEntity<?> booksPatch(@PathVariable Integer id, @RequestBody Book book) {
        Optional<Book> b = bookService.findById(id);
        if (b.isEmpty()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "指定图书不存在", null), HttpStatus.OK);
        }
        Book exist = b.get();
        BeanWrapper beanWrapper = new BeanWrapperImpl(book);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        List<String> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (beanWrapper.getPropertyValue(pd.getName()) == null) {
                nullPropertyNames.add(pd.getName());
            }
        }
        BeanUtils.copyProperties(book, exist, nullPropertyNames.toArray(new String[0]));
        return new ResponseEntity<>(bookService.save(exist), HttpStatus.OK);
    }

    /**
     * 删除一个书单
     * DELETE  /books/{id}   删除一条书单
     *
     * @param id id
     * @return http 响应
     */
    @DeleteMapping("/books/{id}")
    public HttpEntity<?> booksDeleteOne(@PathVariable Integer id) {
        Optional<Book> b = bookService.findById(id);
        if (b.isEmpty()) {
            return new ResponseEntity<>(JsonResponse.failure(HttpStatus.NOT_FOUND.value(), "指定图书不存在", null), HttpStatus.OK);
        }
        Book exist = b.get();
        bookService.deleteById(exist.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 删除所有书单
     * DELETE  /books        删除所有书单
     *
     * @return http 响应
     */
    @DeleteMapping("/books")
    public HttpEntity<?> booksDeleteAll() {
//        List<Book> books = bookService.findAll();
        bookService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // //////////////////////////////////////////////////////////////////////////////
    @GetMapping("/findAll")
    public List<Book> findAll() {
        PageRequest pageable = PageRequest.of(1, 3);
        Page<Book> page = bookService.getBookByPage(pageable);
        System.out.println("总页数: " + page.getTotalPages());
        System.out.println("总记录数: " + page.getTotalElements());
        System.out.println("查询结果: " + page.getContent());
        System.out.println("当前页数: " + (page.getNumber() + 1));
        System.out.println("当前页记录数: " + page.getNumberOfElements());
        System.out.println("每页记录数: " + page.getSize());
        return page.getContent();
    }

    @GetMapping("/search")
    public Book search() {
        List<Book> bs1 = bookService.getBookByIdAndAuthor("鲁迅", 7);
        List<Book> bs2 = bookService.getBooksByAuthorStartingWith("吴");
        List<Book> bs3 = bookService.getBooksByIdAndName("西", 8);
        List<Book> bs4 = bookService.getBooksByPriceGreaterThan(30F);
        Book b = bookService.getMaxIdBook();
        System.out.println("bs1: " + bs1);
        System.out.println("bs2: " + bs2);
        System.out.println("bs3: " + bs3);
        System.out.println("bs4: " + bs4);
        System.out.println("b: " + b);
        return b;
    }

    @GetMapping("/save")
    public void save() {
        Book book = new Book();
        book.setAuthor("鲁迅");
        book.setName("呐喊");
        book.setPrice(23F);
        bookService.save(book);
    }
}
