package test.hibernate;

import java.util.Date;

import org.hibernate.Session;

public class EventManager {
	public static void main(String args[]){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event theEvent = new Event();
        theEvent.setTitle("some title");
        theEvent.setDate(new Date());
        session.save(theEvent);
        session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
	}
}
