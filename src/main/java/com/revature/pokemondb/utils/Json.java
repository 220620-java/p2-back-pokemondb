package com.revature.pokemondb.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;


import org.springframework.beans.factory.annotation.Autowired;

public class Json {

    private Json () {}

    private static ObjectMapper objectMapper = createObjectMapper();
    @Autowired
    private static ObjectMapper createObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    /**
     *
     * @param js
     * @return
     * @throws JsonMappingException
     * @throws JsonProcessingException
     */
    public static JsonNode parse(String js) throws JsonProcessingException {
        return objectMapper.readTree(js);
    }

    /**
     *
     * @param <O>
     * @param node
     * @param classObj
     * @return
     * @throws JsonProcessingException
     * @throws IllegalArgumentException
     */
    public static <O> O fromJson(JsonNode node, Class<O> classObj) throws JsonProcessingException, IllegalArgumentException {
        return objectMapper.treeToValue(node, classObj);
    }

    /**
     *
     * @param classObj
     * @return
     */
    public static JsonNode toJson(Object classObj) {
        return objectMapper.valueToTree(classObj);
    }

    /**
     *
     * @param node
     * @return
     * @throws JsonProcessingException
     */
    public static String nodeToString(JsonNode node) throws JsonProcessingException {
        return generateString(node, false);
    }

    /**
     *
     * @param node
     * @return
     * @throws JsonProcessingException
     */
    public static String nodeToFormatedString(JsonNode node) throws JsonProcessingException {
        return generateString(node, true);
    }

    private static String generateString(JsonNode node, boolean type) throws JsonProcessingException {
        ObjectWriter objectWriter = objectMapper.writer();

        if ( type ) {
            objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);

    }
}
