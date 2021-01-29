package com.farzadz.addressbook.domain;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "name")
@EqualsAndHashCode(of = { "id" })
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "person")
  private List<ContactInfo> contactInfos = new LinkedList<>();

  @ManyToMany
  @JoinTable(name = "person_addressbook", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "addressbook_id"))
  private List<AddressBook> addressBooks = new LinkedList<>();

  public void updateUpdatableProperties(Person person) {
    this.setName(person.getName());
    this.setDescription(person.getDescription());
  }

  public void addAddressBook(AddressBook addressBook) {
    this.addressBooks.add(addressBook);
    addressBook.getPeople().add(this);
  }
}
