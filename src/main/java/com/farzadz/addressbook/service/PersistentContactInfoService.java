package com.farzadz.addressbook.service;

import com.farzadz.addressbook.config.ElementWithIDNotFoundException;
import com.farzadz.addressbook.dao.ContactInfoDAO;
import com.farzadz.addressbook.domain.ContactInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PersistentContactInfoService implements ContactInfoService {

  private final ContactInfoDAO contactInfoDAO;

  private final PersonService personService;

  @Override
  public ContactInfo create(ContactInfo contactInfo) {
    return contactInfoDAO.save(contactInfo);
  }

  @Override
  public ContactInfo findById(Long id) {
    return contactInfoDAO.findById(id).orElseThrow(() -> new ElementWithIDNotFoundException(
        String.format("ContactInfo with %d id not found in the database", id)));
  }

  @Override
  public List<ContactInfo> findAll() {
    return contactInfoDAO.findAll();
  }

  @Override
  public ContactInfo updateById(Long id, ContactInfo contactInfo) {
    ContactInfo contactInDB = findById(id);
    contactInfo.updateUpdatableProperties(contactInDB);
    return contactInfoDAO.save(contactInfo);
  }

  @Override
  public void deleteById(Long id) {
    contactInfoDAO.deleteById(id);
  }

  @Override
  public List<ContactInfo> findAllContactInfoForPerson(Long personId) {
    return contactInfoDAO.findByPersonId(personId);
  }
}
