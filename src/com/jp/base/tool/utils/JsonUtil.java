package com.jp.base.tool.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author leven
 */
public class JsonUtil {
    private static ObjectMapper om = new ObjectMapper();

    static {

        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        om.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
    }

    public static <T> T deserializeRequstVo(String param, Class<T> clazz, Class<?> ActualClazz) {
        T t = null;
        try {
            JavaType javaType = getCollectionType(clazz, ActualClazz);
            t = om.readValue(param, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> T readValue(String param, Class<T> clazz) {
        T t = null;
        try {
            t = om.readValue(param, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static byte[] writeValueAsBytes(Object obj) {

        try {
            return om.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String writeValueAsString(Object obj) {

        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return om.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    public static JsonNode readTree(String param) {
        try {
            return om.readTree(param);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String writerWithDefaultPrettyPrinter(Object json) {

        try {
            return om.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


}
