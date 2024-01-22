package com.tpe.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_hotel")//opsiyonel
public class Hotel {//one

    @Id//primary key
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "hotel",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Room> rooms=new ArrayList<>();//many,100,200,300

    //hibernate data çekerken(fetch) default constructorı kullanır.
    public Hotel() {
    }

    //param const
    public Hotel(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }


    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    //toString

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
