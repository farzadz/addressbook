package com.farzadz.addressbook.domain;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addressbook")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressBook {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @ManyToMany(mappedBy = "addressBooks")
  private List<Person> people = new LinkedList<>();

  public void updateUpdatableProperties(AddressBook addressBook) {
    this.name = addressBook.getName();
    this.description = addressBook.getDescription();
  }

}
