package com.farzadz.addressbook.config;

import com.farzadz.addressbook.domain.AddressBook;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class AddressBookConverter extends CustomConverter<AddressBook, Long> {

  @Override
  public Long convert(AddressBook source, Type<? extends Long> destinationType, MappingContext mappingContext) {
    return source.getId();
  }
}
