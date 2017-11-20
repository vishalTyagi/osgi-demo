package com.osgi.demo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * Created by vishal on 15/11/17.
 */
public class Activator implements BundleActivator {
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Bundle started");
//		bundleContext.addServiceListener(this);
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Bundle stoped");
	}
}
