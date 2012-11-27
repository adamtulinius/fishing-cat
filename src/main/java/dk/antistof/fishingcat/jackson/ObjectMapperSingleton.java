package dk.antistof.fishingcat.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperSingleton {
    private static ObjectMapper objectMapper = null;

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
            objectMapper.configure(MapperFeature.AUTO_DETECT_FIELDS, false);
            objectMapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
            objectMapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        return objectMapper;
    }
}
