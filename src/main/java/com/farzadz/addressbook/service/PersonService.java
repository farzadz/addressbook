package com.farzadz.addressbook.service;

import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import java.util.List;

public interface PersonService extends CrudService<Person, Long> {

  //  Person addContactInfoForPerson(Long id, ContactInfo contactInfo);
  List<ContactInfo> getAllContactInfo(Long personId);

  List<Person> uniquePeople(Long addressBookId);

  List<Person> allPeopleInAddressBook(Long addressBookId);
}
