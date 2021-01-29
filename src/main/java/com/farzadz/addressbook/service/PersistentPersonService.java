package com.farzadz.addressbook.service;

import com.farzadz.addressbook.config.ElementWithIDNotFoundException;
import com.farzadz.addressbook.dao.PersonDAO;
import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
    return personDAO.save(personInDb);
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
    return personDAO.findByAddressBooksId(addressBookId);
  }


  /**
   * Finds people who are in either of the two addressBooks but not both.
   *
   * @return unique people in address books
   */
  @Override
  public List<Person> uniquePeopleToAddressBooks(Long addressBook1, Long addressBook2) {

    // NOTE
    // The whole operation could have been done in with Guava's  Sets.symmetricDifference(peopleIn1, peopleIn2)
    HashSet<Person> peopleIn1 = new HashSet<>(allPeopleInAddressBook(addressBook1));
    HashSet<Person> peopleIn2 = new HashSet<>(allPeopleInAddressBook(addressBook2));

    HashSet<Person> union = new HashSet<>();
    union.addAll(peopleIn1);
    union.addAll(peopleIn2);

    HashSet<Person> intersection = new HashSet<>(peopleIn1);
    intersection.retainAll(peopleIn2);

    union.removeAll(intersection);
    return new LinkedList<>(union);

  }

}
