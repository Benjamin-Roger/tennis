package com.tennis.back.driver.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.DefaultResponseCreator;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

public class TestUtils {

    public static String getStringifiedJsonDTO(String source) {
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(source)) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
            return mapper.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringifiedCsvDTO(String source) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(source)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static MockRestServiceServer createMockServer(String field, Object bean, String source, DefaultResponseCreator payload) throws URISyntaxException, NoSuchFieldException, IllegalAccessException {
        MockRestServiceServer server = mockPlayerDataAPI(field, bean);
        server.expect(requestTo(new URI(source)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(payload);
        return server;
    }

    public static MockRestServiceServer mockPlayerDataAPI(String field, Object bean) throws NoSuchFieldException, IllegalAccessException {
        Field reader = bean.getClass().getDeclaredField(field);
        reader.setAccessible(true);
        RestTemplate restTemplate = (RestTemplate) reader.get(bean);
        MockRestServiceServer server = MockRestServiceServer.bindTo(restTemplate).build();
        return server;
    }

    public static String getMalePlayerStringifiedDTO() {
        return getStringifiedCsvDTO("static/players/wiki/atp_players.csv");
    }

    public static String getFemalePlayerStringifiedDTO() {
        return getStringifiedCsvDTO("static/players/wiki/wta_players.csv");
    }
}
