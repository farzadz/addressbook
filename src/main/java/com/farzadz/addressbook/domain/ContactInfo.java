package com.farzadz.addressbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {

  private Long id;

  private Person person;

  private String phone;

  private String description;

}
