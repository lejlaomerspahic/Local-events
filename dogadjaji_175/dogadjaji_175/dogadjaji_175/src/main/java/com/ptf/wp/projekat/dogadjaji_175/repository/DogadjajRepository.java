package com.ptf.wp.projekat.dogadjaji_175.repository;
import com.ptf.wp.projekat.dogadjaji_175.models.Dogadjaji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogadjajRepository extends JpaRepository<Dogadjaji, Long> {
    @Query(value = "select * from dogadjaji d where d.ime like %:keyword% or d.datum like %:keyword%", nativeQuery = true)
    List<Dogadjaji> findByKeyword(@Param("keyword") String keyword);
}


