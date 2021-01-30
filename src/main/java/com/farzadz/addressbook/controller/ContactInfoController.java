package com.farzadz.addressbook.controller;

import static com.farzadz.addressbook.config.RestEndpointConfig.BASE_CONTACT_INFOS_PATH;
import static com.farzadz.addressbook.config.RestEndpointConfig.BASE_CONTACT_INFO_PATH;
import static com.farzadz.addressbook.config.RestEndpointConfig.PERSON_CONTACT_INFO_PATH;

import com.farzadz.addressbook.config.DTOMapper;
import com.farzadz.addressbook.domain.ContactInfo;
import com.farzadz.addressbook.domain.Person;
import com.farzadz.addressbook.dto.ContactInfoDTO;
import com.farzadz.addressbook.service.ContactInfoService;
import com.farzadz.addressbook.service.PersonService;
import io.swagger.annotations.Api;
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
@Api(description = "Operations on Contact Information")
public class ContactInfoController {

  private final ContactInfoService contactInfoService;

  private final PersonService personService;

  private final DTOMapper mapper;

  @ApiOperation(value = "Retrieve all contacts", notes = "Returns every contact for every person in any address book")
  @RequestMapping(path = BASE_CONTACT_INFOS_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  List<ContactInfoDTO> getAllContactInfos() {
    return mapper.mapAsList(contactInfoService.findAll(), ContactInfoDTO.class);
  }

  @ApiOperation(value = "Create contact", notes = "Creates a contact for a person")
  @RequestMapping(path = PERSON_CONTACT_INFO_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ContactInfoDTO createContactInfo(@PathVariable Long person, @RequestBody ContactInfoDTO contactInfoDTO) {
    ContactInfo contactInfo = mapper.map(contactInfoDTO, ContactInfo.class);
    Person personInDb = personService.findById(person);
    contactInfo.setPerson(personInDb);
    return mapper.map(contactInfoService.create(contactInfo), ContactInfoDTO.class);
  }

  @ApiOperation(value = "Retrieve contact", notes = "Returns a single contact")
  @RequestMapping(path = BASE_CONTACT_INFO_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ContactInfoDTO getContactInfo(@PathVariable Long contact) {
    return mapper.map(contactInfoService.findById(contact), ContactInfoDTO.class);
  }

  @ApiOperation(value = "Update contact", notes = "Updates a single contact's name and phone")
  @RequestMapping(path = BASE_CONTACT_INFO_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ContactInfoDTO updateContactInfo(@PathVariable Long contact, @RequestBody ContactInfoDTO contactInfoDTO) {
    return mapper.map(contactInfoService.updateById(contact, mapper.map(contactInfoDTO, ContactInfo.class)),
        ContactInfoDTO.class);
  }

  @ApiOperation(value = "Delete contact", notes = "Deletes the specified contact")
  @RequestMapping(path = BASE_CONTACT_INFO_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  void deleteContactInfo(@PathVariable Long contact) {
    contactInfoService.deleteById(contact);
  }

}
