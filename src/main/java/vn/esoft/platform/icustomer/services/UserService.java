package vn.esoft.platform.icustomer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.esoft.platform.icustomer.controllers.request.AssignRoleRequest;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.CustomerRoleEntity;
import vn.esoft.platform.icustomer.entities.RoleEntity;
import vn.esoft.platform.icustomer.repositories.CustomerRoleRepository;
import vn.esoft.platform.icustomer.repositories.RoleRepository;
import vn.esoft.platform.icustomer.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CustomerRoleRepository customerRoleRepository;
    public List<CustomerEntity> allUsers() {
        List<CustomerEntity> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void assignRole(AssignRoleRequest request) {

        Optional<CustomerEntity> optionalCustomer = userRepository.findByEmail(request.getEmail());
        if(!optionalCustomer.isPresent()) throw new RuntimeException("Not found customer");
        Optional<RoleEntity> optRole = roleRepository.findByName(request.getRole().getValue());
        if(!optRole.isPresent()) throw new RuntimeException("Not found role");

        Optional<CustomerRoleEntity> optionalCustomerRole = customerRoleRepository.findByCustomerRole(optionalCustomer.get().getId(),optRole.get().getId());
        if(!optionalCustomerRole.isPresent()) {
            CustomerRoleEntity customerRole = new CustomerRoleEntity(optionalCustomer.get().getId(), optRole.get().getId());
            customerRoleRepository.save(customerRole);
        } else {
            throw new RuntimeException("User and Role is exist!");
        }
    }
}
