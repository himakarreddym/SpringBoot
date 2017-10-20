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

import com.gcit.lms.entity.Publisher;
import com.gcit.lms.helper.PublisherHelper;

@RestController
public class PublisherController {


    @Autowired
    PublisherHelper publisherHelper;


    /**
	 * Publisher
	 * 
	 */
	@Transactional
	@RequestMapping(value = "/publisher/save", method = RequestMethod.POST, consumes="application/json")
	public Integer savePublisher(@RequestBody Publisher publisher) throws SQLException{
		return publisherHelper.savePublisher(publisher);
	}
	@Transactional
	@RequestMapping(value = "/publisher/{pubId}/edit", method = RequestMethod.PUT, consumes="application/json")
	public Integer updatePublisher(@PathVariable Integer pubId,@RequestBody Publisher publisher) throws SQLException{
		if(pubId== publisher.getPublisherId()) {
		return publisherHelper.savePublisher(publisher);
		}
		return -1;
	}
	@Transactional
	@RequestMapping(value = "/publisher/{pubId}/delete", method = RequestMethod.DELETE, consumes="application/json")
	public Integer deletePublisher(@PathVariable Integer pubId) throws SQLException{
			return publisherHelper.deletePublisher(pubId);
	}
	@RequestMapping(value = "/publishers", method = RequestMethod.GET, produces="application/json")
	public List<Publisher> readPublishers() throws SQLException{
			return publisherHelper.readPublishers();
		} 
	@RequestMapping(value = "/publisher/{pubId}", method = RequestMethod.GET, produces="application/json")
	public Publisher readPublisherByPK(@PathVariable Integer pubId) throws SQLException{
			return publisherHelper.readPublisherByPK(pubId);
		} 
	@RequestMapping(value = "/publishers/{searchString}", method = RequestMethod.GET, produces="application/json")
	public List<Publisher> readPublisher(@PathVariable String searchString,@RequestParam(value= "pageNo", required=false)String pageNo) throws SQLException{
		return publisherHelper.readPublisher(searchString, pageNo != null ? Integer.parseInt(pageNo):-1);
	} 

}
