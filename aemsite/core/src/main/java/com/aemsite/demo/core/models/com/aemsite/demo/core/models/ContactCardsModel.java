package com.aemsite.demo.core.models;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class, resourceType= ContactCardsModel.RESOURCE_TYPE, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name="jackson", selector="test", extensions="json")
public class ContactCardsModel {

    static final String RESOURCE_TYPE="/apps/aemsite/components/multiField";

    @ChildResource(name="contact")
    public List<Contact> contact;
    
    public List<Contact> getContact()
    {
        return contact;
    }
}