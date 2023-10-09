package com.aemsite.demo.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
    service = Servlet.class,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Create Node Servlet",
        "sling.servlet.methods=POST",
        "sling.servlet.paths=/bin/createNodeAPI"
    }
)
public class CreateComponentNodeAPI extends SlingAllMethodsServlet {
    private static final long serialVersionUID = 6L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Session session = resourceResolver.adaptTo(Session.class);

        try {
            // Define the path where you want to create the node
            String nodePath = "/content/aemsite/newNode";
            
            // Check if the node doesn't exist already
            if (!session.nodeExists(nodePath)) 
            {
                Node parentNode = session.getNode("/content/aemsite");
                Node newNode = parentNode.addNode("newNode","nt:unstructured");
                newNode.setProperty("propertyName", "propertyValue");
                session.save();
                response.getWriter().write("Node created successfully.");
            } else {
                response.getWriter().write("Node already exists.");
            }
        } catch (RepositoryException e) {
            logger.error("Error creating node: " + e.getMessage(), e);
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            if (session != null && session.isLive()) {
                session.logout();
            }
        }
    }
}
