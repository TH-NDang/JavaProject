package org.group.koipondbackend.service;

import org.group.koipondbackend.dto.contact.ContactDTO;
import org.group.koipondbackend.dto.contact.CreateContactRequest;
import org.group.koipondbackend.dto.contact.UpdateContactRequest;
import org.group.koipondbackend.entity.Contact;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllContacts() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Contact contact = new Contact();
        contact.setName("John Doe");
        contact.setEmail("john.doe@example.com");
        contact.setPhone("1234567890");
        Page<Contact> contactPage = new PageImpl<>(Collections.singletonList(contact));

        when(contactRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(contactPage);

        Page<ContactDTO> result = contactService.getAllContacts(pageRequest, "john", "ACTIVE");

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(contactRepository, times(1)).findAll(any(Specification.class), any(PageRequest.class));
    }

    @Test
    void testGetContactById() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("John Doe");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));

        ContactDTO result = contactService.getContactById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testGetContactById_NotFound() {
        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contactService.getContactById(1L));
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateContact() {
        CreateContactRequest request = new CreateContactRequest();
        request.setName("John Doe");
        request.setEmail("john.doe@example.com");
        request.setPhone("1234567890");
        request.setSubject("Subject");
        request.setMessage("Message");

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("John Doe");

        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        ContactDTO result = contactService.createContact(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void testUpdateContact() {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setStatus("UPDATED");
        request.setNotes("Notes");

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setStatus("PENDING");

        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        ContactDTO result = contactService.updateContact(1L, request);

        assertNotNull(result);
        assertEquals("UPDATED", result.getStatus());
        verify(contactRepository, times(1)).findById(1L);
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void testUpdateContact_NotFound() {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setStatus("UPDATED");
        request.setNotes("Notes");

        when(contactRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> contactService.updateContact(1L, request));
        verify(contactRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteContact() {
        when(contactRepository.existsById(1L)).thenReturn(true);

        contactService.deleteContact(1L);

        verify(contactRepository, times(1)).existsById(1L);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteContact_NotFound() {
        when(contactRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> contactService.deleteContact(1L));
        verify(contactRepository, times(1)).existsById(1L);
    }
}
