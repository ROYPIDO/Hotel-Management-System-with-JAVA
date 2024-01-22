package com.tpe.service;

import com.tpe.domain.Guest;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import com.tpe.exceptions.ReservationNotFoundException;
import com.tpe.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ReservationService {
    private Scanner scanner=new Scanner(System.in);

    private final ReservationRepository reservationRepository;

    private final GuestService guestService;

    private final RoomService roomService;


    public ReservationService(ReservationRepository reservationRepository, GuestService guestService, RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.guestService = guestService;
        this.roomService = roomService;
    }

    //Not : ödev4
    public Reservation findReservationById(Long id) {
        Reservation foundReservation = reservationRepository.findById(id);
        try {
            if (foundReservation != null) {
                System.out.println(foundReservation);
            } else {
                throw new ReservationNotFoundException("Reservation not found with ID: " + id);
            }
        }catch (ReservationNotFoundException e){
            System.out.println(e.getMessage() );
        }
        return foundReservation;
    }
    //Not : ödev5
    public void findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("There is no reservation...");
        }
    }

    public void createReservation() {

        Reservation reservation=new Reservation();

        System.out.println("Enter check-in date (yyyy-MM-dd) : ");
        LocalDate checkin=LocalDate.parse(scanner.nextLine());

        System.out.println("Enter check-out date (yyyy-MM-dd): ");
        LocalDate checkout=LocalDate.parse(scanner.nextLine());

        System.out.println("Enter room id : ");
        Long roomId=scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter guest id : ");
        Long guestId= scanner.nextLong();
        scanner.nextLine();

        Guest guest=guestService.findGuestById(guestId);
        Room room=roomService.findRoomById(roomId);

        reservation.setCheckIn(checkin);
        reservation.setCheckOut(checkout);
        reservation.setGuest(guest);
        reservation.setRoom(room);

        reservationRepository.saveReservation(reservation);
        System.out.println("Reservation is created successfully...Reservation id : "+reservation.getId());



    }
    public void deleteReservationById(Long id) {
        Reservation existingReservation=findReservationById(id);

        if (existingReservation!=null){

            reservationRepository.deleteReservation(existingReservation);
            System.out.println("Reservation is cancelled succesfully.. Reservation id: "+existingReservation.getId());

        }
    }
}
