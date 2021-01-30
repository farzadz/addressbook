package com.farzadz.addressbook.config;

import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import com.farzadz.addressbook.dto.ContactInfoDTO;
import com.farzadz.addressbook.dto.PersonDTO;
import com.farzadz.addressbook.service.AddressBookService;
import java.util.stream.Collectors;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonDTOConverter extends CustomConverter<PersonDTO, Person> {

  private AddressBookService addressBookService;

  private MapperFacade contactInfoMapper;

  public PersonDTOConverter() {
    DefaultMapperFactory factory = new DefaultMapperFactory.Builder().build();
    factory.classMap(ContactInfoDTO.class, ContactInfo.class).byDefault().register();
    this.contactInfoMapper = factory.getMapperFacade();
  }

  @Autowired
  public void setAddressBookService(AddressBookService addressBookService) {
    this.addressBookService = addressBookService;
  }

  @Override
  public Person convert(PersonDTO source, Type<? extends Person> destinationType, MappingContext mappingContext) {
    Person person = new Person();
    person.setId(source.getId());
    person.setName(source.getName());
    person.setDescription(source.getDescription());
    person.setContactInfos(contactInfoMapper.mapAsList(source.getContactInfos(), ContactInfo.class));
    person.setAddressBooks(
        source.getAddressBooks().stream().map(adb -> addressBookService.findById(adb)).collect(Collectors.toList()));
    return person;
  }
}