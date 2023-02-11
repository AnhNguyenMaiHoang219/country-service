package com.nordea.countries.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class JsonUtil {

    public final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public <T> T readJsonFile(String file, Class<T> clazz) {
        try (InputStream is = JsonUtil.class.getResourceAsStream(file)) {
            return OBJECT_MAPPER.readValue(is, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public <T> T readJsonString(String jsonString, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
