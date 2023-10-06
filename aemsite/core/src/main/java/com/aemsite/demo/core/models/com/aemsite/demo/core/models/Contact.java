package com.aemsite.demo.core.models;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;

import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.api.resource.Resource;
@Model(
	    adaptables = SlingHttpServletRequest.class,
	    adapters = ComponentExporter.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
		resourceType= Contact.RESOURCE_TYPE
	  )
@Exporter( 
		name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
		extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class Contact 
{
    static final String RESOURCE_TYPE="/apps/aemsite/components/multiField";
    @ValueMapValue
    private String contactName;
    @ValueMapValue
    private String contactEmail;
    public String getContactName() 
    {
        return contactName;
    }
    public String getContactEmail() 
    {
        return contactEmail;
    }
}