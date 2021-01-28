package com.farzadz.addressbook.dao;

import com.farzadz.addressbook.domain.Person;
import java.util.List;

public interface PersonDAO extends BaseDAO<Person, Long> {

  List<Person> findByAddressBookId(Long addressBookId);

  List<Person> findUniqueToAddressBook(Long addressBookId);
}
