package com.aemsite.demo.core.services;

import javax.annotation.Resource;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.xfa.Node;


@Component(service = NodeCreationService.class)
public class NodeCreationServiceImpl implements NodeCreationService{
		
	    private final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	    @Reference
	    private ResourceResolverFactory resolverFactory;

	    @Override
	    public boolean createNode(String parentPath, String nodeName,String type,SlingHttpServletRequest request) 
	    {
	    	ResourceResolver resourceResolver = request.getResourceResolver();
	        Session session = resourceResolver.adaptTo(Session.class);

	        try {
	            String nodePath = parentPath+"/"+nodeName;
	            if (!session.nodeExists(nodePath)) 
	            {
	                javax.jcr.Node parentNode = session.getNode(parentPath);
	                javax.jcr.Node newNode = parentNode.addNode(nodeName,type);
	                newNode.setProperty("jcr:title", "Node created through Service");
	                session.save();
	                if (session != null && session.isLive()) {
		                session.logout();
		            }
	                return true;
	            } else {
	            	return false;
	            }
	        } catch (RepositoryException e) {
	        	logger.error("Error creating node: " + e.getMessage(), e);
	        	if (session != null && session.isLive()) {
	                session.logout();
	            }
	        	return false;
	        } 
	  }
}
