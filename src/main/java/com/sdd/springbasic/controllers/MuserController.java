package com.sdd.springbasic.controllers;

import com.sdd.springbasic.Handler.ResponseHandler;
import com.sdd.springbasic.entity.Muser;
import com.sdd.springbasic.model.ObjectResp;
import com.sdd.springbasic.repository.MuserRepository;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MuserController {

    public List<Muser> objList;

    @Autowired
    public MuserRepository muserRepository;

    @GetMapping("/musers")
    public ResponseEntity<List<Muser>> getAllUsers(){
        try {
            objList = muserRepository.findAll();

           return new ResponseEntity<>(objList,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/musers/byaddress")
    public ResponseEntity<List<Muser>> getByAddress(){
        try {
            objList = muserRepository.findByaddress();
            return new ResponseEntity<>(objList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/musers/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Integer muserpk){
        Optional<Muser> obj = muserRepository.findById(muserpk);
        if(obj.isPresent()){
            return ResponseHandler.generateResponse("success",HttpStatus.OK,obj);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/musers/getall")
    public ResponseEntity<Object> getAll(){
        objList = muserRepository.getall();
        return new ResponseEntity<>(objList,HttpStatus.OK);
    }

    @GetMapping("/musers/test")
    public ResponseEntity<Object> getTest(){
        ObjectResp objResp = new ObjectResp();
        try {
            objResp.setCode(201);
            objResp.setMessage("success");
            objResp.setData(muserRepository.findAll());
            return new ResponseEntity<>(objResp,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
