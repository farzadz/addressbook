package com.farzadz.addressbook.service;

import com.farzadz.addressbook.config.ElementWithIDNotFoundException;
import com.farzadz.addressbook.dao.PersonDAO;
import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersistentPersonService implements PersonService {

  private final PersonDAO personDAO;

  @Override
  public Person create(Person person) {
    return personDAO.save(person);
  }

  @Override
  public Person findById(Long id) {
    return personDAO.findById(id).orElseThrow(
        () -> new ElementWithIDNotFoundException(String.format("Person with %d id not found in the database", id)));
  }

  @Override
  public List<Person> findAll() {
    return personDAO.findAll();
  }

  @Override
  public Person updateById(Long id, Person person) {
    Person personInDb = findById(id);
    personInDb.updateUpdatableProperties(person);
    return personDAO.save(person);
  }

  @Override
  public void deleteById(Long id) {
    personDAO.deleteById(id);
  }

  @Override
  public List<ContactInfo> getAllContactInfo(Long id) {
    Person person = personDAO.findById(id).orElseThrow(
        () -> new ElementWithIDNotFoundException(String.format("Person with %d id not found in the database", id)));
    return person.getContactInfos();
  }

  @Override
  public List<Person> uniquePeople(Long addressBookId) {
    return null;
  }

  @Override
  public List<Person> allPeopleInAddressBook(Long addressBookId) {
    return personDAO.findByAddressBooksId( addressBookId);
  }

}
