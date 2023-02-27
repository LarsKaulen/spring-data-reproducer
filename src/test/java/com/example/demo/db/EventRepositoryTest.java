package com.example.demo.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotEquals;

@SpringBootTest(classes = {RepositoryTestConfiguration.class})
class EventRepositoryTest {

    @Autowired
    private EventRepository repository;

    @Test
    void test() {
        Event toSave = Event.builder().timestamp(Instant.now()).build();
        Event saved = repository.save(toSave);
        Event found = repository.findById(saved.getId()).orElse(null);
        assert found != null;

        System.out.println("Given timestamp:                       " + toSave.getTimestamp());
        System.out.println("Returned timestamp by save method:     " + saved.getTimestamp());
        System.out.println("Returned timestamp by findById method: " + found.getTimestamp());

        assertEquals("Timestamp returned by save", found.getTimestamp(), saved.getTimestamp());
        assertNotEquals("Timestamp returned by save", toSave.getTimestamp(), saved.getTimestamp());
    }
}
