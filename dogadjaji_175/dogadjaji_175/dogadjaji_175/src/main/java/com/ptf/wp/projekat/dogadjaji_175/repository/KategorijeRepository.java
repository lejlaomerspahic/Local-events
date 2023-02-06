package com.ptf.wp.projekat.dogadjaji_175.repository;

import com.ptf.wp.projekat.dogadjaji_175.models.Kategorije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KategorijeRepository extends JpaRepository<Kategorije, Long> {
    @Query(value = "select * from kategorije k where k.ime like %:keyword%", nativeQuery = true)
    List<Kategorije> findByKeyword(@Param("keyword") String keyword);

}
