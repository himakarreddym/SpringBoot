package com.gcit.lms.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.helper.Genrehelper;

@RestController
public class GenreController {


    @Autowired
    Genrehelper genreHelper;


    /**
	 * Genre
	 */
	@Transactional
	@RequestMapping(value = "/genre/save", method = RequestMethod.POST, consumes="application/json")
	public Integer saveGenre(@RequestBody Genre genre) throws SQLException{
			return genreHelper.saveGenre(genre);
	}
	@Transactional
	@RequestMapping(value = "/genre/{genreId}/edit", method = RequestMethod.PUT, consumes="application/json")
	public Integer updateGenre(@PathVariable Integer genreId,@RequestBody Genre genre) throws SQLException{
		if(genreId== genre.getGenreId()) {
		return genreHelper.saveGenre(genre);
		}
		return -1;
	}
	
	@Transactional
	@RequestMapping(value = "/genre/{genreId}/addbooks", method = RequestMethod.PUT, consumes="application/json")
	public Integer updateGenrebooks(@PathVariable Integer genreId,@RequestBody Genre genre) throws SQLException{
		if(genreId== genre.getGenreId()) {
		return genreHelper.saveGenre(genre);
		}
		return -1;
	}
	@RequestMapping(value = "/genres", method = RequestMethod.GET, produces="application/json")
	public List<Genre> readGenres() throws SQLException {
		return genreHelper.readGenres();
		} 
	@RequestMapping(value = "/genre/{genreId}", method = RequestMethod.GET, produces="application/json")
	public Genre readGenreByPK(@PathVariable Integer genreId) throws SQLException{
		return genreHelper.readGenreByPK(genreId);
	} 
	
	@Transactional
	@RequestMapping(value = "/genre/{genreId}/delete", method = RequestMethod.DELETE, consumes="application/json")
	public Integer deleteGenre(@PathVariable Integer genreId) throws SQLException{
		return genreHelper.deleteGenre(genreId);
	}
	@RequestMapping(value = "/genres/{searchString}", method = RequestMethod.GET, produces="application/json")
	public List<Genre> readGenre(@PathVariable String searchString,@RequestParam(value= "pageNo", required=false)String pageNo) throws SQLException{
		return genreHelper.readGenre(searchString, pageNo != null ? Integer.parseInt(pageNo):-1);
		} 
	 @RequestMapping(value = "/genres/book/{bookId}", method = RequestMethod.GET, produces="application/json")
	    public List<Genre> readGenresByBook( @PathVariable Integer bookId) throws SQLException{
	        return genreHelper.readGenresByBook(bookId);
	    }
	 @Transactional
		@RequestMapping(value = "/genre/{genreId}/book/{bookId}/delete", method = RequestMethod.DELETE, consumes="application/json")
		public Integer deleteBookGenre(@PathVariable Integer genreId,@PathVariable Integer bookId) throws SQLException{
			return genreHelper.deleteBookGenre(genreId,bookId);
		}

}
