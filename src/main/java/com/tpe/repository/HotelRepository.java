package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

//DB ye erişim sağlanan katman
public class HotelRepository {


    private Session session;

    //1-b

    public void save(Hotel hotel){

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.save(hotel);//ınsert ınto t_hotel

            transaction.commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }


    }

    //2-b
    public Hotel findById(Long id) {

        try{
            session=HibernateUtils.getSessionFactory().openSession();
            return session.get(Hotel.class,id);//select * from t_hotel where id=5

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
       return null;
    }

    //3-b

    public List<Hotel> findAll() {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            //select * from t_hotel
            List<Hotel> hotelList=session.createQuery("FROM Hotel",Hotel.class).getResultList();
            return hotelList;

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }
       return null;
    }

    public void deleteHotel(Hotel hotel) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.delete(hotel);

            transaction.commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }


    public void updateHotel(Hotel hotel) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.update(hotel);

            transaction.commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }

}
