package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.gcit.lms.entity.Publisher;
import com.mysql.jdbc.Statement;
import com.gcit.lms.entity.Book;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
@Component
@SuppressWarnings({ "rawtypes" })
public class PublisherDAO extends BaseDAO implements ResultSetExtractor<List<Publisher>> {
	

	public Integer savePublisher(Publisher publisher) throws SQLException {
		return template.update("INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)", new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() });
	}
	
	public void saveBookPublisher(Publisher publisher) throws SQLException {
		for(Book b: publisher.getBooks()){
			template.update("INSERT INTO tbl_book VALUES (?, ?,?)", new Object[] { b.getBookId(), b.getTitle(),publisher.getPublisherId()});
		}
	}
	
	public Integer savePublisherWithID(Publisher publisher) throws SQLException {
		KeyHolder holder = new GeneratedKeyHolder();
		final String sql = "INSERT INTO tbl_publisher (publisherName,publisherAddress,publisherPhone) VALUES (?,?,?)";
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps =  connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, publisher.getPublisherName());
                ps.setString(2, publisher.getPublisherAddress());
                ps.setString(3, publisher.getPublisherPhone());
				return ps;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	public Integer updatePublisher(Publisher publisher) throws SQLException {
		return template.update("UPDATE tbl_publisher SET publisherName = ?,publisherAddress=?,publisherPhone = ? WHERE publisherId = ?",
				new Object[] { publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone() ,publisher.getPublisherId() });
	}
	public Integer getPublishersCount() throws SQLException {
		return template.queryForObject("SELECT count(*) as COUNT FROM tbl_publisher", null,Integer.class);
	}

	public Integer deletePublisher(Integer pubId) throws SQLException {
		return template.update("DELETE FROM tbl_publisher WHERE publisherId = ?", new Object[] { pubId });
	}
	
	public Publisher readPublisherByPK(Integer publisherId) throws SQLException {
		List<Publisher> publishers= template.query("SELECT * FROM tbl_publisher WHERE publisherId = ?", new Object[]{publisherId},this);
		if(publishers!=null && !(publishers.isEmpty())){
			return publishers.get(0);
		}
		return null;
	}
	
	public List<Publisher> readPublishers(String PublisherName,Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		String sql = null;
		if(PublisherName  !=null && !PublisherName.isEmpty()){
			PublisherName  = "%"+PublisherName+"%";
			sql="SELECT * FROM tbl_publisher WHERE publisherName like ?";
			if(pageNo >0)
			sql+=pagenation();
			return template.query(sql, new Object[]{PublisherName},this);
		}else{
			sql="SELECT * FROM tbl_publisher";
			if(pageNo >0)
			sql+=pagenation();
			return template.query(sql, this);
		}
		
	}

	public List<Publisher> readPublishers(String publisherName) throws SQLException {
		if(publisherName !=null && !publisherName.isEmpty()){
			publisherName = "%"+publisherName+"%";
			return template.query("SELECT * FROM tbl_publisher WHERE publisherName like ?", new Object[]{publisherName},this);
		}else{
			return template.query("SELECT * FROM tbl_publisher",this);
		}
		
	}

	@Override
	public List<Publisher> extractData(ResultSet rs) throws SQLException {
		List<Publisher> publishers = new ArrayList<>();
		while(rs.next()){
			Publisher a = new Publisher();
			a.setPublisherId(rs.getInt("publisherId"));
			a.setPublisherName(rs.getString("publisherName"));
			a.setPublisherAddress(rs.getString("publisherAddress"));
			a.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(a);
		}
		
		return publishers;
	}
	public String pagenation() {
	if(getPageNo()!=null){
		Integer index = (getPageNo() -1) * getPageSize();
		return " LIMIT "+index+","+getPageSize();
	}
	return null;
	}


}
