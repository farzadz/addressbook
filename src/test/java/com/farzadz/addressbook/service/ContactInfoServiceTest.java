package com.farzadz.addressbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class ContactInfoServiceTest {

  @Autowired
  private ContactInfoService contactInfoService;

  @Autowired
  private PersonService personService;

  private Person person;

  @BeforeEach
  public void setup() {
    Person person1 = new Person();
    person1.setName("Alex");
    this.person = personService.create(person1);
  }

  @Test
  public void create() {
    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setPhone("1234");
    contactInfo.setDescription("desc");
    contactInfo.setPerson(person);

    ContactInfo contactInfoInDb = contactInfoService.create(contactInfo);
    assertNotNull(contactInfo.getId());
    assertEquals(contactInfo.getPhone(), contactInfoInDb.getPhone());
    assertEquals(contactInfo.getPerson(), contactInfoInDb.getPerson());
    assertEquals(contactInfo.getDescription(), contactInfoInDb.getDescription());

  }

  @Test
  public void findById() {
    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setPhone("1234");
    contactInfo.setDescription("desc");
    contactInfo.setPerson(person);

    Long contactInfoDbId = contactInfoService.create(contactInfo).getId();
    ContactInfo returnedContactInfo = contactInfoService.findById(contactInfoDbId);
    assertEquals(contactInfo.getPhone(), returnedContactInfo.getPhone());
    assertEquals(contactInfo.getDescription(), returnedContactInfo.getDescription());
    assertEquals(contactInfo.getPerson().getId(), returnedContactInfo.getPerson().getId());
  }

  @Test
  public void findAll() {
    ContactInfo contactInfo1 = new ContactInfo();
    contactInfo1.setPhone("1234");
    contactInfo1.setDescription("desc");
    contactInfo1.setPerson(person);

    ContactInfo contactInfo2 = new ContactInfo();
    contactInfo2.setPhone("4321");
    contactInfo2.setDescription("DESC");
    contactInfo2.setPerson(person);

    contactInfoService.create(contactInfo1);
    contactInfoService.create(contactInfo2);

    List<ContactInfo> allContacts = contactInfoService.findAll();

    assertTrue(allContacts.stream().anyMatch(
        contactInfo -> contactInfo.getPhone().equals(contactInfo1.getPhone()) && contactInfo.getDescription()
            .equals(contactInfo1.getDescription()) && contactInfo.getPerson().getId()
            .equals(contactInfo1.getPerson().getId())));
    assertTrue(allContacts.stream().anyMatch(
        contactInfo -> contactInfo.getPhone().equals(contactInfo2.getPhone()) && contactInfo.getDescription()
            .equals(contactInfo2.getDescription()) && contactInfo.getPerson().getId()
            .equals(contactInfo2.getPerson().getId())));

  }

  @Test
  public void updateById() {
    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setPhone("1234");
    contactInfo.setDescription("desc");
    contactInfo.setPerson(person);

    Long contactInfoDbId = contactInfoService.create(contactInfo).getId();

    Person newPerson = new Person();
    newPerson.setName("Jack");
    newPerson = personService.create(newPerson);

    ContactInfo newContactInfo = new ContactInfo();
    newContactInfo.setPhone("4321");
    newContactInfo.setDescription("other desc");
    newContactInfo.setPerson(newPerson);

    ContactInfo updatedContactInfo = contactInfoService.updateById(contactInfoDbId, newContactInfo);

    assertEquals(newContactInfo.getPerson(), newPerson);
    assertEquals(newContactInfo.getPhone(), updatedContactInfo.getPhone());
    assertEquals(newContactInfo.getDescription(), updatedContactInfo.getDescription());

  }

  @Test
  public void deleteById() {

    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setPhone("1234");
    contactInfo.setDescription("desc");
    contactInfo.setPerson(person);
    ContactInfo contactInfoInDb = contactInfoService.create(contactInfo);

    contactInfoService.deleteById(contactInfoInDb.getId());

    assertTrue(contactInfoService.findAll().stream().noneMatch(c -> c.getId().equals(contactInfoInDb.getId())));
  }

  @Test
  public void findAllContactInfoForPerson() {

    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setPhone("1234");
    contactInfo.setDescription("desc");
    contactInfo.setPerson(person);

    contactInfoService.create(contactInfo);
    List<ContactInfo> allContactInfoForPerson = contactInfoService.findAllContactInfoForPerson(person.getId());
    assertEquals(1, allContactInfoForPerson.size());
    assertTrue(allContactInfoForPerson.stream().anyMatch(
        c -> c.getDescription().equals(contactInfo.getDescription()) && c.getPhone().equals(contactInfo.getPhone())));
  }

}