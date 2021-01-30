package com.farzadz.addressbook.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class PersonServiceTest {

  @Autowired
  private PersonService personService;

  @Autowired
  private ContactInfoService contactInfoService;

  @Autowired
  private AddressBookService addressBookService;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void create() {
    Person person = new Person();
    person.setName("Alex");

    Person returnedPerson = personService.create(person);

    assertNotNull(returnedPerson.getId());
    assertEquals(person.getName(), returnedPerson.getName());

  }

  @Test
  void findById() {
    Person person = new Person();
    person.setName("Alex");
    person.setDescription("Desc");

    Person savedPerson = personService.create(person);

    Long persistedId = savedPerson.getId();

    Person foundPerson = personService.findById(persistedId);
    assertEquals(person.getName(), foundPerson.getName());
    assertEquals(person.getDescription(), foundPerson.getDescription());
  }

  @Test
  void findAll() {

    Person person1 = new Person();
    person1.setName("Al");

    Person person2 = new Person();
    person2.setName("Joe");

    Person person1InDb = personService.create(person1);
    Person person2InDb = personService.create(person2);

    List<Person> allPeople = personService.findAll();
    assertTrue(allPeople.stream().anyMatch(person -> person.getName().equals(person1.getName())));

    assertTrue(allPeople.stream().anyMatch(person -> person.getName().equals(person2.getName())));

    assertTrue(allPeople.indexOf(person1InDb) < allPeople.indexOf(person2InDb));
  }

  @Test
  void updateById() {
    Person person = new Person();
    person.setName("Alex");
    person.setDescription("Desc");

    Person savedPerson = personService.create(person);
    Long persistedId = savedPerson.getId();

    Person updatedPerson = new Person();
    person.setName("Jacky");
    person.setDescription("New Desc");

    Person updatedPersonInDB = personService.updateById(persistedId, updatedPerson);

    assertEquals(persistedId, updatedPersonInDB.getId());
    assertEquals(updatedPerson.getName(), updatedPersonInDB.getName());
    assertEquals(updatedPerson.getDescription(), updatedPersonInDB.getDescription());

  }

  @Test
  void deleteById() {

    Person person = new Person();
    person.setName("Alex");

    Person savedPerson = personService.create(person);

    Long persistedId = savedPerson.getId();

    personService.deleteById(persistedId);

    assertTrue(personService.findAll().stream().noneMatch(p -> p.getId().equals(persistedId)));

  }

  @Test
  void getAllContactInfo() {
    Person person = new Person();
    person.setName("Alex");

    Person savedPerson = personService.create(person);

    Long personId = savedPerson.getId();

    ContactInfo contactInfo1 = new ContactInfo();
    contactInfo1.setPerson(person);
    contactInfo1.setPhone("1234");
    contactInfo1.setDescription("Desc1");

    ContactInfo contactInfo2 = new ContactInfo();
    contactInfo2.setPerson(person);
    contactInfo2.setPhone("1234654");
    contactInfo2.setDescription("Desc2");

    contactInfoService.create(contactInfo1);
    contactInfoService.create(contactInfo2);

    List<ContactInfo> personContacts = personService.getAllContactInfo(personId);

    assertTrue(personContacts.stream().noneMatch(contactInfo -> contactInfo.getId() == null));
    assertTrue(personContacts.stream().anyMatch(
        contactInfo -> contactInfo.getPhone().equals(contactInfo1.getPhone()) && contactInfo.getDescription()
            .equals(contactInfo1.getDescription())));
    assertTrue(personContacts.stream().anyMatch(
        contactInfo -> contactInfo.getPhone().equals(contactInfo2.getPhone()) && contactInfo.getDescription()
            .equals(contactInfo2.getDescription())));
  }

  @Test
  public void uniquePeopleToAddressBooks() {
    AddressBook addressBook1 = new AddressBook();
    addressBook1.setName("ADB1");
    AddressBook addressBook2 = new AddressBook();
    addressBook2.setName("ADB2");

    Long adb1Id = addressBookService.create(addressBook1).getId();
    Long adb2Id = addressBookService.create(addressBook2).getId();

    List<Person> uniquePeople1 = personService.uniquePeopleToAddressBooks(adb1Id, adb2Id);
    List<Person> uniquePeople2 = personService.uniquePeopleToAddressBooks(adb2Id, adb1Id);

    assertTrue(uniquePeople1.isEmpty());
    assertTrue(uniquePeople2.isEmpty());

    // only in adb1
    Person person1 = new Person();
    person1.setName("Jill");
    person1.addAddressBook(addressBook1);
    Person person1InDb = personService.create(person1);

    List<Person> uniquePeople3 = personService.uniquePeopleToAddressBooks(adb1Id, adb2Id);

    assertEquals(1, uniquePeople3.size());
    assertTrue(uniquePeople3.contains(person1InDb));

    // only in adb2
    Person person2 = new Person();
    person2.setName("Jack");
    person2.addAddressBook(addressBook2);
    Person person2InDb = personService.create(person2);

    List<Person> uniquePeople4 = personService.uniquePeopleToAddressBooks(adb1Id, adb2Id);

    assertEquals(2, uniquePeople4.size());
    assertTrue(uniquePeople4.contains(person1InDb));
    assertTrue(uniquePeople4.contains(person2InDb));

    // in both
    Person person3 = new Person();
    person3.setName("Joe");
    person3.addAddressBook(addressBook1);
    person3.addAddressBook(addressBook2);
    Person person3InDb = personService.create(person3);

    List<Person> uniquePeople5 = personService.uniquePeopleToAddressBooks(adb1Id, adb2Id);

    assertEquals(2, uniquePeople5.size());
    assertTrue(uniquePeople5.contains(person1InDb));
    assertTrue(uniquePeople5.contains(person2InDb));
    assertFalse(uniquePeople5.contains(person3InDb));

    List<Person> uniquePeople6 = personService.uniquePeopleToAddressBooks(adb1Id, adb1Id);
    assertTrue(uniquePeople6.isEmpty());
  }

}