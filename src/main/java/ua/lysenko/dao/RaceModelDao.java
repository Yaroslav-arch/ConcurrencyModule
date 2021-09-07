package ua.lysenko.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.hibernate.query.Query;
import ua.lysenko.entity.RaceModel;
import ua.lysenko.utils.HibernateUtil;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

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

    public List <RaceModel> getInfoAboutRace(long id) {

        Transaction transaction = null;
        List<RaceModel> results = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM RaceModel racemodel WHERE racemodel.raceId = :id order by racemodel.position asc";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            results = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;
    }

    public List <RaceModel> getRacesByHorseId(long id) {

        Transaction transaction = null;
        List<RaceModel> results = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM RaceModel racemodel WHERE racemodel.horseId = :id order by racemodel.date asc";
            Query query = session.createQuery(hql);
            query.setParameter("id",id);
            results = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return results;
    }

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
