package com.sdd.springbasic.repository;

import com.sdd.springbasic.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products,Integer> {


}
