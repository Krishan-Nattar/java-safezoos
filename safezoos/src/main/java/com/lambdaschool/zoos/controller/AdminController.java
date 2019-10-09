package com.lambdaschool.zoos.controller;

import com.lambdaschool.zoos.model.Zoo;
import com.lambdaschool.zoos.service.AnimalService;
import com.lambdaschool.zoos.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    ZooService zooService;

    @Autowired
    AnimalService animalService;

    @DeleteMapping(value = "/zoos/{zooid}/animals/{animalid}")
    public ResponseEntity<?> DeleteAnimalFromZoo(@PathVariable long zooid, @PathVariable long animalid)
    {
//        Zoo z = zooService.findZooById(zooid);
        zooService.deleteAnimalsFromZoo(zooid, animalid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/zoos/{id}",
                produces = {"application/json"},
                consumes = {"application/json"})
    public ResponseEntity<?> updateZoo(
            @RequestBody
                    Zoo updateZoo,
            @PathVariable
                    long id)
    {
        zooService.update(updateZoo, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // POST localhost:2019/admin/zoos
    @PostMapping(value = "/zoos",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewZoo(HttpServletRequest request, @Valid
    @RequestBody
            Zoo newZoo) throws URISyntaxException
    {
        newZoo = zooService.save(newZoo);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        // URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zooid}").buildAndExpand(newZoo.getZooid()).toUri();
        URI newRestaurantURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/zoos/zoos/{zooid}").buildAndExpand(newZoo.getZooid()).toUri();
        responseHeaders.setLocation(newRestaurantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/zoos/{zooid}")
    public ResponseEntity<?> deleteZooById(
            @PathVariable
                    long zooid)
    {
        zooService.delete(zooid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/zoos/{zooid}/animals/{animalid}")
    public ResponseEntity<?> saveZooAnimalCombo(HttpServletRequest request,
            @PathVariable("zooid")
                    long zooid,
            @PathVariable("animalid")
                    long animalid)
    {
//        zooService.saveZooAnimalCombo(zooid, animalid);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        // URI newZooURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{zooid}").buildAndExpand(newZoo.getZooid()).toUri();
        URI newZooURI = ServletUriComponentsBuilder.fromUriString(request.getServerName() + ":" + request.getLocalPort() + "/zoos/zoos/{zooid}").buildAndExpand(zooid).toUri();
        responseHeaders.setLocation(newZooURI);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
