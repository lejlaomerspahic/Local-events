package com.ptf.wp.projekat.dogadjaji_175.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@Table (name="dogadjaji")
public class Dogadjaji {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datum;
    private String opis;
    private String url;
    private Long kategorijaID;
    private Long lokacijaID;

    public Dogadjaji(){}

    public Dogadjaji(Long id, String ime, LocalDate datum, String opis, String url) {
        this.id = id;
        this.ime = ime;
        this.datum = datum;
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

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
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

    public Long getLokacijaID() {
        return lokacijaID;
    }

    public void setLokacijaID(Long lokacijaID) {
        this.lokacijaID = lokacijaID;
    }

    public Long getKategorijaID() {
        return kategorijaID;
    }

    public void setKategorijaID(Long kategorijaID) {
        this.kategorijaID = kategorijaID;
    }
    public void addComment(Komentari komentari){
        this.komentari.add(komentari);
    }
    @ManyToOne
    @JoinColumn(name="lokacijaID",insertable = false,updatable = false)
    private Lokacije lokacije;

    @ManyToOne
    @JoinColumn(name="kategorijaID",insertable = false,updatable = false)
    private Kategorije kategorije;

    @OneToMany
    private List<Komentari> komentari;
}



