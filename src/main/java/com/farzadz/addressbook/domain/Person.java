package com.farzadz.addressbook.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "person")
  private List<ContactInfo> contactInfos;

  @ManyToMany
  @JoinTable(name = "person_addressbook", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "addressbook_id"))
  private List<AddressBook> addressBooks;

  public void updateUpdatableProperties(Person person) {
    this.setName(person.getName());
    this.setDescription(person.getDescription());
  }
}
