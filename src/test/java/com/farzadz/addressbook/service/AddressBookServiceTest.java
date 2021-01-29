package com.farzadz.addressbook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.domain.Person;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressBookServiceTest {

  @Autowired
  private AddressBookService addressBookService;

  @Autowired
  private PersonService personService;

  private Person person;

  @Before
  public void setup() {
    Person person = new Person();
    person.setName("Alex");
    person.setDescription("Desc");
    this.person = person;
  }

  @Test
  public void create() {
    AddressBook addressBook = new AddressBook();
    addressBook.setName("ADB");
    addressBook.setDescription("ADB Desc");

    AddressBook addressBookInDB = addressBookService.create(addressBook);

    assertNotNull(addressBookInDB.getId());
    assertEquals(addressBook.getName(), addressBookInDB.getName());
    assertEquals(addressBook.getDescription(), addressBookInDB.getDescription());
  }

  @Test
  public void findById() {
    AddressBook addressBook = new AddressBook();
    addressBook.setName("ADB");
    addressBook.setDescription("ADB Desc");

    AddressBook addressBookInDB = addressBookService.create(addressBook);

    Long idInDB = addressBookInDB.getId();
    AddressBook foundAddressBook = addressBookService.findById(idInDB);
    assertEquals(idInDB, foundAddressBook.getId());
    assertEquals(addressBook.getName(), foundAddressBook.getName());
    assertEquals(addressBook.getDescription(), foundAddressBook.getDescription());
  }

  @Test
  public void findAll() {
    AddressBook addressBook1 = new AddressBook();
    addressBook1.setName("ADB");
    addressBook1.setDescription("ADB Desc");

    AddressBook addressBook2 = new AddressBook();
    addressBook2.setName("ADB");
    addressBook2.setDescription("ADB Desc");

    addressBookService.create(addressBook1);
    addressBookService.create(addressBook2);
    List<AddressBook> allAddressBooks = addressBookService.findAll();

    assertTrue(allAddressBooks.stream().anyMatch(
        addressBook -> addressBook.getDescription().equals(addressBook1.getDescription()) && addressBook.getName()
            .equals(addressBook1.getName())));
    assertTrue(allAddressBooks.stream().anyMatch(
        addressBook -> addressBook.getDescription().equals(addressBook2.getDescription()) && addressBook.getName()
            .equals(addressBook2.getName())));
  }

  @Test
  public void updateById() {
    AddressBook addressBook = new AddressBook();
    addressBook.setName("ADB");
    addressBook.setDescription("ADB Desc");

    Long idInDb = addressBookService.create(addressBook).getId();

    AddressBook updatedAddressBook = new AddressBook();
    updatedAddressBook.setName("New ADB");
    updatedAddressBook.setDescription("New Desc");

    AddressBook updatedInDb = addressBookService.updateById(idInDb, updatedAddressBook);
    assertEquals(updatedAddressBook.getDescription(), updatedInDb.getDescription());
    assertEquals(updatedAddressBook.getName(), updatedInDb.getName());

  }

  @Test
  public void deleteById() {
    AddressBook addressBook = new AddressBook();
    addressBook.setName("ADB");
    addressBook.setDescription("ADB Desc");

    Long idInDb = addressBookService.create(addressBook).getId();


    addressBookService.deleteById(idInDb);

    List<AddressBook> allAddressBooks = addressBookService.findAll();
    assertTrue(allAddressBooks.stream().noneMatch(adb -> adb.getId().equals(idInDb)));
  }
}