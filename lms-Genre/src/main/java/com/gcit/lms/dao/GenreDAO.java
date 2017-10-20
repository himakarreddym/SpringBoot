package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import com.mysql.jdbc.Statement;

import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Book;
@Component
@SuppressWarnings({  "rawtypes" })
public class GenreDAO extends BaseDAO implements ResultSetExtractor<List<Genre>> {

	public void saveGenre(Genre Genre) throws SQLException {
		template.update("INSERT INTO tbl_genre (`genre_name`) VALUES (?)", new Object[] { Genre.getGenreName() });
	}
	
	public void saveBookGenre(Genre Genre) throws SQLException {
		for(Book b: Genre.getBooks()){
			template.update("INSERT INTO tbl_book_genres VALUES (?, ?)", new Object[] { Genre.getGenreId(),b.getBookId()});
		}
	}
	
	public Integer saveGenreWithID(Genre genre) throws SQLException {
		KeyHolder holder = new GeneratedKeyHolder();
		final String sql = "INSERT INTO tbl_genre (`genre_name`) VALUES (?)";
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps =  connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, genre.getGenreName());
				return ps;
			}
		}, holder);
		return holder.getKey().intValue();
	}
	
	public Integer deleteBookGenre(int genreId,int bookId) throws SQLException {
		return template.update("DELETE FROM tbl_book_genres WHERE genre_id = ? AND bookId = ?", new Object[] { genreId,bookId });
	}
	public Integer updateGenre(Genre Genre) throws SQLException {
		return template.update("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?",
				new Object[] { Genre.getGenreName(), Genre.getGenreId() });
	}
	public Integer getGenresCount() throws SQLException {
		return template.queryForObject ("SELECT count(*) as COUNT FROM tbl_genre", Integer.class);
	}
	public List<Genre> readGenres(String genrename) throws SQLException {
		String sql = null;
		if(genrename !=null && !genrename.isEmpty()){
			genrename = "%"+genrename+"%";
			sql="SELECT * FROM tbl_genre WHERE genre_name like ?";
			return template.query(sql, new Object[]{genrename},this);
		}else{
			sql="SELECT * FROM tbl_genre";		
			return template.query(sql,this);
		}
		
	}
	public List<Genre> readGenresByBook(Integer bookId) throws SQLException {
		return template.query("SELECT * FROM tbl_genre where genre_id IN (Select genre_id from tbl_book_genres where bookId = ?)", new Object[]{bookId}, this);
	}
	public Genre readGenreByPK(Integer genreId) throws SQLException {
		List<Genre> genres = template.query("SELECT * FROM tbl_genre WHERE genre_id = ?", new Object[]{genreId},this);
		if(genres!=null){
			return genres.get(0);
		}
		return null;
	}
	public Integer deleteGenre(Integer genreId) throws SQLException {
		return template.update("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] { genreId });
	}
	
	
	public List<Genre> readGenres(String GenreName,Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		String sql = null;
		if(GenreName  !=null && !GenreName.isEmpty()){
			GenreName  = "%"+GenreName+"%";
			sql="SELECT * FROM tbl_genre WHERE genre_name like ?";
			if(pageNo >0)
			sql+=pagenation();
			return template.query(sql, new Object[]{GenreName},this);
		}else{
			sql="SELECT * FROM tbl_genre";
			if(pageNo >0)
			sql+=pagenation();		
			return template.query(sql,this);
		}
		
	}

	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> Genres = new ArrayList<>();
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			Genres.add(a);
		}
		
		return Genres;
	}
	
	public String pagenation() {
	if(getPageNo()!=null){
		Integer index = (getPageNo() -1) * getPageSize();
		return " LIMIT "+index+","+getPageSize();
	}
	return null;
	}



}
