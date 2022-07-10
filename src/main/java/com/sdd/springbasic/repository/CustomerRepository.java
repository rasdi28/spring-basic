package com.sdd.springbasic.repository;
import com.sdd.springbasic.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{

    public List<Customer> findByNameContaining(String name);

}
