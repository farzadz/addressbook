package com.farzadz.addressbook.service;

import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.domain.Person;
import java.util.List;

public interface AddressBookService extends CrudService<AddressBook, Long> {

  List<Person> uniquePeople(Long addressBookId);
}
