package com.farzadz.addressbook.config;

import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.dto.ContactInfoDTO;
import ma.glasnost.orika.MapperFacade;
import org.junit.jupiter.api.Test;

class DTOMapperTest {

  @Test
  public void test() {
    DTOMapper dtoMapper = new DTOMapper(new PersonDTOConverter());
    dtoMapper.init();
    MapperFacade mapper = dtoMapper.getMapper();

    ContactInfoDTO contactInfoDTO = new ContactInfoDTO();
    contactInfoDTO.setDescription("desc");
    contactInfoDTO.setPhone("4321");
    contactInfoDTO.setId(1L);
    ContactInfo mappedContactInfo = mapper.map(contactInfoDTO, ContactInfo.class);
    System.out.println("y");
  }

}