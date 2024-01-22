package com.tpe.repository;

import com.tpe.config.HibernateUtils;
import com.tpe.domain.Guest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GuestRepository {

    private Session session;

    //Not : ödev2
    public Guest findById(Long id) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Guest.class,id);

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    //Not : ödev3
    public List<Guest> findAll() {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Guest> guests = session.createQuery("FROM Guest", Guest.class).getResultList();
            return guests;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void save(Guest guest) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();
            session.save(guest);

            tx.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void delete(Guest guest) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction tx =session.beginTransaction();
            session.delete(guest);

            tx.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
