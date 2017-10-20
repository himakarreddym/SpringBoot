package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Book;
import com.gcit.lms.helper.BookHelper;
@CrossOrigin
@RestController
public class BookController {


    @Autowired
    BookHelper bookHelper;


    /**
     * Book services
     */
    @Transactional
    @RequestMapping(value = "/book/save", method = RequestMethod.POST, consumes="application/json")
    public Integer saveBook(@RequestBody Book book) throws SQLException {
        return bookHelper.saveBook(book);
    }
    @Transactional
    @RequestMapping(value = "/book/{bookId}/edit", method = RequestMethod.PUT, consumes="application/json")
    public Integer updateBook(@PathVariable Integer bookId,@RequestBody Book book) throws SQLException{
        return bookHelper.saveBook(book);
    }
    @Transactional
    @RequestMapping(value = "/book/{bookId}/addauthors", method = RequestMethod.PUT, consumes="application/json")
    public Integer updatebookAuthors(@PathVariable Integer bookId,@RequestBody Book book) throws SQLException{
        if(bookId== book.getBookId()) {
            return bookHelper.saveBook(book);
        }
        return -1;
    }
    @Transactional
    @RequestMapping(value = "/book/{bookId}/addgenres", method = RequestMethod.PUT, consumes="application/json")
    public Integer updatebookGenres(@PathVariable Integer bookId,@RequestBody Book book) throws SQLException{
        if(bookId== book.getBookId()) {
            return bookHelper.saveBook(book);
        }
        return -1;
    }
    @Transactional
    @RequestMapping(value = "/book/{bookId}/addpublisher", method = RequestMethod.PUT, consumes="application/json")
    public Integer upadatebookPublisher(@PathVariable Integer bookId,@RequestBody Book book) throws SQLException{
        if(bookId== book.getBookId()) {
            return bookHelper.upadatebookPublisher(book);
        }
        return -1;
    }
    @Transactional
    @RequestMapping(value = "/book/{bookId}/delete", method = RequestMethod.DELETE, consumes="application/json")
    public Integer deleteBook(@PathVariable Integer bookId) throws SQLException {
        return bookHelper.deleteBook(bookId);
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET, produces="application/json")
    public List<Book> readBooks() throws SQLException {
        return bookHelper.readBooks();
    }
    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.GET, produces="application/json")
    public Book readBookByPK(@PathVariable Integer bookId) throws SQLException {
        return bookHelper.readBookByPK(bookId);
    }
    @RequestMapping(value = "/books/{searchString}", method = RequestMethod.GET, produces="application/json")
    public List<Book> readBooks(@PathVariable String searchString, @RequestParam(value= "pageNo", required=false)String pageNo) throws SQLException {
        return bookHelper.readBooks(searchString, pageNo != null ? Integer.parseInt(pageNo):-1);
    }
    @RequestMapping(value = "/books/author/{authorId}", method = RequestMethod.GET, produces="application/json")
    public List<Book> readAllBooksByAuthor(@PathVariable Integer authorId) throws SQLException {
        return bookHelper.readAllBooksByAuthor(authorId);
    }
    @RequestMapping(value = "/books/genre/{genreId}", method = RequestMethod.GET, produces="application/json")
    public List<Book> readAllBooksByGenre(@PathVariable Integer genreId) throws SQLException {
        return bookHelper.readAllBooksByGenre(genreId);
    }
    @RequestMapping(value = "/books/publisher/{pubId}", method = RequestMethod.GET, produces="application/json")
    public List<Book> readAllBooksByPublisher(@PathVariable Integer pubId) throws SQLException {
        return bookHelper.readAllBooksByPublisher(pubId);
    }
    @RequestMapping(value = "/books/bookcopies/{bookId}", method = RequestMethod.GET, produces="application/json")
    public Book readBooksByCopies(@PathVariable Integer bookId) throws SQLException {
        return bookHelper.readBooksByCopies(bookId);
    }
    @RequestMapping(value = "/books/bookloans/{bookId}", method = RequestMethod.GET, produces="application/json")
    public Book readAllBooksByCard(@PathVariable Integer bookId) throws SQLException {
        return bookHelper.readAllBooksByCard(bookId);
    }
    @Transactional
    @RequestMapping(value = "/book/{bookId}/author/{authorId}/delete", method = RequestMethod.DELETE, produces="application/json")
    public void deleteBookAuthor(@PathVariable int authorId,@PathVariable int bookId) throws SQLException{
         bookHelper.deleteBookAuthor1(authorId, bookId);
    }
    @Transactional
    @RequestMapping(value = "genre/{genreId}/book/{bookId}/delete", method = RequestMethod.DELETE, produces="application/json")
    public void deleteBookGenre(@PathVariable int genreId,@PathVariable int bookId) throws SQLException{
         bookHelper.deleteBookGenre1(genreId, bookId);
    }


}
