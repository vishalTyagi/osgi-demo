package com.osgi.demo.servlet;

import com.osgi.demo.dto.Blog;
import com.osgi.demo.interfaces.BlogServiceInterface;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.util.List;


/**
 * Created by vishal on 20/11/17.
 */

@SlingServlet(resourceTypes = "blog", extensions = "html", methods = "GET") public class BlogServlet extends SlingSafeMethodsServlet {

	@Reference BlogServiceInterface blogServiceInterface;

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			String sort = request.getParameter("sort");
			List<Blog> blogs = blogServiceInterface.getBlogList(sort);
			request.setAttribute("blogs", blogs);
			if ("desc".equals(sort)) {
				sort = "asc";
			} else {
				sort = "desc";
			}
			request.setAttribute("sort", sort);
			request.getRequestDispatcher("/blog.list.html").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
