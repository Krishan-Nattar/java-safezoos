package com.lambdaschool.zoos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "zoo")
public class Zoo extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long zooid;

    private String zooname;

    @OneToMany(mappedBy = "zoo",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonIgnoreProperties("zoo")
    private List<Telephone> telephones = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "zooanimals",
               joinColumns = {@JoinColumn(name = "zooid")},
               inverseJoinColumns = {@JoinColumn(name = "animalid")})

//    @CreatedDate //Spring adds underscores.
//    @Temporal(TIMESTAMP) //This tells Spring how we want the date to be formatted.
//    protected Date createdDate;
    @JsonIgnoreProperties("zoos")
    private List<Animal> animals = new ArrayList<>();

    public long getZooid()
    {
        return zooid;
    }

    public void setZooid(long zooid)
    {
        this.zooid = zooid;
    }

    public String getZooname()
    {
        return zooname;
    }

    public void setZooname(String zooname)
    {
        this.zooname = zooname;
    }

    public List<Telephone> getTelephones()
    {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones)
    {
        this.telephones = telephones;
    }

    public List<Animal> getAnimals()
    {
        return animals;
    }

    public void setAnimals(List<Animal> animals)
    {
        this.animals = animals;
    }

    @Override
    public String toString()
    {
        return "Zoo{" + "zooid=" + zooid + ", zooname='" + zooname + '\'' + ", telephones=" + telephones + ", animals=" + animals + '}';
    }
}
