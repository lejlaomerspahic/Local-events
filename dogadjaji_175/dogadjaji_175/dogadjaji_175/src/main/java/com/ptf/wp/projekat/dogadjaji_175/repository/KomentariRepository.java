package com.ptf.wp.projekat.dogadjaji_175.repository;

import com.ptf.wp.projekat.dogadjaji_175.models.Dogadjaji;
import com.ptf.wp.projekat.dogadjaji_175.models.Komentari;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KomentariRepository extends JpaRepository<Komentari, Long> {
    List<Komentari> findByDogadjaj(Optional<Dogadjaji> dogadjaj);
}
