package com.ptf.wp.projekat.dogadjaji_175.contollers;

import com.ptf.wp.projekat.dogadjaji_175.models.Lokacije;
import com.ptf.wp.projekat.dogadjaji_175.services.LokacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class LokacijeController {
    @Autowired
    private LokacijeService service;

    @GetMapping
    @RequestMapping("/lokacije/admin")
    public String viewPageAdmin(Model model) {
        List<Lokacije> listaLokacija = service.listAll();
        model.addAttribute("listaLokacija", listaLokacija);
        return "indexAdminaLokacije";
    }


    @GetMapping
    @RequestMapping("/lokacije/user")
    public String viewPageUser(Model model) {
        List<Lokacije> listaLokacija = service.listAll();
        model.addAttribute("listaLokacija", listaLokacija);
        return "indexUseraLokacije";
    }

    @GetMapping
    @RequestMapping("/lokacije/admin/new")
    public String addEvents(Model model) {
        model.addAttribute("lokacije", new Lokacije());
        return "addNewLocation";
    }

    @PostMapping
    @RequestMapping("/lokacije/admin/save")
    public String saveEvents(@ModelAttribute("lokacije") Lokacije std) {
        service.save(std);
        return "redirect:/lokacije/admin";
    }


    @GetMapping
    @RequestMapping("/lokacije/admin/edit/{id}")
    public ModelAndView Forma(@PathVariable("id") long id, Model model) {
        ModelAndView mv=new ModelAndView("updateLocation");
        model.addAttribute("lokacije", this.service.dohvatiIDLokacije(id));
        return mv;
    }

    @PutMapping
    @RequestMapping("/lokacije/admin/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute("lokacije") Lokacije lokacijeRequest){
        service.updateLokacije(id,lokacijeRequest);
        return "redirect:/lokacije/admin";
    }

    @RequestMapping("/lokacije/admin/search")
    public String homeAdminSearch(LokacijeService lokacije, Model model, String keyword) {
        if(keyword!=null) {
            List<Lokacije> listaLokacija = service.getByKeyword(keyword);
            model.addAttribute("listaLokacija", listaLokacija);
        }else {
            List<Lokacije> listaLokacija = service.listAll();
            model.addAttribute("listaLokacija", listaLokacija);}
        return "lokacijeAdminSearch";
    }

    @RequestMapping("/lokacije/user/search")
    public String homeUserSearch(LokacijeService lokacije, Model model, String keyword) {
        if(keyword!=null) {
            List<Lokacije> listaLokacija = service.getByKeyword(keyword);
            model.addAttribute("listaLokacija", listaLokacija);
        }else {
            List<Lokacije> listaLokacija = service.listAll();
            model.addAttribute("listaLokacija", listaLokacija);}
        return "lokacijeUserSearch";
    }


}
