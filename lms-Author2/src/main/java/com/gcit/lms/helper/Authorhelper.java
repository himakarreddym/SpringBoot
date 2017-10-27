package com.gcit.lms.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Authorhelper {
    @Autowired
    AuthorDAO adao;

    public Integer saveAuthor(Author author) throws SQLException{

        if(author.getAuthorId()!=null){
            if(author.getAuthorName() != null) {
            	if(author.getBooks() != null && author.getBooks().size() > 0) {
                    adao.saveBookAuthor(author);
            	}
                return adao.updateAuthor(author);
            }
        }else{
            author.setAuthorId(adao.saveAuthorWithID(author));
            if(author.getBooks() != null && author.getBooks().size() > 0)
                adao.saveBookAuthor(author);
            return author.getAuthorId();
        }
        return null;
    }

    public Integer deleteAuthor(Integer id) throws SQLException{
        return adao.deleteAuthor(id);
    }
    public Integer deleteBookAuthor(Integer authorId, Integer bookId) throws SQLException{
        return adao.deleteBookAuthor(authorId,bookId);
    }

    public Author readAuthorByPK(Integer authorId) throws SQLException{
        Author author= adao.readAuthorByPK(authorId);
        author.setBooks(readAllBooksByAuthor(authorId));
        return author;
    }

    public List<Author> getAuthors(String[] authorIds) throws SQLException
    {
    	
        List<Author> authors = new ArrayList<>();
        for(String authorId: authorIds)
        {
            authors.add(new Author(Integer.parseInt(authorId)));
        }
        return authors;
    }
    public List<Author> readAuthors() throws SQLException{
    		List<Author> authors = adao.readAllAuthors();
    	for(Author a: authors){
            a.setBooks(readAllBooksByAuthor(a.getAuthorId()));
        }
        return authors;    
        
    }
    public List<Author> readtestAuthors() throws SQLException{
		List<Author> authors = adao.readAllAuthors();
    return authors;    
    
}
    public List<Author> readAuthors( String searchString, Integer pageNo) throws SQLException{
        List<Author> authors = adao.readAuthors(searchString, pageNo);
        for(Author a: authors){
            a.setBooks(readAllBooksByAuthor(a.getAuthorId()));
        }
        return authors;
    }
    public List<Author> readAuthorsByBook(int bookId) throws SQLException{
		return adao.readAuthorsByBook(bookId);
    }
    @SuppressWarnings("unchecked")
	public  List<Book> readAllBooksByAuthor(int id)
    {
        final String uri = "http://54.237.148.87:8091/books/author/"+id+".json";
        
        RestTemplate restTemplate = new RestTemplate();
         
        return (List<Book>)restTemplate.getForObject(uri, Object.class);
        
    }
}
