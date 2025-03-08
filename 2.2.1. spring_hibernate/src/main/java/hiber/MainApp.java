package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user5 = new User("User5", "Lastname5", "user5@mail.ru");
      user5.setCar(new Car("model1", 1));
      User user6 = new User("User6", "Lastname6", "user6@mail.ru");
      user6.setCar(new Car("model2", 2));
      User user7 = new User("User7", "Lastname7", "user7@mail.ru");
      user7.setCar(new Car("model3", 3));
      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      userService.add(user5);
      userService.add(user6);
      userService.add(user7);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
      }

      List<User> users2 = userService.getUsersByCarModelAndCarSeries("model3", 3);
      for (User user : users2) {
         System.out.println(user.toString());
         System.out.println();
      }

      System.out.println(userService.getUserByCarModelAndCarSeries("model3", 3).toString());

      context.close();
   }
}
