package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.exceptions.HotelNotFoundException;
import com.tpe.repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

//ödev:tüm entityler için service ve repository classları hazırlayalım
public class HotelService {

    private Scanner scanner = new Scanner(System.in);

    private final HotelRepository hotelRepository;


    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    //1-add hotel
    public void saveHotel() {

        Hotel hotel = new Hotel();

        System.out.println("Enter hotel ID : ");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter hotel name : ");
        hotel.setName(scanner.nextLine());

        System.out.println("Enter hotel location : ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);

        System.out.println("Hotel saved successfully. Hotel ID : " + hotel.getId());


    }

    //2-find hotel, we want to use later so we change the return type from void to Hotel
    public Hotel findHotelById(Long id) {

        Hotel foundHotel = hotelRepository.findById(id);
        try {
            if (foundHotel != null) {
                System.out.println("---------------------------------------------");
                System.out.println(foundHotel);
                System.out.println("---------------------------------------------");
                return foundHotel;
            } else {
                throw new    HotelNotFoundException("Hotel not found by id : " + id);
            }
        } catch (HotelNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //3-tüm otelleri listeleme
    public void getAllHotels() {

        List<Hotel> hotelList = hotelRepository.findAll();

        if (!hotelList.isEmpty()) {

            System.out.println("----------------- List of Hotels ----------------------------");
            for (Hotel hotel : hotelList) {
                System.out.println(hotel);
            }
            System.out.println("-------------------------------------------------------------");
        } else {
            System.out.println("Hotel list is empty!");
        }
    }


    public void deleteHotelById(Long hotelid) {
        //id si verilen otel var mı?
        Hotel foundHotel = findHotelById(hotelid);

        if (foundHotel != null) {
            System.out.println(foundHotel);
            System.out.println("Are you sure to delete hotel by id: " + foundHotel.getId());
            System.out.println("Please answer with Y or N :");
            String answer = scanner.next();
            scanner.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                hotelRepository.deleteHotel(foundHotel);
                System.out.println("Hotel is deleted successfully...");
            } else {
                System.out.println("Delete operation is cancelled! ");
            }
        }
    }

    //TODO:method bodysi yazılacak
    public void updateHotelById(Long updateId) {
    }
}
