package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
      UserService userService = context.getBean(UserService.class);

      // Создание и добавление пользователей с машинами
      User user1 = new User("Ivan", "Ivanov", "Vanya@gmail.com");
      User user2 = new User("Petr", "Petrov", "Petro@mail.ru");
      User user3 = new User("John", "Smith", "John@bk.ru");
      Car car1 = new Car("KIA", 5);
      Car car2 = new Car("BMW", 10);
      Car car3 = new Car("Mazda", 15);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      // Вывод списка пользователей
      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car: " + user.getCar());
      }

      // Получение пользователей по автомобилю KIA
      printUsersByCar(userService, "KIA", 5);

      // Получение пользователей по автомобилю Audi
      printUsersByCar(userService, "Audi", 20);

      context.close();
   }

   private static void printUsersByCar(UserService userService, String model, int series) {
      List<User> usersWithCar = userService.getUserByCar(model, series);

      if (usersWithCar.isEmpty()) {
         System.out.println("Пользователя с автомобилем " + model + " и серией " + series + " не существует.");
      } else {
         for (User user : usersWithCar) {
            System.out.println(user);
         }
      }
   }
}
