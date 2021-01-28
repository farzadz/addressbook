package com.farzadz.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContactInfoDTO {

  private Long id;

  private Long personId;

  private String phone;

  private String description;

  public ContactInfoDTO(Long personId, String phone, String description) {
    this.personId = personId;
    this.phone = phone;
    this.description = description;
  }
}
