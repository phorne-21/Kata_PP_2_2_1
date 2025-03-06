package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<User> getUsersByCarModelAndCarSeries (String model, int series) {
      String hql = "FROM User u JOIN u.car c" +
              "WHERE c.model = :model AND c.series = :series";
      Query<User> query= sessionFactory.getCurrentSession().createQuery(hql, User.class);
      query.setParameter("model", model)
           .setParameter("series", series);
      return query.getResultList();
   }

   @Override
   public User getUserByCarModelAndCarSeries (String model, int series) {
      List<User> users = getUsersByCarModelAndCarSeries (model, series);
      return users.isEmpty() ? null : users.get(0);
   }
}
