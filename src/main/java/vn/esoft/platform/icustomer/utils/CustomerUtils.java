package vn.esoft.platform.icustomer.utils;

import vn.esoft.platform.icustomer.model.entities.CustomerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerUtils {

    public static Map<String, Object> claims(CustomerEntity customer, List<String> roles, List<String> permissions) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customer.getId());
        claims.put("email", customer.getEmail());
        claims.put("fullName", customer.getFullName());
        claims.put("roles", roles);
        claims.put("permissions", permissions);
        return claims;
    }

    public static Map<String, Object> claims(CustomerEntity customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customer.getId());
        claims.put("email", customer.getEmail());
        claims.put("fullName", customer.getFullName());
        claims.put("roles", customer.getRoles());
        claims.put("permissions", customer.getPermissions());
        return claims;
    }
}
