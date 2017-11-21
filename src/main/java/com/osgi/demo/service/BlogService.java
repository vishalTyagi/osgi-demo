package com.osgi.demo.service;

import com.osgi.demo.dto.Blog;
import com.osgi.demo.interfaces.BlogServiceInterface;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.util.*;


/**
 * Created by vishal on 17/11/17.
 */
@Component(immediate = true) @Service(BlogServiceInterface.class) public class BlogService implements BlogServiceInterface {

	@Reference public ResourceResolverFactory resourceResolverFactory;

	public Iterator<Blog> getBlogs() throws LoginException {
		return getBlogList().iterator();
	}

	public List<Blog> getBlogList() throws LoginException {
		ResourceResolver resourceResolver = resourceResolverFactory.getResourceResolver(getAuth());
		Resource resource = resourceResolver.getResource("/content/blog");
		Iterator<Resource> iterator = resource.listChildren();
		List<Blog> blogs = new ArrayList<Blog>();
		while (iterator.hasNext()) {
			Resource resource1 = iterator.next();
			Blog blog = new Blog(resource1.getValueMap());
			blog.setBlogPath(resource1.getPath());
			blogs.add(blog);
		}
		//		System.out.println("Blogs :: " + blogs);
		return blogs;
	}

	public List<Blog> getBlogList(final String sort) {
		try {
			List<Blog> blogs = getBlogList();
			Collections.sort(blogs, new Comparator<Blog>() {
				public int compare(Blog o1, Blog o2) {
					if ("desc".equals(sort)) {
						return o2.getPublishedDate().compareTo(o1.getPublishedDate());
					} else {
						return o1.getPublishedDate().compareTo(o2.getPublishedDate());
					}
				}
			});
			return blogs;
		} catch (LoginException e) {
			e.printStackTrace();
			return new ArrayList<Blog>();
		}
	}

	private HashMap<String, Object> getAuth() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.USER, "admin");
		param.put(ResourceResolverFactory.PASSWORD, "admin".toCharArray());
		return param;
	}
}
