package com.tpe.service;

import com.tpe.domain.Hotel;
import com.tpe.domain.Room;
import com.tpe.exceptions.RoomNotFoundException;
import com.tpe.repository.RoomRepository;

import java.util.List;
import java.util.Scanner;


public class RoomService {

    private Scanner scanner=new Scanner(System.in);

    private final HotelService hotelService/*=new HotelService()*/;

    private final RoomRepository roomRepository;

    public RoomService(HotelService hotelService, RoomRepository roomRepository) {
        this.hotelService = hotelService;
        this.roomRepository = roomRepository;
    }

    public void saveroom(){

        Room room=new Room();

        System.out.println("Enter room id :");
        room.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.println("Enter room number :");//A100
        room.setNumber(scanner.nextLine());

        System.out.println("Enter room capacity :");
        room.setCapacity(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Enter hotel id : ");
        Long hotelId=scanner.nextLong();

        //girilen id hangi otele ait
        Hotel hotel=hotelService.findHotelById(hotelId);
        if (hotel!=null){
          room.setHotel(hotel);//hangi odaya ait olduğu set edildi

          //  hotel.getRooms().add(room);

          roomRepository.save(room);//oda tabloya ekle
            System.out.println("Room is saved successfully. Room id : "+room.getId());
        }

    }

    // Ödev 1-room servicede : findRoomById(Long id), findAllRoom() metodlarını yazalım

    public Room findRoomById(Long id){
        try {
            Room foundRoom = roomRepository.findById(id);
            if (foundRoom != null) {
                System.out.println("---------------------------------");
                System.out.println(foundRoom);
                System.out.println("---------------------------------");
                return foundRoom;
            } else {
                throw new RoomNotFoundException(" Room  not found with ID: " + id);
            }
        } catch (RoomNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Ödev
    public void findAllRooms(){
        List<Room> rooms = roomRepository.findAll();
        if (!rooms.isEmpty()) {
            for (Room room : rooms) {
                System.out.println(room);
            }
        } else {
            System.out.println("No rooms found!");
        }
    }

    //Not : ödev1
    public void deleteRoomById(Long id) {
        Room existingRoom =findRoomById(id);
        if (existingRoom != null) {
            roomRepository.deleteById(existingRoom);
            System.out.println("Room  deleted successfully. ID: " + id);
        }


    }
}
