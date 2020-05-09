import edu.utn.mail.controller.UserController;
import edu.utn.mail.domain.City;
import edu.utn.mail.domain.Country;
import edu.utn.mail.domain.User;
import edu.utn.mail.exceptions.UserAlreadyExistsException;
import edu.utn.mail.exceptions.UserNotexistException;
import edu.utn.mail.exceptions.ValidationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class SpringMain {


    public static void main(String args[]) {
        User u = new User("jose", "juanperez", "1234", "perez", new City(1, "Buenos Aires", new Country(1, "Argentina")));
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            UserController userController = context.getBean("userController", UserController.class);
            User u2 = userController.login("juanperez", "1234");
            User u3 = userController.getUserById(1);

        } catch (UserNotexistException userNotExist) {
            userNotExist.printStackTrace();
        } catch (ValidationException validationException) {
            validationException.printStackTrace();
/*        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }*/

        }
    }
}
