package com.example.contactmanager.repository;

import com.example.contactmanager.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ContactRepository {
    private final ConcurrentHashMap<Long, Contact> contacts = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Contact> findAll() {
        return new ArrayList<>(contacts.values());
    }

    public Optional<Contact> findById(Long id) {
        return Optional.ofNullable(contacts.get(id));
    }

    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            contact.setId(idGenerator.getAndIncrement());
        }
        contacts.put(contact.getId(), contact);
        return contact;
    }

    public void deleteById(Long id) {
        contacts.remove(id);
    }

    public boolean existsById(Long id) {
        return contacts.containsKey(id);
    }
}

