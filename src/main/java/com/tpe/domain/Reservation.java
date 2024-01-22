package com.tpe.domain;


import javax.persistence.*;
import java.time.LocalDate;

//ödev 2:  fieldlarını ve getter/setter yazalım
//Reservation: Long id,LocalDate checkIn,LocalDate checkOut,Guest guest,Room room

@Entity
@Table(name = "t_reservation")
public class Reservation {


    @Id
    @GeneratedValue(generator = "seq",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq",
                       sequenceName = "reservation_id_seq",
                        initialValue = 1000,
                        allocationSize = 5)//1000,1001,1002,1003,1004,1005
    private Long id;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Guest guest;//guest_id

    @ManyToOne
    @JoinColumn(nullable = false)
    private Room room;

    //getter-setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    //toString

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", room=" + room +
                '}';
    }
}
