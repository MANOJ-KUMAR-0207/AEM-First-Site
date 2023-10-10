package com.aemsite.demo.core.services;

import org.osgi.service.component.annotations.Component;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

@Component( service=SampleNodeService.class)
public class SampleNodeServiceImpl implements SampleNodeService 
{

    @Override
    public String createNode(final Session session, final String path, final String nodeName, final String type) throws RepositoryException 
    {

        Node parentNode = session.getNode(path);
        Node newNode = parentNode.addNode(nodeName, type);
        newNode.setProperty("propertyName", "propertyValue");
        session.save();
        String res="A new node with name "+nodeName+" was created aty the path "+path;
        if (newNode.isNew()) 
        {
            return res;
        } else 
        {
            return "Node creation failed";
        }
   }
}