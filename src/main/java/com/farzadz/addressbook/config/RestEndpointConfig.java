package com.farzadz.addressbook.config;

public class RestEndpointConfig {

  public static final String BASE_API_PATH = "/api";

  public static final String BASE_ADDRESS_BOOKS_PATH = BASE_API_PATH + "/addressbook";

  public static final String BASE_ADDRESS_BOOK_PATH = BASE_ADDRESS_BOOKS_PATH + "/{addressbook}";

  public static final String BASE_CONTACT_INFOS_PATH = BASE_API_PATH + "/contact";

  public static final String BASE_CONTACT_INFO_PATH = BASE_CONTACT_INFOS_PATH + "/{contact}";

  public static final String PERSON_CONTACT_INFO_PATH = BASE_CONTACT_INFOS_PATH + "/person/{person}";

  public static final String BASE_PEOPLE_PATH = BASE_API_PATH + "/people";

  public static final String UNIQUE_PEOPLE_PATH = BASE_PEOPLE_PATH + "/{addressbook1}/{addressbook2}";

  public static final String BASE_PERSON_PATH = BASE_PEOPLE_PATH + "/{person}";

  public static final String BASE_PERSON_ADDRESS_BOOK_PATH = BASE_PERSON_PATH + "/{addressbook}";

  public static final String SWAGGER_BASE_PATH = BASE_API_PATH + "/swagger";

}
