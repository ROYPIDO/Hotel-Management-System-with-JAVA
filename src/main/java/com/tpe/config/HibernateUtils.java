package com.tpe.config;

import com.tpe.domain.Guest;
import com.tpe.domain.Hotel;
import com.tpe.domain.Reservation;
import com.tpe.domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {


    private static SessionFactory sessionFactory;

    static{

        try{

            Configuration configuration=new Configuration().
                    configure().//yapılandırma dosyası varsayılan isimde ise parametre opsiyonel olur.
                    addAnnotatedClass(Hotel.class).
                    addAnnotatedClass(Room.class).
                    addAnnotatedClass(Guest.class).
                    addAnnotatedClass(Reservation.class);


           sessionFactory =configuration.buildSessionFactory();

        }catch(Exception e){

            System.err.println("Initialization of session factory is FAILED!!!");

        }
    }

    //getter SF
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //SF kapatalım
    public static void shutDown(){
        getSessionFactory().close();
    }


    //sessionı da kapatalım
    public static void closeSession(Session session){
        if (session!=null && session.isOpen()){
            session.close();
        }
    }


}
