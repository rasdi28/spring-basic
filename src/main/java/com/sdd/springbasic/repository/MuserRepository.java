package com.sdd.springbasic.repository;

import com.sdd.springbasic.entity.Muser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MuserRepository extends JpaRepository<Muser,Integer> {

    @Query(value = "SELECT * from Muser t Where t.address ='bogor'",
            nativeQuery = true)
    public List<Muser> findByaddress();

    @Query(value = "SELECT * from Muser", nativeQuery = true)
    public List<Muser> getall();




}
