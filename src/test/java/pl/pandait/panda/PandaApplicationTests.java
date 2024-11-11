package pl.pandait.panda;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PandaApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl = "/messages";

    @BeforeEach
    public void setUp() {
        // Este método puede usarse para inicializar datos comunes antes de cada prueba si es necesario.
    }

    @Test
    public void testCreateMessage() {
        String message = "Hello, World!";
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "/create", message, String.class);
        assertTrue(response.getBody().contains("Message created with ID"));
    }

    @Test
    public void testGetMessage() {
        String message = "Test Message";
        ResponseEntity<String> createResponse = restTemplate.postForEntity(baseUrl + "/create", message, String.class);
        String messageId = createResponse.getBody().split(": ")[1];

        ResponseEntity<String> getResponse = restTemplate.getForEntity(baseUrl + "/" + messageId, String.class);
        assertEquals("Test Message", getResponse.getBody());
    }

    @Test
    public void testUpdateMessage() {
        String message = "Old Message";
        ResponseEntity<String> createResponse = restTemplate.postForEntity(baseUrl + "/create", message, String.class);
        String messageId = createResponse.getBody().split(": ")[1];

        String newMessage = "Updated Message";
        HttpEntity<String> requestEntity = new HttpEntity<>(newMessage);
        ResponseEntity<String> updateResponse = restTemplate.exchange(baseUrl + "/" + messageId, HttpMethod.PUT, requestEntity, String.class);
        assertEquals("Message updated for ID: " + messageId, updateResponse.getBody());

        ResponseEntity<String> getResponse = restTemplate.getForEntity(baseUrl + "/" + messageId, String.class);
        assertEquals(newMessage, getResponse.getBody());
    }

    @Test
    public void testDeleteMessage() {
        String message = "Message to be deleted";
        ResponseEntity<String> createResponse = restTemplate.postForEntity(baseUrl + "/create", message, String.class);
        String messageId = createResponse.getBody().split(": ")[1];

        ResponseEntity<String> deleteResponse = restTemplate.exchange(baseUrl + "/" + messageId, HttpMethod.DELETE, null, String.class);
        assertEquals("Message deleted for ID: " + messageId, deleteResponse.getBody());

        ResponseEntity<String> getResponse = restTemplate.getForEntity(baseUrl + "/" + messageId, String.class);
        assertEquals("Message not found", getResponse.getBody());
    }
}

