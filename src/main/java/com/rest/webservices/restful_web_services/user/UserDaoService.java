package com.rest.webservices.restful_web_services.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    // JPA/Hibernate > Database
    // UserDaoService > Static List
    private static int usersCount = 0;
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(++usersCount , "Baris", LocalDate.now().minusYears(26)));
        users.add(new User(++usersCount , "Yiğit", LocalDate.now().minusYears(27)));
        users.add(new User(++usersCount , "Gürel", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount , "Erhan", LocalDate.now().minusYears(24)));
    }

    // public List<User> findAll()
    public List<User> findAll() {
        return users;
    }

    // public User save(User user)
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    // public User findOne(int id)
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users
                .stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    public void deleteUserByIdVol1(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        User user = users.stream().filter(predicate).findFirst().orElse(null);

        if (user == null) {
            throw new UserNotFoundException("User not found...");
        }

        users.remove(user);
    }

    public void deleteByIdVol2(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
