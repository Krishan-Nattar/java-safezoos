package com.lambdaschool.zoos.service;

import com.lambdaschool.zoos.model.Zoo;

import java.util.ArrayList;

public interface ZooService
{
    ArrayList<Zoo> findAll();

    Zoo findZooById(long id);

    Zoo findZooByName(String name);

//    Zoo findZooByZooname(String name);

    void delete(long id);

    void deleteAnimalsFromZoo(long zooid, long animalid);

    Zoo save(Zoo zoo);

    Zoo update(Zoo zoo, long id);
}
