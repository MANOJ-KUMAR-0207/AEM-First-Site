package com.aemsite.demo.core.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.aemsite.demo.core.services.SampleNodeService;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Node;
import javax.jcr.Repository;

import io.wcm.testing.mock.aem.junit5.AemContext;
import org.apache.sling.api.resource.ResourceResolver;

class SampleNodeServiceTest {
//    public AemContext aemContext = new AemContext();
//    ResourceResolver resolver = aemContext.resourceResolver();
//    Session session = resolver.adaptTo(Session.class);
	Repository repository = mock(Repository.class);
    Session session = mock(Session.class);
    Node rootNode = mock(Node.class);
    private SampleNodeService sampleNodeService;

    @BeforeEach
    public void setUp() 
    {
        sampleNodeService = new SampleNodeServiceImpl();
    }

    @Test
    void createNode() throws RepositoryException
    {
         String parentPath="/content/aemsite";
         String nodeName = "testServiceNode";
         String type="nt:unstructured";
         String expectedRes="A new node with name "+nodeName+" was created aty the path "+parentPath;
         assertEquals(expectedRes,sampleNodeService.createNode(session,parentPath,nodeName,type));
    }
}