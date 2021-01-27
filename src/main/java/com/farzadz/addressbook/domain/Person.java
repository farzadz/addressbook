package com.farzadz.addressbook.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

  private Long id;

  private String firstName;

  private String lastName;

  private String description;

}
