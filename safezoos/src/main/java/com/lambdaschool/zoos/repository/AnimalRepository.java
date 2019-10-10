package com.lambdaschool.zoos.repository;

import com.lambdaschool.zoos.model.Animal;
import com.lambdaschool.zoos.view.JustTheCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface AnimalRepository extends CrudRepository<Animal, Long>
{
    Animal findByAnimaltype(String type);

@Query(value = "SELECT a.animaltype as animalname, count(z.zooid) as count FROM animal a LEFT JOIN zooanimals z ON a.animalid = z.zooid GROUP BY animalname order by count DESC", nativeQuery =true)
    List<JustTheCount> getCountZooanimals();
}
