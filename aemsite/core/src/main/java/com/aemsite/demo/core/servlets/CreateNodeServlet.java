package com.aemsite.demo.core.servlets;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import javax.servlet.Servlet;
import java.io.IOException;
@Component(
   service = { Servlet.class },
   property = {
       "sling.servlet.methods=POST",
       "sling.servlet.paths=/bin/createNodeResourceResolver"
   }
)
public class CreateNodeServlet extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 1L;
   @Override
   protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
       ResourceResolver resourceResolver = request.getResourceResolver();
       // Define the path where you want to create the node
       String nodePath = "/apps/aemsite/components/createdNodesFolder/createdNode";
       try {
           // Check if the node doesn't exist already
           if (resourceResolver.getResource(nodePath)==null) {
               // Create the node
               Resource parentNode = resourceResolver.getResource("/apps/aemsite/components/createdNodesFolder");
               resourceResolver.create(parentNode, "createdNode", null);
               // Add a path property to the newly created node
               Resource newNode = resourceResolver.getResource(nodePath);
               newNode.adaptTo(ModifiableValueMap.class).put("pathProperty", "/apps/aemsite/components/createdNodesFolder/createdNode");
               // Save changes
               resourceResolver.commit();
               response.getWriter().write("Node created successfully.");
           } else {
               response.getWriter().write("Node already exists.");
           }
       } catch (Exception e) 
       {
           response.getWriter().write("Error: " + e.getMessage());
       }
   }
}