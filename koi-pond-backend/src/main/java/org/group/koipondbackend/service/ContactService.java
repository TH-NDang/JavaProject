package org.group.koipondbackend.service;

import lombok.RequiredArgsConstructor;
import org.group.koipondbackend.dto.contact.ContactDTO;
import org.group.koipondbackend.dto.contact.CreateContactRequest;
import org.group.koipondbackend.dto.contact.UpdateContactRequest;
import org.group.koipondbackend.entity.Contact;
import org.group.koipondbackend.exception.ResourceNotFoundException;
import org.group.koipondbackend.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public Page<ContactDTO> getAllContacts(PageRequest pageRequest, String search, String status) {
        Specification<Contact> spec = Specification.where(null);

        if (StringUtils.hasText(search)) {
            spec = spec.and((root, query, builder) -> builder.or(
                    builder.like(builder.lower(root.get("name")), "%" + search.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("email")), "%" + search.toLowerCase() + "%"),
                    builder.like(builder.lower(root.get("phone")), "%" + search.toLowerCase() + "%")
            ));
        }

        if (StringUtils.hasText(status)) {
            spec = spec.and((root, query, builder) -> builder.equal(root.get("status"), status));
        }

        return contactRepository.findAll(spec, pageRequest)
                .map(this::mapToDTO);
    }

    public ContactDTO getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));
        return mapToDTO(contact);
    }

    public ContactDTO createContact(CreateContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .subject(request.getSubject())
                .message(request.getMessage())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return mapToDTO(contactRepository.save(contact));
    }

    public ContactDTO updateContact(Long id, UpdateContactRequest request) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found"));

        contact.setStatus(request.getStatus());
        contact.setNotes(request.getNotes());
        contact.setUpdatedAt(LocalDateTime.now());

        return mapToDTO(contactRepository.save(contact));
    }

    public void deleteContact(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new ResourceNotFoundException("Contact not found");
        }
        contactRepository.deleteById(id);
    }

    private ContactDTO mapToDTO(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setEmail(contact.getEmail());
        dto.setPhone(contact.getPhone());
        dto.setSubject(contact.getSubject());
        dto.setMessage(contact.getMessage());
        dto.setStatus(contact.getStatus());
        dto.setNotes(contact.getNotes());
        dto.setCreatedAt(contact.getCreatedAt());
        dto.setUpdatedAt(contact.getUpdatedAt());
        return dto;
    }
}
