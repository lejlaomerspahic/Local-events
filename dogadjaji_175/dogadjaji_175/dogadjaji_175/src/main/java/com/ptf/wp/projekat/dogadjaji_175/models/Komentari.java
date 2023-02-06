package com.ptf.wp.projekat.dogadjaji_175.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="komentari")
@Data
public class Komentari {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String komentarText;
    @ManyToOne
    private User user;
    @ManyToOne
    @JsonIgnore
    private Dogadjaji dogadjaj;
    public Komentari(String text)
    {
        this.komentarText=text;
    }
    public Komentari() {
    }
    public Komentari(String komentarText, Date datum) {
        this.komentarText = komentarText;
        this.datum = datum;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datum=new Date();


}
