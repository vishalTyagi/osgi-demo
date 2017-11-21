package com.osgi.demo.servlet;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;


/**
 * Created by vishal on 21/11/17.
 */
@SlingServlet(resourceTypes = "/bloglist", extensions = "html", methods = "GET")
public class PdfServlet extends SlingSafeMethodsServlet {

	//@Reference PdfService pdfService;

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			System.out.println("------------inside pdf document-----------------");
			//PDDocument document = pdfService.createPdf("this is the pdf doc");
			response.getWriter().print("Vishal");
		} catch (Exception e) {

		}
	}
}
