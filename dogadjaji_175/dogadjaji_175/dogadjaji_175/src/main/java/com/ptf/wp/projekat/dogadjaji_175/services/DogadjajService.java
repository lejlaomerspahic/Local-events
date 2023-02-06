package com.ptf.wp.projekat.dogadjaji_175.services;

import com.ptf.wp.projekat.dogadjaji_175.models.Dogadjaji;
import com.ptf.wp.projekat.dogadjaji_175.models.User;
import com.ptf.wp.projekat.dogadjaji_175.repository.DogadjajRepository;
import com.ptf.wp.projekat.dogadjaji_175.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogadjajService {

    @Autowired
    private DogadjajRepository dogadjajRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Dogadjaji> listAll(){
        return dogadjajRepository.findAll();
    }

    public void save(Dogadjaji dogadjaj){
        dogadjajRepository.save(dogadjaj);
    }

    public Dogadjaji dohvatiIDdogadjaja(long id){
        return dogadjajRepository.findById(id).get();
    }


    public Dogadjaji updateDogadjaja(long id, Dogadjaji dogadjajiRequest)
    {
        Dogadjaji postojeciDogadjaj=dogadjajRepository.findById(id).get();
        postojeciDogadjaj.setIme(dogadjajiRequest.getIme());
        postojeciDogadjaj.setDatum(dogadjajiRequest.getDatum());
        postojeciDogadjaj.setOpis(dogadjajiRequest.getOpis());
        postojeciDogadjaj.setUrl(dogadjajiRequest.getUrl());
        postojeciDogadjaj.setKategorijaID(dogadjajiRequest.getKategorijaID());
        postojeciDogadjaj.setLokacijaID(dogadjajiRequest.getLokacijaID());
        return dogadjajRepository.save(postojeciDogadjaj);
    }


    public List<Dogadjaji> getByKeyword(String keyword){
        return dogadjajRepository.findByKeyword(keyword);
    }

    public User updateUsera(long id) {
        User postojeciUser= userRepository.findById(id).get();
        postojeciUser.setAktivan(false);
        return userRepository.save(postojeciUser);
    }
}
