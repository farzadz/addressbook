package com.farzadz.addressbook.dto;

import java.util.List;
import lombok.Data;

@Data
public class AddressBookDTO {

  private Long id;

  private Long name;

  private Long description;

  private List<PersonDTO> people;
}
