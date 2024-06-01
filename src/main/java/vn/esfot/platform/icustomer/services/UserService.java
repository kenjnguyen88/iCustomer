package vn.esfot.platform.icustomer.services;

import vn.esfot.platform.icustomer.entities.customer;
import vn.esfot.platform.icustomer.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<customer> allUsers() {
        List<customer> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
