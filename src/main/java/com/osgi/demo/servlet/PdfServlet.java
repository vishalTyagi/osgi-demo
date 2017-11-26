package com.osgi.demo.servlet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.osgi.demo.dto.Blog;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.io.OutputStream;
import java.util.Arrays;


/**
 * Created by vishal on 21/11/17.
 */
@SlingServlet(resourceTypes = "blog/id", selectors = "print", extensions = "html", methods = "GET") public class PdfServlet extends SlingSafeMethodsServlet {

	//@Reference PdfService pdfService;

	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
		try {
			Blog blog = new Blog(request.getResource().getValueMap());
			blog.setBlogPath(request.getResource().getPath());
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "blogPdf.pdf\"");
			response.setHeader("Cache-Control", "no-cache");
			OutputStream os = response.getOutputStream();
			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			try {
				PdfWriter writer = PdfWriter.getInstance(document, os);
				document.open();
				Anchor anchorTarget = new Anchor("Blog Summary");
				anchorTarget.setName("BackToTop");
				Paragraph paragraph1 = new Paragraph();
				paragraph1.setSpacingBefore(50);
				paragraph1.add(anchorTarget);
				PdfPTable table = new PdfPTable(5);
				table.setSpacingBefore(25);
				table.setSpacingAfter(25);
				PdfPCell c1 = new PdfPCell(new Phrase("Title"));
				table.addCell(c1);
				PdfPCell c2 = new PdfPCell(new Phrase("Description"));
				table.addCell(c2);
				PdfPCell c3 = new PdfPCell(new Phrase("Author"));
				table.addCell(c3);
				PdfPCell c4 = new PdfPCell(new Phrase("Published On"));
				table.addCell(c4);
				PdfPCell c5 = new PdfPCell(new Phrase("Tags"));
				table.addCell(c5);
				table.addCell(blog.getTitle());
				table.addCell(blog.getDescription());
				table.addCell(blog.getAuthor());
				table.addCell(blog.getPublishOn());
				table.addCell(Arrays.asList(blog.getTags()).toString());
				document.add(paragraph1);
				document.add(table);
				document.close();
				os.close();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {

		}
	}
}
