package vn.esoft.platform.icustomer.utils;

import vn.esoft.platform.icustomer.entities.CustomerEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerUtils {

    public static Map<String, Object> claims(CustomerEntity customer, Map<String, List<String>> scope) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("customerId", customer.getId());
        claims.put("email", customer.getEmail());
        claims.put("fullName", customer.getFullName());
        claims.put("scope", scope);
        return claims;
    }
}
