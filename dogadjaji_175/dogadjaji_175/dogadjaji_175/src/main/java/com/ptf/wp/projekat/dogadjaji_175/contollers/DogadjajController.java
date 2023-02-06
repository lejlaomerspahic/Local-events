package com.ptf.wp.projekat.dogadjaji_175.contollers;

import com.ptf.wp.projekat.dogadjaji_175.models.*;
import com.ptf.wp.projekat.dogadjaji_175.services.DogadjajService;
import com.ptf.wp.projekat.dogadjaji_175.services.KategorijeService;
import com.ptf.wp.projekat.dogadjaji_175.services.KomentariService;
import com.ptf.wp.projekat.dogadjaji_175.services.LokacijeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class DogadjajController {
    @Autowired
    private DogadjajService service;
    @Autowired
    private KategorijeService service1;
    @Autowired
    private LokacijeService service2;
    @Autowired
    private KomentariService service3;

    @GetMapping
    @RequestMapping("/dogadjaji/admin")
    public String viewHomePageAdmin(Model model) {
        List<Kategorije> listaKategorija = service1.listAll();
        model.addAttribute("listaKategorija", listaKategorija);

        List<Lokacije> listaLokacija = service2.listAll();
        model.addAttribute("listaLokacija", listaLokacija);

        List<Dogadjaji> listaDogadjaja = service.listAll();
        model.addAttribute("listaDogadjaja", listaDogadjaja);
        return "indexAdmina";
    }

    @GetMapping
    @RequestMapping("/dogadjaji/admin/komentari/{id}")
    public ModelAndView Forma2(@PathVariable("id") long id, Model model) throws Exception {
        ModelAndView mv=new ModelAndView("indexAdminaKomentari");
        List<Lokacije> listaLokacija = service2.listAll();
        model.addAttribute("listaLokacija", listaLokacija);
        model.addAttribute("dogadjaj", this.service.dohvatiIDdogadjaja(id));
        model.addAttribute("listaKomentara", this.service3.dohvatiKomentare(id));
        return mv;
    }

    @PutMapping
    @RequestMapping("/dogadjaji/admin/komentari/suspenduj/{id}")
    public String update(@PathVariable long id){
        this.service.updateUsera(id);
        return "redirect:/dogadjaji/admin";
    }

    @GetMapping
    @RequestMapping("/dogadjaji/user")
    public String viewHomePageUser(Model model) {
        List<Kategorije> listaKategorija = service1.listAll();
        model.addAttribute("listaKategorija", listaKategorija);

        List<Lokacije> listaLokacija = service2.listAll();
        model.addAttribute("listaLokacija", listaLokacija);

        List<Dogadjaji> listaDogadjaja = service.listAll();
        model.addAttribute("listaDogadjaja", listaDogadjaja);
        return "indexUsera";
    }


    @GetMapping
    @RequestMapping("/dogadjaji/admin/new")
    public String addEvents(Model model) {
        List<Kategorije> listaKategorija = service1.listAll();
        model.addAttribute("listaKategorija", listaKategorija);

        List<Lokacije> listaLokacija = service2.listAll();
        model.addAttribute("listaLokacija", listaLokacija);

        model.addAttribute("dogadjaj", new Dogadjaji());
        return "addNewEvent";
    }

    @PostMapping
    @RequestMapping("/dogadjaji/admin/save")
    public String saveEvents(@ModelAttribute("dogadjaj") Dogadjaji std) {
        service.save(std);
        return "redirect:/dogadjaji/admin";
    }

    @GetMapping
    @RequestMapping("/dogadjaji/admin/edit/{id}")
    public ModelAndView Forma(@PathVariable("id") long id, Model model) {
        ModelAndView mv=new ModelAndView("updateEvent");
        List<Kategorije> listaKategorija = service1.listAll();
        model.addAttribute("listaKategorija", listaKategorija);

        List<Lokacije> listaLokacija = service2.listAll();
        model.addAttribute("listaLokacija", listaLokacija);
        model.addAttribute("dogadjaj", this.service.dohvatiIDdogadjaja(id));
        return mv;
    }

    @PutMapping
    @RequestMapping("/dogadjaji/admin/update/{id}")
    public String update(@PathVariable long id, @ModelAttribute("dogadjaj") Dogadjaji dogadajiRequest){
        service.updateDogadjaja(id,dogadajiRequest);
        return "redirect:/dogadjaji/admin";
    }

    @GetMapping
    @RequestMapping("/dogadjaji/user/komentari/{id}")
    public ModelAndView Forma1(@PathVariable("id") long id, Model model) throws Exception {
        ModelAndView mv=new ModelAndView("komentariUser");
        List<Lokacije> listaLokacija = service2.listAll();
        model.addAttribute("listaLokacija", listaLokacija);
        model.addAttribute("dogadjaj", this.service.dohvatiIDdogadjaja(id));
        model.addAttribute("listaKomentara", this.service3.dohvatiKomentare(id));
        return mv;
    }

    @PostMapping
    @RequestMapping("/dogadjaji/user/komentari/save/{id}")
    public String saveComment(@PathVariable long id,@ModelAttribute("komentar") Komentari komentari) {
        service3.save(id,komentari);
        return "redirect:/dogadjaji/user/komentari/{id}";
    }

    @RequestMapping("/dogadjaji/admin/search")
    public String homeSearchAdmin(Dogadjaji dogadjaj, Model model, String keyword) {
        if(keyword!=null) {
            List<Dogadjaji> listaDogadjaja = service.getByKeyword(keyword);
            model.addAttribute("listaDogadjaja", listaDogadjaja);
        }else {
            List<Dogadjaji> listaDogadjaja = service.listAll();
            model.addAttribute("listaDogadjaja", listaDogadjaja);}
        return "dogadjajiAdminSearch";
    }

    @RequestMapping("/dogadjaji/user/search")
    public String homeSearchUser(Dogadjaji dogadjaj, Model model, String keyword) {
        if(keyword!=null) {
            List<Dogadjaji> listaDogadjaja = service.getByKeyword(keyword);
            model.addAttribute("listaDogadjaja", listaDogadjaja);
        }else {
            List<Dogadjaji> listaDogadjaja = service.listAll();
            model.addAttribute("listaDogadjaja", listaDogadjaja);}
        return "dogadjajiUserSearch";
    }

}
