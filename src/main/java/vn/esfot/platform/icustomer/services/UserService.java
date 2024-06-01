package vn.esfot.platform.icustomer.services;

import vn.esfot.platform.icustomer.entities.CustomerEntity;
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

    public List<CustomerEntity> allUsers() {
        List<CustomerEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
