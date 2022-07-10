package com.sdd.springbasic.controllers;

import com.sdd.springbasic.entity.Customer;
import com.sdd.springbasic.repository.CustomerRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> findAll(
            @RequestParam(name = "name",
                    required = false,
                    defaultValue = "") String name) {
        try {
            List<Customer> customers;
            if (StringUtils.hasText(name)) {
                customers = customerRepository.findByNameContaining(name);
            } else {
                customers = customerRepository.findAll();
            }

            if (customers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity(customers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        Optional<Customer> customerData = customerRepository.findById(id);
        if(customerData.isPresent()){
            return  new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        try {
            Customer objCustomer = customerRepository.save(customer);
            return new ResponseEntity<>(objCustomer,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id){
        try {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
