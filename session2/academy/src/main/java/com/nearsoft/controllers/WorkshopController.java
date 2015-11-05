package com.nearsoft.controllers;


import com.nearsoft.domain.Workshop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class WorkshopController {
    @RequestMapping("/workshops")
    public String list(Model model) {

        List<Workshop> workshops = getWorkshops();
        model.addAttribute("workshops", workshops);
        return "workshops";
    }

    private List<Workshop> getWorkshops() {
        List<Workshop> workshops = new ArrayList<>();
        workshops.add(new Workshop("Java", 1, 10, new Date()));
        workshops.add(new Workshop("C", 1, 10, new Date()));
        workshops.add(new Workshop("PHP", 1, 10, new Date()));
        return workshops;
    }

    @RequestMapping("/workshop/{title}")
    public String detail(@PathVariable String title
                        , Model model) {
        Workshop workshop = findWorkshop(title);

        model.addAttribute("workshop", workshop);
        return "workshopDetail";
    }

    private Workshop findWorkshop(String title) {
        List<Workshop> workshops = getWorkshops();
        for (Workshop workshop : workshops) {
            if (title.equals(workshop.getTitle())){
                 return workshop;
            }
        }
        return null;
    }

}
