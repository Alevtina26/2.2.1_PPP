package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      // Получаем текущую сессию
      Session session = sessionFactory.getCurrentSession();

      // Используем TypedQuery для безопасного приведения типов
      TypedQuery<User> query = session.createQuery("from User", User.class);

      // Получаем список пользователей
      return query.getResultList(); // Возвращаем список пользователей
   }

   @Override
   public List<User> getUserByCar(String model, int series) {
      // Получаем текущую сессию
      Session session = sessionFactory.getCurrentSession();

      // Используем TypedQuery для безопасного приведения типов
      TypedQuery<User> query = session.createQuery("from User u where u.car.model = :model and u.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);

      // Получаем список пользователей
      List<User> users = query.getResultList(); // Теперь это List<User>

      return users; // Возвращаем список пользователей (может быть пустым)
   }
}
