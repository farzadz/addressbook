package com.farzadz.addressbook.config;

import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import com.farzadz.addressbook.dto.AddressBookDTO;
import com.farzadz.addressbook.dto.ContactInfoDTO;
import com.farzadz.addressbook.dto.PersonDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOMapper extends ConfigurableMapper {

  @Override
  protected void configure(MapperFactory factory) {

    factory.classMap(Person.class, PersonDTO.class).byDefault().register();
    factory.classMap(ContactInfo.class, ContactInfoDTO.class).byDefault().register();
    factory.classMap(AddressBook.class, AddressBookDTO.class).byDefault().register();
  }

  @Bean
  public MapperFacade mapper() {
    return new DTOMapper();
  }

}
