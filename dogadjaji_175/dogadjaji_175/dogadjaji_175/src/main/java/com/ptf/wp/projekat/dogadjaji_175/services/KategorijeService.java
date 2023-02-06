package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.Kategorije;
import com.ptf.wp.projekat.dogadjaji_175.repository.DogadjajRepository;
import com.ptf.wp.projekat.dogadjaji_175.repository.KategorijeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class KategorijeService {

    @Autowired
    private KategorijeRepository kategorijeRepository;
    @Autowired
    private DogadjajRepository dogadjajRepository;

    public List<Kategorije> listAll(){
        return kategorijeRepository.findAll();
    }

    public void save(Kategorije kategorije){
        kategorijeRepository.save(kategorije);
    }

    public Kategorije dohvatiIDKategorije(long id){
        return kategorijeRepository.findById(id).get();
    }

    public Kategorije updateKategorije(long id, Kategorije kategorijeRequest)
    {
        Kategorije postojecaKategorija=kategorijeRepository.findById(id).get();
        postojecaKategorija.setIme(kategorijeRequest.getIme());
        postojecaKategorija.setIkona(kategorijeRequest.getIkona());
        return kategorijeRepository.save(postojecaKategorija);
    }

    public List<Kategorije> getByKeyword(String keyword){
        return kategorijeRepository.findByKeyword(keyword);
    }

}
