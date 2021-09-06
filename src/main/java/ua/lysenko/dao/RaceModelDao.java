package ua.lysenko.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ua.lysenko.entity.RaceModel;
import ua.lysenko.utils.HibernateUtil;

public class RaceModelDao {
    public void saveRaceModel(RaceModel raceModel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(raceModel);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public RaceModel getRaceModel(long id) {

        Transaction transaction = null;
        RaceModel raceModel = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            raceModel = session.get(RaceModel.class, id);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return raceModel;
    }

//    public int getLastRaceNumber() {
//
//        Transaction transaction = null;
//        int totalCalls = 0;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//
//            String hql = "SELECT raceId FROM race r GROUP BY raceId order by count(*) DESC";//TODO
//            Query query = session.createQuery(hql);
//            Object callRecords = query.getSingleResult();
//            totalCalls = Integer.parseInt(String.valueOf(callRecords));
//
//            transaction.commit();
//
//        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//        return totalCalls;
//    }

    public void updateRaceModel(RaceModel raceModel) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(raceModel);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteRaceModel(long id) {

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            RaceModel raceModel = session.get(RaceModel.class, id);
            if (raceModel != null) {
                session.delete(raceModel);
                System.out.println("Race model is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
