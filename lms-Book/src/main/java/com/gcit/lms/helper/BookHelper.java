package com.gcit.lms.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

@Component
public class BookHelper {
    @Autowired
    BookDAO bdao;

    public Integer saveBook( Book book) throws SQLException {
        if (book.getBookId() != null) {
            if(book.getTitle() != null)
                return bdao.updateBook(book);
            if (book.getAuthors() != null && book.getAuthors().size() > 0)
                bdao.saveBookAuthor(book);

            if (book.getGenres() != null && book.getGenres().size() > 0)
                bdao.saveBookGenre(book);
        } else {
            book.setBookId(bdao.saveBookID(book));
            if(book.getAuthors() != null && book.getAuthors().size() > 0)
                bdao.saveBookAuthor(book);
            if(book.getGenres() != null && book.getGenres().size() > 0)
                bdao.saveBookGenre(book);
            return book.getBookId();
        }
        return null;
    }
    public Integer deleteBook(Integer bookId) throws SQLException {
        return bdao.deleteBook(bookId);
    }
    public List<Book> getBooks(String[] bookIds) throws SQLException {
        List<Book> books = new ArrayList<>();
        for (String bookId : bookIds) {
            books.add(new Book(Integer.parseInt(bookId)));
        }
        return books;
    }
    public List<Book> readBooks() throws SQLException {
        return bdao.readAllBooks();
    }
    public List<Book> readBooks(@PathVariable String searchString, @PathVariable Integer pageNo) throws SQLException {
        List<Book> books = bdao.readBooksDAO(searchString, pageNo);
        for(Book b: books){
        		 b.setAuthors(readAuthorsByBook(b.getBookId()));
             b.setGenres(readGenresByBook(b.getBookId()));
             b.setPublisher(readPublisher(b.getPublisher().getPublisherId()));
        }
        return books;
    }
    public Book readBookByPK(@PathVariable Integer bookId) throws SQLException {
        Book book= bdao.readBookByPK(bookId);
        book.setAuthors(readAuthorsByBook(book.getBookId()));
        book.setGenres(readGenresByBook(book.getBookId()));
       book.setPublisher(readPublisher(book.getPublisher().getPublisherId()));
        return book;
    }
    public Integer upadatebookPublisher(Book book) throws SQLException{
        if(book.getBookId()!=null){
            if(book.getPublisher().getPublisherId() != null)
                return bdao.updatebookPub(book);
        }
        return null;
    }
    public List<Book> readAllBooksByAuthor(int authorId) throws SQLException{
    		return bdao.readAllBooksByAuthor(authorId);
    }
    public List<Book> readAllBooksByGenre(int genreId) throws SQLException{
		return bdao.readAllBooksByGenre(genreId);
    }
    public List<Book> readAllBooksByPublisher(int pubId) throws SQLException{
		return bdao.readAllBooksByPublisher(pubId);
    }
    public Book readBooksByCopies(int bookId) throws SQLException{
		return bdao.readBooksByCopies(bookId);
    }
    public Book readAllBooksByCard(int bookId) throws SQLException{
		return bdao.readAllBooksByCard(bookId);
    }
    public void deleteBookAuthor1(int authorId,int bookId) throws SQLException{
         deleteBookAuthor(authorId,bookId);
    }
    public void deleteBookGenre1(int genreId,int bookId) throws SQLException{
        deleteBookGenre(genreId,bookId);
    }
    @SuppressWarnings("unchecked")
  	public  List<Author> readAuthorsByBook(int id)
      {
          final String uri = "http://54.237.148.87:8090/authors/book/"+id+".json";
          
          RestTemplate restTemplate = new RestTemplate();
           
          return (List<Author>)restTemplate.getForObject(uri, Object.class);
          
      }
    @SuppressWarnings("unchecked")
  	public  List<Genre> readGenresByBook(int id)
      {
          final String uri = "http://localhost:8094/genres/book/"+id+".json";
          
          RestTemplate restTemplate = new RestTemplate();
           
          return (List<Genre>)restTemplate.getForObject(uri, Object.class);
          
      }
  	public  Publisher readPublisher(int id)
      {
          final String uri = "http://localhost:8093/publisher/"+id+".json";
          
          RestTemplate restTemplate = new RestTemplate();
          Publisher pub =  restTemplate.getForObject(uri, Publisher.class);
          return pub;
      }
  	public void deleteBookAuthor(int authorId,int bookId)
      {
          final String uri = "http://54.237.148.87:8090/author/"+authorId+"/book/"+bookId+"/delete.json";
          RestTemplate restTemplate = new RestTemplate();
          restTemplate.delete(uri,authorId,bookId); 
      }
	public void deleteBookGenre(int genreId,int bookId)
    {
        final String uri = "http://localhost:8094/genre/"+genreId+"/book/"+bookId+"/delete.json";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri); 
    }
}
