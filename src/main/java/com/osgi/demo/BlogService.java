package com.osgi.demo;

import com.osgi.demo.dto.Blog;
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(ResourceResolverFactory.USER, "admin");
		param.put(ResourceResolverFactory.PASSWORD, "admin".toCharArray());
		ResourceResolver resourceResolver = resourceResolverFactory.getResourceResolver(param);
		Resource resource = resourceResolver.getResource("/content/blog");
		Iterator<Resource> iterator = resource.listChildren();
		List<Blog> blogs = new ArrayList<Blog>();
		while (iterator.hasNext()) {
			Resource resource1 = iterator.next();
			Blog blog = new Blog(resource1.getValueMap());
			blog.setBlogPath(resource1.getPath());
			blogs.add(blog);
		}
		return blogs.iterator();
	}
}
