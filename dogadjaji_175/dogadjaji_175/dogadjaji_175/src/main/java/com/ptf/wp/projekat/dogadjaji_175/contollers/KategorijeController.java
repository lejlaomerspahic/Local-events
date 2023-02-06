package com.ptf.wp.projekat.dogadjaji_175.contollers;

import com.ptf.wp.projekat.dogadjaji_175.models.Kategorije;
import com.ptf.wp.projekat.dogadjaji_175.services.KategorijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class KategorijeController {

    @Autowired
    private KategorijeService service1;

    @GetMapping
    @RequestMapping("/kategorije/admin")
    public String viewPageAdmin(Model model) {
        List<Kategorije> listaKategorija = service1.listAll();
        model.addAttribute("listaKategorija", listaKategorija);
        return "indexAdminaKategorije";
    }

    @GetMapping
    @RequestMapping("/kategorije/user")
    public String viewPageUser(Model model) {
        List<Kategorije> listaKategorija = service1.listAll();
        model.addAttribute("listaKategorija", listaKategorija);
        return "indexUseraKategorije";
    }

    @GetMapping
    @RequestMapping("/kategorije/admin/new")
    public String addEvents(Model model) {
        model.addAttribute("kategorije", new Kategorije());
        return "addNewCategory";
    }

    @PostMapping
    @RequestMapping("/kategorije/admin/save")
    public String saveEvents(@ModelAttribute("kategorije") Kategorije std) {
        service1.save(std);
        return "redirect:/kategorije/amin";
    }


    @GetMapping
    @RequestMapping("/kategorije/admin/edit/{id}")
    public ModelAndView Forma(@PathVariable("id") long id, Model model) {
        ModelAndView mv=new ModelAndView("updateCategory");
        model.addAttribute("kategorije", this.service1.dohvatiIDKategorije(id));
        return mv;
    }

    @PutMapping
    @RequestMapping("/kategorije/admin/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute("kategorije") Kategorije kategorijeRequest){
        service1.updateKategorije(id,kategorijeRequest);
        return "redirect:/kategorije/admin";
    }


    @RequestMapping("/kategorije/admin/search")
    public String homeAdminSearch(KategorijeService kategorije, Model model, String keyword) {
        if(keyword!=null) {
            List<Kategorije> listaKategorija = service1.getByKeyword(keyword);
            model.addAttribute("listaKategorija", listaKategorija);
        }else {
            List<Kategorije> listaKategorija = service1.listAll();
            model.addAttribute("listaKategorija", listaKategorija);}
        return "kategorijeAdminSearch";
    }

    @RequestMapping("/kategorije/user/search")
    public String homeUserSearch(KategorijeService kategorije, Model model, String keyword) {
        if(keyword!=null) {
            List<Kategorije> listaKategorija = service1.getByKeyword(keyword);
            model.addAttribute("listaKategorija", listaKategorija);
        }else {
            List<Kategorije> listaKategorija = service1.listAll();
            model.addAttribute("listaKategorija", listaKategorija);}
        return "kategorijeUserSearch";
    }
}
