package pl.pandait.panda;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/messages")
public class HelloController {

    private Map<Integer, String> messages = new HashMap<>();
    private int currentId = 1;

    @PostMapping("/create")
    public String createMessage(@RequestBody String message) {
        messages.put(currentId, message);
        return "Message created with ID: " + currentId++;
    }

    @GetMapping("/{id}")
    public String getMessage(@PathVariable int id) {
        return messages.getOrDefault(id, "Message not found");
    }

    @PutMapping("/{id}")
    public String updateMessage(@PathVariable int id, @RequestBody String newMessage) {
        if (messages.containsKey(id)) {
            messages.put(id, newMessage);
            return "Message updated for ID: " + id;
        } else {
            return "Message not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable int id) {
        if (messages.containsKey(id)) {
            messages.remove(id);
            return "Message deleted for ID: " + id;
        } else {
            return "Message not found";
        }
    }
}


