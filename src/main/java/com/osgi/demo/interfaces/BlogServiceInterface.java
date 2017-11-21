package com.osgi.demo.interfaces;

import com.osgi.demo.dto.Blog;
import org.apache.sling.api.resource.LoginException;

import java.util.Iterator;
import java.util.List;


/**
 * Created by vishal on 17/11/17.
 */
public interface BlogServiceInterface {

	public Iterator<Blog> getBlogs() throws LoginException;

	public List<Blog> getBlogList(String sort) throws LoginException;
}
