package com.aemsite.demo.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aemsite.demo.core.services.NodeCreationService;
import com.aemsite.demo.core.services.SampleNodeService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/createNodeService",
        "sling.servlet.methods=POST"
})
public class CreateNodeServiceServlet extends SlingAllMethodsServlet 
{
	private static final long serialVersionUID = 2L;
    @Reference
//    private NodeCreationService nodeCreationService;
    private SampleNodeService sampleNodeService;
    
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException 
    {
    	
        String parentPath = "/content/aemsite";
        String nodeName = "serviceNode";
        String type="nt:unstructured";
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);
        try {
			sampleNodeService.createNode(session, parentPath, nodeName, type);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        
//        nodeCreationService.createNode(parentPath, nodeName, type,request);
        
    	  

    }
}
