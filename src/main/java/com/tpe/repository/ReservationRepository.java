package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepository {
    private Session session;

    //Not : ödev4
    public Reservation findById(Long id) {

        session= HibernateUtils.getSessionFactory().openSession();

        Reservation reservation = session.get(Reservation.class, id);
        HibernateUtils.closeSession(session);
        return reservation;

    }

    //Not : ödev5
    public List<Reservation> findAll() {
         session = HibernateUtils.getSessionFactory().openSession();
        List<Reservation> reservations = session.createQuery("FROM Reservation", Reservation.class).getResultList();
        if (reservations.isEmpty()) {
            System.out.println("Reservation is empty");
        }
        HibernateUtils.closeSession(session);
        return reservations;
    }

    public void saveReservation(Reservation reservation) {
        try{
            session=HibernateUtils.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();

            session.save(reservation);

            tx.commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }

    public void deleteReservation(Reservation reservation) {
        try{
            session=HibernateUtils.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();

            session.delete(reservation);

            tx.commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            HibernateUtils.closeSession(session);
        }

    }




}
