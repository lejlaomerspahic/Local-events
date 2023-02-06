package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.Lokacije;
import com.ptf.wp.projekat.dogadjaji_175.repository.LokacijeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LokacijeService {
    @Autowired
    private LokacijeRepository lokacijeRepository;


    public List<Lokacije> listAll(){
        return lokacijeRepository.findAll();
    }


    public void save(Lokacije lokacije){
        lokacijeRepository.save(lokacije);
    }

    public Lokacije dohvatiIDLokacije(long id){
        return lokacijeRepository.findById(id).get();
    }

    public Lokacije updateLokacije(long id, Lokacije lokacijeRequest)
    {
        Lokacije postojecaLokacija=lokacijeRepository.findById(id).get();
        postojecaLokacija.setIme(lokacijeRequest.getIme());
        postojecaLokacija.setOpis(lokacijeRequest.getOpis());
        postojecaLokacija.setUrl(lokacijeRequest.getUrl());
        return lokacijeRepository.save(postojecaLokacija);
    }
    public List<Lokacije> getByKeyword(String keyword){
        return lokacijeRepository.findByKeyword(keyword);
    }

}
