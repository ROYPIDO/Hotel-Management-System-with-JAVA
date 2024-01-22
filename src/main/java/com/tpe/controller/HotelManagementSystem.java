package com.tpe.controller;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import com.tpe.repository.GuestRepository;
import com.tpe.repository.HotelRepository;
import com.tpe.repository.ReservationRepository;
import com.tpe.repository.RoomRepository;
import com.tpe.service.GuestService;
import com.tpe.service.HotelService;
import com.tpe.service.ReservationService;
import com.tpe.service.RoomService;

import java.util.Scanner;

public class HotelManagementSystem {


    private static Scanner scanner = new Scanner(System.in);


    public static void displayMenuHotelManagementSystem() {

        HotelRepository hotelRepository=new HotelRepository();
        HotelService hotelService=new HotelService(hotelRepository);

        RoomRepository roomRepository=new RoomRepository();
        RoomService roomService=new RoomService(hotelService,roomRepository);

        GuestRepository guestRepository=new GuestRepository();
        GuestService guestService=new GuestService(guestRepository);

        ReservationRepository reservationRepository=new ReservationRepository();
        ReservationService reservationService=new ReservationService(reservationRepository,guestService,roomService);

        boolean exit = false;

        while (!exit) {
            System.out.println("========= Hotel Management System =========");
            System.out.println("1. Hotel Operations");
            System.out.println("2. Room Operations");
            System.out.println("3. Guest Operations");
            System.out.println("4. Reservation Operations");
            System.out.println("0. Exit");
            System.out.println("Enter your choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();//yeni satıra geçme komutunu okumak için(nextInt okumaz!)

            switch (choice) {
                case 1:
                    displayHotelOperationsMenu(hotelService);
                    break;
                case 2:
                    displayRoomOperationsMenu(roomService);
                    break;
                case 3:
                    displayGuestOperationsMenu(guestService);
                    break;
                case 4:
                    displayReservationOperationsMenu(reservationService);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Goog bye...");
                    HibernateUtils.shutDown();
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
                    break;
            }
        }


    }


    private static void displayHotelOperationsMenu(HotelService hotelService) {

        System.out.println("Hotel Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Hotel Operations ====");
            System.out.println("1. Add a new hotel");
            System.out.println("2. Find Hotel By ID");
            System.out.println("3. Delete Hotel By ID");
            System.out.println("4. Find All Hotels");
            System.out.println("5. Update Hotel By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //service saveHotel metodu çağırıcaz
                    hotelService.saveHotel();

                    break;
                case 2:
                    System.out.println("Enter hotel ID : ");
                    Long id= scanner.nextLong();
                    scanner.nextLine();
                    hotelService.findHotelById(id);
                    break;
                case 3:
                    System.out.println("Enter hotel ID to delete : ");
                    Long hotelid= scanner.nextLong();
                    scanner.nextLine();

                    hotelService.deleteHotelById(hotelid);
                    break;
                case 4:
                    //tüm oteller
                    hotelService.getAllHotels();
                    break;
                case 5:
                    System.out.println("Enter hotel ID to update : ");
                    Long updateId= scanner.nextLong();
                    scanner.nextLine();

                    hotelService.updateHotelById(updateId);
                    break;
                case 0:
                    exit = true;
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //room operations
    private static void displayRoomOperationsMenu(RoomService roomService) {

       // RoomService roomService=new RoomService();

        System.out.println("Room Operation Menu");
        boolean exit = false;
        while (!exit) {
            System.out.println("==== Room Operations ====");
            System.out.println("1. Add a new room");
            System.out.println("2. Find Room By ID");
            System.out.println("3. Delete Room By ID");
            System.out.println("4. Find All Rooms");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    //saveRoom
                    roomService.saveroom();
                    break;
                case 2:
                    System.out.println("Enter room ID: ");
                    Long roomId=scanner.nextLong();
                    scanner.nextLine();

                    roomService.findRoomById(roomId);
                    break;
                case 3:
                    System.out.print("Enter the room ID to delete: ");
                    Long roomIdToDelete = scanner.nextLong();
                    scanner.nextLine();
                    roomService.deleteRoomById(roomIdToDelete);
                    break;
                case 4:
                    roomService.findAllRooms();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }

    //guest operations
    private static void displayGuestOperationsMenu(GuestService guestService) {
        System.out.println("Guest Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Guest Operations ====");
            System.out.println("1. Add a new guest");
            System.out.println("2. Find Guest By ID");
            System.out.println("3. Delete Guest By ID");
            System.out.println("4. Find All Guests");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    guestService.saveGuest();
                    break;
                case 2:
                    System.out.print("Enter the Guest ID :");
                    Long guestId = scanner.nextLong();
                    scanner.nextLine();
                    guestService.findGuestById(guestId);
                    break;
                case 3:
                    System.out.print("Enter the Guest ID to delete :");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    guestService.deleteGuest(id);
                    break;
                case 4:
                    guestService.findAllGuest();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    //reservation operations
    private static void displayReservationOperationsMenu(ReservationService reservationService) {
        System.out.println("Reservation Operation Menu");

        boolean exit = false;
        while (!exit) {
            System.out.println("==== Reservation Operations ====");
            System.out.println("1. Add a new reservation");
            System.out.println("2. Find Reservation By ID");
            System.out.println("3. Find All Reservations");
            System.out.println("4. Delete Reservation By ID");
            System.out.println("0. Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    reservationService.createReservation();
                    break;
                case 2:

                    System.out.print("Enter the reservation ID: ");
                    Long reservationId = scanner.nextLong();
                    scanner.nextLine();

                    reservationService.findReservationById(reservationId);
                    break;
                case 3:

                    reservationService.findAllReservations();
                    break;
                case 4:
                    System.out.print("Enter the reservation ID to delete : ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    reservationService.deleteReservationById(id);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }


    }

}
