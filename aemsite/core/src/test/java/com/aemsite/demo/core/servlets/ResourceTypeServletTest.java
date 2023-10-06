/*
 *  Copyright 2018 Adobe Systems Incorporated
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.aemsite.demo.core.servlets;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.sling.servlethelpers.MockSlingHttpServletRequest;
import org.apache.sling.servlethelpers.MockSlingHttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
@ExtendWith(AemContextExtension.class)
class ResourceTypeServletTest {
	
	AemContext aemContext = new AemContext();
	ResourceTypeServlet unitTest = new ResourceTypeServlet();
	@BeforeEach
	void setUp()
	{
		
	}
    @Test
    void doGetTest()  throws ServletException, IOException
    {
    	aemContext.build().resource("/content/aemsiteTest/test", "jcr:title", "Test Page");
    	aemContext.currentResource("/content/aemsiteTest/test");
    	MockSlingHttpServletRequest req = aemContext.request();
    	MockSlingHttpServletResponse res = aemContext.response();
    	unitTest.doGet(req,res);
    	assertEquals("This message is retrived from Resource Type Servlet",res.getOutputAsString());
    }
}
