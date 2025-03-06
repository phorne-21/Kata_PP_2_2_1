package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   List<User> getUsersByCarModelAndCarSeries (String model, int series);
   User getUserByCarModelAndCarSeries (String model, int series);
}
