package com.osgi.demo.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.osgi.demo.interfaces.PdfService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolverFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


/**
 * Created by vishal on 20/11/17.
 */
@Component(immediate = true) @Service(PdfService.class) public class PdfServiceImpl implements PdfService {

	@Reference private ResourceResolverFactory resolverFactory;

	public String createPDF(String filename, String value) {


		return null;
	}
}
