package com.gcit.lms.helper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;
@Component
public class PublisherHelper {
	@Autowired
	PublisherDAO pdao;
	
	public Integer savePublisher(Publisher publisher) throws SQLException{
		if(publisher.getPublisherId()!=null){
			if(publisher.getPublisherName() != null)
			return pdao.updatePublisher(publisher);
		}else{
			return pdao.savePublisher(publisher);
		}
		return null;
	}
	public Integer deletePublisher( Integer pubId) throws SQLException{
		return pdao.deletePublisher(pubId);
	}
	public List<Publisher> readPublishers() throws SQLException{
		return pdao.readPublishers(null);
	} 
	public Publisher readPublisherByPK(Integer pubId) throws SQLException{
		return pdao.readPublisherByPK(pubId);
	} 
	public List<Publisher> readPublisher(String searchString,Integer pageNo) throws SQLException{
		List<Publisher> pubList =pdao.readPublishers(searchString,pageNo);
		for(Publisher pub : pubList ) {
			pub.setBooks(readAllBooksByPublisher(pub.getPublisherId()));
		}
		return pubList;
	} 
	 @SuppressWarnings("unchecked")
		public  List<Book> readAllBooksByPublisher(int id)
	    {
	        final String uri = "http://52.91.158.36:8091/books/publisher/"+id+".json";
	        RestTemplate restTemplate = new RestTemplate();
	        return (List<Book>)restTemplate.getForObject(uri, Object.class);   
	    }
}
