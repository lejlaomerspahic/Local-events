package com.ptf.wp.projekat.dogadjaji_175.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

@Table (name="kategorije")
public class Kategorije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;

    private String ikona;

    public  Kategorije() {
    }

    public Kategorije(Long id, String ime, String ikona) {
        this.id = id;
        this.ime = ime;
        this.ikona = ikona;
    }

    public Long getId() {
        return id;
    }

    public void Long(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getUrl() {
        return ikona;
    }

    public void setUrl(String ikona) {
        this.ikona = ikona;
    }

    @OneToMany(mappedBy = "kategorije")
    private List<Dogadjaji> dogadjaji;

}