package com.farzadz.addressbook.config;

import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import com.farzadz.addressbook.dto.AddressBookDTO;
import com.farzadz.addressbook.dto.ContactInfoDTO;
import com.farzadz.addressbook.dto.PersonDTO;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOMapper {

  private final PersonDTOConverter personDTOConverter;

  @Getter
  private MapperFacade mapper;

  @PostConstruct
  public void init() {
    MapperFactory factory = new DefaultMapperFactory.Builder().build();
    factory.classMap(Person.class, PersonDTO.class).byDefault().register();
    factory.classMap(ContactInfo.class, ContactInfoDTO.class).byDefault().register();
    factory.classMap(AddressBook.class, AddressBookDTO.class).byDefault().register();
    factory.classMap(ContactInfoDTO.class, ContactInfo.class).byDefault().register();
    factory.getConverterFactory().registerConverter(personDTOConverter);
    factory.getConverterFactory().registerConverter(new AddressBookConverter());
    mapper = factory.getMapperFacade();
  }

  public <S, D> D map(S source, Class<D> destination) {
    return mapper.map(source, destination);
  }

  public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destination) {
    return mapper.mapAsList(source, destination);
  }

}
