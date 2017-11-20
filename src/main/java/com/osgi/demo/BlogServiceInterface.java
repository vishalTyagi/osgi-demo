package com.osgi.demo;

import com.osgi.demo.dto.Blog;
import org.apache.sling.api.resource.LoginException;

import java.util.Iterator;


/**
 * Created by vishal on 17/11/17.
 */
public interface BlogServiceInterface {

	public Iterator<Blog> getBlogs() throws LoginException;

}
