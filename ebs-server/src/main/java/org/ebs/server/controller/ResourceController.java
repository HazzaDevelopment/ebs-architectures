package org.ebs.server.controller;

import org.ebs.shared.server.hibernate.dao.ResourceDao;
import org.ebs.shared.server.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class ResourceController {

    private final ResourceDao resourceDao;

    @Autowired
    public ResourceController(ResourceDao resourceDao){
        this.resourceDao = resourceDao;
    }


    @GetMapping("/resource-page")
    public String main(Model model, @RequestParam(value = "id", required = true) Long id) {
        Optional<Resource> optionalResource = resourceDao.findById(id);
        if(optionalResource.isPresent()){
            Resource resource = optionalResource.get();
            model.addAttribute("mainTitle", resource.getTitle());
            model.addAttribute("descriptionHeading", resource.getDescriptionHeading());
            model.addAttribute("descriptionBody", resource.getDescriptionPanel());
            model.addAttribute("adviceHeading", resource.getAdvicePanelHeading());
            model.addAttribute("adviceBody", resource.getAdvicePanel());
            model.addAttribute("listItemsHeading", resource.getListItemsHeading());
            model.addAttribute("listItems", resource.getListItems());

        } else {
            throw new RuntimeException("Id not found");
        }
        return "resource"; //view
    }
}
