package com.aemsite.demo.core.servlets;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aemsite.demo.core.services.SampleNodeService;

@Component(
	    service = Servlet.class,
	    property = {
	        Constants.SERVICE_DESCRIPTION + "=Create Node Servlet",
	        "sling.servlet.methods=POST",
	        "sling.servlet.paths=/bin/sampleCreateNodeAPI"
	    }
	)
public class SampleNodeServiceServlet extends SlingAllMethodsServlet 
{
	 private static final long serialVersionUID = 7L;
	 
	 @Reference
	 private SampleNodeService sampleNodeService;
	 
	 protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response){
	        ResourceResolver resourceResolver = request.getResourceResolver();
	        Session session = resourceResolver.adaptTo(Session.class);
	        String parentPath="/content/aemsite";
	        String nodeName = "sampleServiceNode2";
	        String type="nt:unstructured";
	        try {
	            sampleNodeService.createNode(session,parentPath,nodeName,type);
	        } catch (RepositoryException e) {
	            
	        } finally {
	            if (session != null && session.isLive()) {
	                session.logout();
	            }
	        }
	    }
}
