package com.farzadz.addressbook.service;

import com.farzadz.addressbook.config.AddressBookRequestException;
import com.farzadz.addressbook.config.ElementAlreadyExistsException;
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

  @Override
  public ContactInfo create(ContactInfo contactInfo) {
    if (contactInfo.getId() == null) {
      return contactInfoDAO.save(contactInfo);
    }
    throw new AddressBookRequestException("Invalid Id for contact info");
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
    ContactInfo contactInfo = findById(id);
    contactInfo.getPerson().getContactInfos().remove(contactInfo);
    contactInfoDAO.deleteById(id);
  }

  @Override
  public List<ContactInfo> findAllContactInfoForPerson(Long personId) {
    return contactInfoDAO.findByPersonId(personId);
  }
}
