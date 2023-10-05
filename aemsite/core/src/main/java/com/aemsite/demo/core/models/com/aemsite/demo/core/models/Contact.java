package com.aemsite.demo.core.models;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import javax.inject.Inject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.api.resource.Resource;
@Model(adaptables = Resource.class,
resourceType= Contact.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name="jackson" , extensions="json")
public class Contact {
    static final String RESOURCE_TYPE="/apps/aemsite/components/multiField";
    @ValueMapValue
    private String contactName;
    @ValueMapValue
    private String contactEmail;
    public String getContactName() {
        return contactName;
    }
    public String getContactEmail() {
        return contactEmail;
    }
}