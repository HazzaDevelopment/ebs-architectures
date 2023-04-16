package org.ebs.rest.controller;

import org.ebs.shared.server.entity.Resource;
import org.ebs.shared.server.hibernate.dao.ResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class ResourceController {

    private final ResourceDao resourceDao;

    @Autowired
    public ResourceController(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @GetMapping("/resources")
    public List<Resource> getResources() {
        return resourceDao.findAll();
    }

    @GetMapping("/resources/{id}")
    public Resource getResourceById(@PathVariable  Long id) {
        Optional<Resource> byId = resourceDao.findById(id);
        if(byId.isPresent()){
            return byId.get();
        } else {
            throw new RuntimeException("Id not found");
        }
    }

    @PostMapping("/resource/add")
    private Resource createResource(String description, String advice, Map<String, String> listItems){
        Resource resource = new Resource();

        resource.setDescriptionPanel("The terrorism threat level system is a way for the government to communicate the likelihood of a terrorist attack in a particular area. The system uses a color-coded scale with five levels, ranging from low to severe.\n" +
                "\n" +
                "The terrorism threat level is determined by a variety of factors, including intelligence information, the overall global threat picture, and specific threat information regarding the United States.");

        resource.setAdvicePanel("When the terrorism threat level is elevated, high, or severe, you should be prepared for the possibility of a terrorist attack. This includes being aware of your surroundings, reporting any suspicious activity to law enforcement, and having an emergency plan in place.\n" +
                "\n" +
                "If an attack occurs, you should follow the instructions of law enforcement and emergency personnel. If you are in a public place, you should try to find a safe location and stay there until it is safe to leave.");

        Map<String, String> listItemsMap = new HashMap<>();
        listItemsMap.put("Infectious disease outbreaks:", "These can include outbreaks of diseases such as influenza, Ebola, and COVID-19");
        listItemsMap.put("Natural disasters:", "These include hurricanes, tornadoes, earthquakes, floods, wildfires, and other severe weather events that can cause injuries, disease, and other health problems.");

        resource.setListItems(listItems);
        return resourceDao.save(resource);
    }

}

