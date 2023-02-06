package com.ptf.wp.projekat.dogadjaji_175.repository;

import com.ptf.wp.projekat.dogadjaji_175.models.Lokacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LokacijeRepository extends JpaRepository<Lokacije, Long> {
    @Query(value = "select * from lokacije l where l.ime like %:keyword%", nativeQuery = true)
    List<Lokacije> findByKeyword(@Param("keyword") String keyword);

}
