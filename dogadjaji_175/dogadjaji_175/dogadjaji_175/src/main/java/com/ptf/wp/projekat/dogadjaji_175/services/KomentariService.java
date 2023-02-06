package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.Komentari;
import com.ptf.wp.projekat.dogadjaji_175.repository.DogadjajRepository;
import com.ptf.wp.projekat.dogadjaji_175.repository.KomentariRepository;
import com.ptf.wp.projekat.dogadjaji_175.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KomentariService {

    @Autowired
    private KomentariRepository komentariRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DogadjajRepository dogadjajRepository;

    public List<Komentari> dohvatiKomentare(long id) throws Exception {
        var dog = this.dogadjajRepository.findById(id);
        if(dog == null) throw new Exception("Ne postoji događaj");
        return komentariRepository.findByDogadjaj(dog);
    }


    public void save(long id,Komentari komentari){
        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        Komentari k = new Komentari();
        k.setKomentarText(komentari.getKomentarText());
        k.setDogadjaj(this.dogadjajRepository.findById(id).get());
        k.setDatum(komentari.getDatum());
        k.setUser(this.userRepository.findByEmail(auth));
        komentariRepository.save(k);
    }
}
