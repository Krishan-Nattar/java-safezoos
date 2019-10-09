package com.lambdaschool.zoos.controller;

import com.lambdaschool.zoos.model.Animal;
import com.lambdaschool.zoos.service.AnimalService;
import com.lambdaschool.zoos.view.JustTheCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/animals")
public class AnimalController
{




    @Autowired
    private AnimalService animalService;

    @GetMapping(value = "/count")
    public ResponseEntity<?> countsZoosPerAnimal()
    {
        List<JustTheCount> myList = animalService.getCountZooanimals();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }
//    GET /animals/count - that returns a JSON object list listing the animals and a
//    count of how many zoos where they can be found. Use a custom query for this.

    @GetMapping(value = "/animals",
                produces = {"application/json"})
    public ResponseEntity<?> listAllAnimals()
    {
        return new ResponseEntity<>(animalService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{type}",
                produces = {"application/json"})
    public ResponseEntity<?> findAnimalByType(
            @PathVariable
                    String type)
    {
        Animal a = animalService.findAnimalByType(type);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
}
