package com.farzadz.addressbook.controller;

import com.farzadz.addressbook.config.AddressBookRequestException;
import com.farzadz.addressbook.config.ElementAlreadyExistsException;
import com.farzadz.addressbook.config.ElementWithIDNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

  private ObjectMapper mapper;

  @Autowired
  public APIExceptionHandler(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @ExceptionHandler(ElementWithIDNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public JsonNode handleElementNotFound(Exception exception) {
    return handle400Error(exception);
  }


  @ExceptionHandler(AddressBookRequestException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ResponseBody
  public JsonNode handleAlreadyExistsException(Exception exception) {
    return handle400Error(exception);
  }

  private JsonNode handle400Error(Exception exception) {
    final ObjectNode response = mapper.createObjectNode();
    response.put("status", "4xx");
    response.put("message", exception.getMessage());
    return response;
  }

}



