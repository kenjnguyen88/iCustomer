package vn.esoft.platform.icustomer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class JSonUtils {

    private static ObjectMapper objectMapper;

    static {
        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        }
    }

    public static String toJson(Object object) {
        String tmp = "";
        try {
            if (object != null) {
                tmp = objectMapper.writeValueAsString(object);
            }
        } catch (Exception e) {
            log.error("Mapping Object to Json was failed {}", e.getMessage());
        }
        return tmp;
    }

    public static Object toObject(String json, Class<?> clazz) {
        Object object = null;
        try {
            object = objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("Mapping Json to Object was failed {}", e.getMessage());
        }
        return object;
    }

    public static List<Map<String, Object>> toMap(String json) {
        List<Map<String, Object>> map = null;
        try {
            map = Arrays.asList(objectMapper.readValue(json, new TypeReference<Map<String, Object>[]>() {
            }));
        } catch (JsonProcessingException e) {
            log.error("Mapping Json to Map was failed {}", e.getMessage());
        }
        return map;
    }
}
