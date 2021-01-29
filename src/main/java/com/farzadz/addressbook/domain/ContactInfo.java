package com.farzadz.addressbook.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contactinfo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  private String phone;

  private String description;

  public void updateUpdatableProperties(ContactInfo contactInfo) {
    this.phone = contactInfo.getPhone();
    this.description = contactInfo.getDescription();
  }

  public void setPerson(Person person){
    this.person = person;
    person.getContactInfos().add(this);
  }
}
