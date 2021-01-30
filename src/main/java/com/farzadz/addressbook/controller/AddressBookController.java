package com.farzadz.addressbook.controller;

import static com.farzadz.addressbook.config.RestEndpointConfig.BASE_ADDRESS_BOOKS_PATH;
import static com.farzadz.addressbook.config.RestEndpointConfig.BASE_ADDRESS_BOOK_PATH;

import com.farzadz.addressbook.config.AddressBookRequestException;
import com.farzadz.addressbook.config.DTOMapper;
import com.farzadz.addressbook.domain.AddressBook;
import com.farzadz.addressbook.dto.AddressBookDTO;
import com.farzadz.addressbook.service.AddressBookService;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AddressBookController {

  private final AddressBookService addressBookService;

  private final DTOMapper mapper;

  @ApiOperation(value = "Retrieve all address books", notes = "Returns all address books")
  @RequestMapping(path = BASE_ADDRESS_BOOKS_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  List<AddressBookDTO> getAllAddressBooks() {
    return mapper.mapAsList(addressBookService.findAll(), AddressBookDTO.class);
  }

  @ApiOperation(value = "Create address book", notes = "Creates new address book, sub-resources not created")
  @RequestMapping(path = BASE_ADDRESS_BOOKS_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  AddressBookDTO createAddressBook(@RequestBody AddressBookDTO addressBookDTO) {
    if (addressBookDTO.getPeople() != null && !addressBookDTO.getPeople().isEmpty()) {
      throw new AddressBookRequestException("Address book definition should not contain sub-resources");
    }
    AddressBook addressBookInDb = addressBookService.create(mapper.map(addressBookDTO, AddressBook.class));
    return mapper.map(addressBookInDb, AddressBookDTO.class);
  }

  @ApiOperation(value = "Retrieve address book", notes = "Retrieves single address book")
  @RequestMapping(path = BASE_ADDRESS_BOOK_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  AddressBookDTO getAddressBook(@PathVariable Long addressbook) {
    return mapper.map(addressBookService.findById(addressbook), AddressBookDTO.class);
  }

  @ApiOperation(value = "Update address book", notes = "Updates existing address book, sub-resources not created/updated")
  @RequestMapping(path = BASE_ADDRESS_BOOK_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  AddressBookDTO updateAddressBook(@PathVariable Long addressbook, @RequestBody AddressBookDTO addressBookDTO) {
    if (addressBookDTO.getPeople() != null && !addressBookDTO.getPeople().isEmpty()) {
      throw new AddressBookRequestException("Address book update should not contain sub-resources");
    }
    return mapper.map(addressBookService.updateById(addressbook, mapper.map(addressBookDTO, AddressBook.class)),
        AddressBookDTO.class);
  }

  @ApiOperation(value = "Delete address book", notes = "Deletes an existing address book")
  @RequestMapping(path = BASE_ADDRESS_BOOK_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  void deleteAddressBook(@PathVariable Long addressbook) {
    addressBookService.deleteById(addressbook);
  }

}
