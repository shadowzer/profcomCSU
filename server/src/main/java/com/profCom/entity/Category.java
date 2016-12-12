package com.profCom.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by VolgiNN on 09.12.2016.
 */
@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment",strategy = "increment")
    public Integer id;

    @Column(name="name", length = 50,nullable = false)
    public String name;

    public void setId(Integer id) {

        this.id = id;
    }
    public Category() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
