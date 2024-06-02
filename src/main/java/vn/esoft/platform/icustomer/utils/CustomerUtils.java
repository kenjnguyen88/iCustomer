package vn.esoft.platform.icustomer.utils;

import vn.esoft.platform.icustomer.entities.CustomerEntity;

import java.util.HashMap;
import java.util.Map;

public class CustomerUtils {

    public static Map<String, Object> claims(CustomerEntity customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customer.getId());
        claims.put("email", customer.getEmail());
        claims.put("fullName", customer.getFullName());
        return claims;
    }
}
