package vn.esoft.platform.icustomer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import vn.esoft.platform.icustomer.controllers.request.AssignRoleRequest;
import vn.esoft.platform.icustomer.controllers.response.CustomerInfoResponse;
import vn.esoft.platform.icustomer.controllers.response.PageData;
import vn.esoft.platform.icustomer.entities.CustomerEntity;
import vn.esoft.platform.icustomer.entities.CustomerRoleEntity;
import vn.esoft.platform.icustomer.entities.CustomerRolePermissionEntity;
import vn.esoft.platform.icustomer.entities.RoleEntity;
import vn.esoft.platform.icustomer.repositories.CustomerRolePermissionRepository;
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
    private final CustomerRolePermissionRepository customerRolePermissionRepository;
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

    public PageData<Object> fetchAllUser(PageRequest pageRequest) {

        List<CustomerInfoResponse> data = new ArrayList<>();
        Page pageCustomers =  userRepository.findAll(pageRequest);
        List<CustomerEntity> customerEntities = pageCustomers.get().toList();
        customerEntities.forEach(o -> {
            List<CustomerRolePermissionEntity> permissionEntities = customerRolePermissionRepository.findByCustomerId(o.getId());
            List<String> roles = permissionEntities.stream().map(CustomerRolePermissionEntity::getRoleName).distinct().toList();
            List<String> permissions = permissionEntities.stream().map(CustomerRolePermissionEntity::getPermissionName).toList();

            data.add(CustomerInfoResponse.builder()
                    .email(o.getEmail())
                    .fullName(o.getFullName())
                    .permissions(permissions)
                    .roles(roles)
                    .build());
        });

        return PageData.builder()
                .pageNum(pageRequest.getPageNumber())
                .pageSize(pageRequest.getPageSize())
                .total(pageCustomers.getTotalElements())
                .data(data)
                .build();
    }

    public CustomerEntity fetchCustomerInfo(final String username) {
        Optional<CustomerEntity> optCust = userRepository.findByEmail(username);
        if (optCust.isPresent()) {
            List<CustomerRolePermissionEntity> permissionEntities = customerRolePermissionRepository.findByCustomerId(optCust.get().getId());

            List<String> roles = permissionEntities.stream().map(CustomerRolePermissionEntity::getRoleName).distinct().toList();
            List<String> permissions = permissionEntities.stream().map(CustomerRolePermissionEntity::getPermissionName).toList();

            optCust.get().setRoles(roles);
            optCust.get().setPermissions(permissions);
            return optCust.get();
        }
        return null;
    }
}
