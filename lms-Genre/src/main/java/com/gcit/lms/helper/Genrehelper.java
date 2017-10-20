package com.gcit.lms.helper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
@Component
public class Genrehelper {
	@Autowired
	GenreDAO gdao;
	
	public Integer saveGenre( Genre genre) throws SQLException{
		if(genre.getGenreId()!=null){
			if(genre.getGenreName() != null)
			return gdao.updateGenre(genre);
			if(genre.getBooks() != null && genre.getBooks().size() > 0)
			gdao.saveBookGenre(genre);
		}else{
			genre.setGenreId(gdao.saveGenreWithID(genre));
			if(genre.getBooks() != null && genre.getBooks().size() > 0)
			gdao.saveBookGenre(genre);
			return genre.getGenreId();
		}
		return null;
	}
	
	public Integer deleteGenre(Integer genreId) throws SQLException{
		return gdao.deleteGenre(genreId);
	}
	public Integer deleteBookGenre(Integer genreId,Integer bookId) throws SQLException{
		return gdao.deleteBookGenre(genreId,bookId);
	}
	
	public List<Genre> getGenres(String[] genreIds) throws SQLException
	{
			List<Genre> genres = new ArrayList<>();
			for(String genreId: genreIds)
			{
				genres.add(new Genre(Integer.parseInt(genreId)));
			}
			return genres;
	}
	public List<Genre> readGenres() throws SQLException
	{
	List<Genre> genres = gdao.readGenres(null);
	for(Genre g : genres) {
		g.setBooks(readAllBooksByGenre(g.getGenreId()));
		}
	return genres;
	} 
	public Genre readGenreByPK( Integer genreId) throws SQLException{
		Genre genre= gdao.readGenreByPK(genreId);
		genre.setBooks(readAllBooksByGenre(genre.getGenreId()));
		return genre;
	} 
	public List<Genre> readGenre( String searchString,Integer pageNo) throws SQLException{
		List<Genre> genres = gdao.readGenres(searchString,pageNo);
		for(Genre g : genres) {
			g.setBooks(readAllBooksByGenre(g.getGenreId()));
		}
		return genres;
	} 
	 public List<Genre> readGenresByBook(int bookId) throws SQLException{
			return gdao.readGenresByBook(bookId);
	    }
	 @SuppressWarnings("unchecked")
		public  List<Book> readAllBooksByGenre(int id)
	    {
	        final String uri = "http://localhost:8091/books/genre/"+id+".json";
	        
	        RestTemplate restTemplate = new RestTemplate();
	         
	        return (List<Book>)restTemplate.getForObject(uri, Object.class);
	        
	    }

}
