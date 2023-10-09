package com.aemsite.demo.core.services;

import org.apache.sling.api.SlingHttpServletRequest;

public interface NodeCreationService 
{
	public boolean createNode(String parentPath, String nodeName,String type,SlingHttpServletRequest request);
}
