package com.tpe.service;

import com.tpe.domain.Address;
import com.tpe.domain.Guest;
import com.tpe.exceptions.GuestNotFoundException;
import com.tpe.repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

    private Scanner scanner=new Scanner(System.in);

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    //Not : ödev2
    public Guest findGuestById(Long id) {
        try {
            Guest foundGuest = guestRepository.findById(id);
            if(foundGuest!=null){
                System.out.println("*-----------------------------*");
                System.out.println(foundGuest);
                return foundGuest;
            }else{
                throw new GuestNotFoundException("Guest not found with id : "+id);
            }
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    //Not : ödev3
    public void findAllGuest() {
        List<Guest> guests = guestRepository.findAll();
        if (!guests.isEmpty()) {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        } else {
            System.out.println("Guest not found");
        }
    }

    public void saveGuest() {

        Guest guest=new Guest();
        //id otomatik generate edilecek

        System.out.println("Enter guest name : ");
        guest.setName(scanner.nextLine());

        Address address=new Address();
        System.out.println("Enter street : ");
        address.setStreet(scanner.nextLine());

        System.out.println("Enter city : ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter country : ");
        address.setCountry(scanner.nextLine());

        System.out.println("Enter zipcode : ");
        address.setZipcode(scanner.nextInt());
        scanner.nextLine();

        guest.setAddress(address);

        guestRepository.save(guest);
        System.out.println("Guest is saved successfully... Guest id : "+guest.getId());

    }


    public void deleteGuest(Long id) {

        Guest foundGuest=findGuestById(id);

        if (foundGuest!=null){
           guestRepository.delete(foundGuest);
            System.out.println("Guest is removed successfully... Guest id: "+foundGuest.getId());
        }
    }
}
