package com.farzadz.addressbook.controller;

import static com.farzadz.addressbook.config.RestEndpointConfig.BASE_PEOPLE_PATH;
import static com.farzadz.addressbook.config.RestEndpointConfig.BASE_PERSON_PATH;
import static com.farzadz.addressbook.config.RestEndpointConfig.UNIQUE_PEOPLE_PATH;

import com.farzadz.addressbook.config.DTOMapper;
import com.farzadz.addressbook.domain.Person;
import com.farzadz.addressbook.dto.PersonDTO;
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
@Api(description = "Operations on People")
public class PeopleController {

  private final PersonService personService;

  private final DTOMapper mapper;

  @ApiOperation(value = "Retrieve all people in address books", notes = "Returns every person in any address book")
  @RequestMapping(path = BASE_PEOPLE_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  List<PersonDTO> getPeople() {
    return mapper.mapAsList(personService.findAll(), PersonDTO.class);
  }


  @ApiOperation(value = "Create person with contacts", notes = "Creates a person with contacts")
  @RequestMapping(path = BASE_PEOPLE_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  PersonDTO createPerson(@RequestBody PersonDTO personDTO) {
    Person person = mapper.map(personDTO, Person.class);
    Person personInDb = personService.create(person);
    return mapper.map(personInDb, PersonDTO.class);
  }

  @ApiOperation(value = "Retrieve person", notes = "Returns person with the specified Id")
  @RequestMapping(path = BASE_PERSON_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  PersonDTO getPerson(@PathVariable Long person) {
    return mapper.map(personService.findById(person), PersonDTO.class);
  }

  @ApiOperation(value = "Modify person", notes = "Updates the person's information")
  @RequestMapping(path = BASE_PERSON_PATH, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  PersonDTO updatePerson(@PathVariable Long person, @RequestBody PersonDTO personDTO) {
    return mapper.map(personService.updateById(person, mapper.map(personDTO, Person.class)), PersonDTO.class);
  }

  @ApiOperation(value = "Delete person", notes = "Removes person and its trace from other resources")
  @RequestMapping(path = BASE_PERSON_PATH, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  void deletePerson(@PathVariable Long person) {
    personService.deleteById(person);
  }

  @ApiOperation(value = "Find unique people to address books", notes = "Finds people who are not in both address books")
  @RequestMapping(path = UNIQUE_PEOPLE_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  List<PersonDTO> getUniquePeople(@PathVariable Long addressbook1, @PathVariable Long addressbook2) {
    return mapper.mapAsList(personService.uniquePeopleToAddressBooks(addressbook1, addressbook2), PersonDTO.class);
  }

}
