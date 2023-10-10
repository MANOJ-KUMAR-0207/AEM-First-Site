package com.aemsite.demo.core.services;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

public interface SampleNodeService {
    public String createNode(final Session session, final String path, final String nodeName, final String type) throws RepositoryException;
}