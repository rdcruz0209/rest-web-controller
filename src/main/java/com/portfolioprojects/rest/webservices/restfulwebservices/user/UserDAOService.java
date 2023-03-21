package com.portfolioprojects.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Component
public class UserDAOService {

//    JPA/Hibertnate -> Database
//    UserDAOService -> Static List then we'll convert this to database
//    public List<User> findAll()
//    public User save (User user)
//    public User findAUser(int id)

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount, "Robert", LocalDate.now().minusYears(27)));
        users.add(new User(++userCount, "Claire", LocalDate.now().minusYears(21)));
        users.add(new User(++userCount, "Baby", LocalDate.now().minusYears(0)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId() == id;
        System.out.println(predicate);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

}
