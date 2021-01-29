package com.farzadz.addressbook.dao;

import com.farzadz.addressbook.domain.ContactInfo;
import java.util.List;

public interface ContactInfoDAO extends BaseDAO<ContactInfo, Long> {

  List<ContactInfo> findByPersonId(Long id);
}
