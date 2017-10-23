package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.gcit.lms.entity.Author;
import com.gcit.lms.helper.Authorhelper;
@CrossOrigin
@RestController
public class AuthorController {


    @Autowired
    Authorhelper authorHelper;


    /**
     * Author services
     */

    @Transactional
    @RequestMapping(value = "/author/save", method = RequestMethod.POST, consumes="application/json")
    public Integer saveAuthor(@RequestBody Author author) throws SQLException{
        return authorHelper.saveAuthor(author);
    }
    @Transactional
    @RequestMapping(value = "/author/{authorid}/edit", method = RequestMethod.PUT, consumes="application/json")
    public Integer updateAuthor(@PathVariable Integer authorid,@RequestBody Author author) throws SQLException{
        if(authorid== author.getAuthorId()) {
            return authorHelper.saveAuthor(author);
        }
        return -1;
    }
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/author/{authorid}/addbooks", method = RequestMethod.PUT, consumes="application/json")
    public Integer updateAuthorbooks(@PathVariable Integer authorid,@RequestBody Author author) throws SQLException{
        if(authorid== author.getAuthorId()) {
            return authorHelper.saveAuthor(author);
        }
        return -1;
    }

    @RequestMapping(value = "/authors", method = RequestMethod.GET, produces={"application/json", "application/xml"})
    public List<Author> readAuthors() throws SQLException{
        return authorHelper.readAuthors();
    }
    @RequestMapping(value = "/authors/test", method = RequestMethod.GET, produces={"application/json", "application/xml"})
    public List<Author> readtestAuthors() throws SQLException{
        return authorHelper.readtestAuthors();
    }

    @Transactional
    @RequestMapping(value = "/author/{authorId}/delete", method = RequestMethod.DELETE)
    public Integer deleteAuthor(@PathVariable Integer authorId) throws SQLException{
        return authorHelper.deleteAuthor(authorId);
    }

    @RequestMapping(value = "/author/{authorId}", method = RequestMethod.GET, produces="application/json")
    public Author readAuthorByPK(@PathVariable Integer authorId) throws SQLException{
        return authorHelper.readAuthorByPK(authorId);
    }

    @RequestMapping(value = "/authors/{searchString}", method = RequestMethod.GET, produces="application/json")
    public List<Author> readAuthors( @PathVariable String searchString, @RequestParam(value= "pageNo", required=false)String pageNo) throws SQLException{
        return authorHelper.readAuthors(searchString, pageNo != null ? Integer.parseInt(pageNo):-1);
    }
    
    @RequestMapping(value = "/authors/book/{bookId}", method = RequestMethod.GET, produces="application/json")
    public List<Author> readAuthorsByBook( @PathVariable Integer bookId) throws SQLException{
        return authorHelper.readAuthorsByBook(bookId);
    }
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/author/{authorId}/book/{bookId}/delete", method = RequestMethod.DELETE)
    public Integer deleteBookAuthor(@PathVariable Integer authorId,@PathVariable Integer bookId) throws SQLException{
        return authorHelper.deleteBookAuthor(authorId,bookId);
    }

}
