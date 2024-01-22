package com.tpe.domain;

//ödev 2:  fieldları ve getter/setter metodlarını yazalım
//Guest: Long id,String name,LocalDateTime createDate, List<Reservation> reservations


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_guest")
public class Guest {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "guest",orphanRemoval = true)
    private List<Reservation> reservations=new ArrayList<>();


    //getter-setter

    @PrePersist
    public void prePersist(){//bu metodu guest tabloya eklenme anında çağırmamız gerekiyor.
        this.createDate=LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

/*    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }*/

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    //toString

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
