package com.farzadz.addressbook.service;

import com.farzadz.addressbook.domain.ContactInfo;
import java.util.List;

public interface ContactInfoService extends CrudService<ContactInfo, Long> {

  List<ContactInfo> findAllContactInfoForPerson(Long personId);

}
