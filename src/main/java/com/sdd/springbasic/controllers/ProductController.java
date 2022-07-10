package com.sdd.springbasic.controllers;

import com.sdd.springbasic.entity.Muser;
import com.sdd.springbasic.entity.Products;
import com.sdd.springbasic.model.ObjectResp;
import com.sdd.springbasic.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductRepository productRepository;
    @GetMapping("/all")
    public ResponseEntity<Object> getAll(){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setCode(201);
            objResp.setMessage("success");
            objResp.setData(productRepository.findAll());

            return new ResponseEntity<>(objResp,HttpStatus.OK);
        }catch( Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody Products obj){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setData(productRepository.save(obj));
            objResp.setCode(201);
            objResp.setMessage("success");
            return new ResponseEntity<>(objResp,HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int product_id){
        Optional<Products> obj = productRepository.findById(product_id);
        ObjectResp objResp = new ObjectResp();
        if(obj.isPresent()){
            objResp.setCode(201);
            objResp.setMessage("success");
            objResp.setData(obj);
            return new ResponseEntity<>(objResp,HttpStatus.OK);
        }else{
            objResp.setMessage("success, data tidak ditemukan");
            objResp.setCode(404);
            objResp.setData(null);
            return new ResponseEntity<>(objResp,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") int product_id){
        ObjectResp objResp = new ObjectResp();
        Optional<Products> obj = productRepository.findById(product_id);
        if(obj.isPresent()){
            productRepository.deleteById(product_id);
            objResp.setMessage("success deleted");
            objResp.setCode(201);
            objResp.setData(obj);
            return new ResponseEntity<>(objResp, HttpStatus.OK);
        }else {
            objResp.setCode(404);
            objResp.setMessage("Data Tidak ditemukan");
            objResp.setData(null);
            return new ResponseEntity<>(objResp,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") int product_id, @RequestBody Products obj){
        ObjectResp objResp = new ObjectResp();
        Optional<Products> data = productRepository.findById(product_id);
        if(data.isPresent()){
            Products product = data.get();
            product.setName(obj.getName());
            product.setPrice(obj.getPrice());
            product.setImage(obj.getImage());
            product.setDescription(obj.getDescription());
            objResp.setMessage("update");
            objResp.setCode(201);
            objResp.setData(product);
            return new ResponseEntity<>(objResp,HttpStatus.OK);
        }else {
            objResp.setCode(404);
            objResp.setMessage("Data Not Found");
            objResp.setData(null);
            return new ResponseEntity<>(objResp,HttpStatus.NOT_FOUND);
        }
    }


}
