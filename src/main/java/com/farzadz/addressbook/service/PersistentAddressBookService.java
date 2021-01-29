package com.farzadz.addressbook.service;

import com.farzadz.addressbook.config.ElementWithIDNotFoundException;
import com.farzadz.addressbook.dao.AddressBookDAO;
import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.domain.Person;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersistentAddressBookService implements AddressBookService {

  private final AddressBookDAO addressBookDAO;

  @Override
  public AddressBook create(AddressBook addressBook) {
    return addressBookDAO.save(addressBook);
  }

  @Override
  public AddressBook findById(Long id) {
    return addressBookDAO.findById(id).orElseThrow(() -> new ElementWithIDNotFoundException(
        String.format("Address book with %d id not found in the database", id)));
  }

  @Override
  public List<AddressBook> findAll() {
    return addressBookDAO.findAll();
  }

  @Override
  public AddressBook updateById(Long id, AddressBook addressBook) {
    AddressBook addressBookInDB = findById(id);
    addressBookInDB.updateUpdatableProperties(addressBook);
    return addressBookDAO.save(addressBook);
  }

  @Override
  public void deleteById(Long id) {
    addressBookDAO.deleteById(id);
  }

}
