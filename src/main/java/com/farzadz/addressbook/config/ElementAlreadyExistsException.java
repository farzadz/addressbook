package com.farzadz.addressbook.config;

public class ElementAlreadyExistsException extends AddressBookRequestException {

  public ElementAlreadyExistsException(String message) {
    super(message);
  }
}

