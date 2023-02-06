package com.ptf.wp.projekat.dogadjaji_175.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table (name="lokacije")
public class Lokacije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    private String opis;
    private  String url;
    public Lokacije() {
    }
    public Lokacije(Long id, String ime, String opis, String url) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
        this.url=url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @OneToMany(mappedBy = "lokacije")
    private List<Dogadjaji> dogadjaji;
}
