package vn.esoft.platform.icustomer.services;

import org.springframework.data.domain.PageRequest;
import vn.esoft.platform.icustomer.controllers.dto.request.AssignRoleRequest;
import vn.esoft.platform.icustomer.controllers.dto.response.PageData;
import vn.esoft.platform.icustomer.entities.CustomerEntity;

public interface IUserService {

    void assignRole(AssignRoleRequest request);

    PageData<Object> fetchAllUser(PageRequest pageRequest);

    CustomerEntity fetchCustomerInfo(String username);
}
