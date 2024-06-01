package vn.esfot.platform.icustomer.utils;

import vn.esfot.platform.icustomer.entities.CustomerEntity;

import java.util.HashMap;
import java.util.Map;

public class CustomerUtils {

    public static Map<String, Object> claims(CustomerEntity customer) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", customer.getEmail());
        claims.put("fullName", customer.getFullName());
        return claims;
    }
}
