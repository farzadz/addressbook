package com.farzadz.addressbook.service;

import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import java.util.List;

public interface PersonService extends CrudService<Person, Long> {

  List<ContactInfo> getAllContactInfo(Long personId);

  List<Person> allPeopleInAddressBook(Long addressBookId);

  List<Person> uniquePeopleToAddressBooks(Long addressBook1, Long addressBook2);
}
