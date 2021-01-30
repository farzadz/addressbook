package com.farzadz.addressbook.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class PersonDTO {

  @ApiModelProperty(hidden = true)
  private Long id;

  private String name;

  private String description;

  private List<Long> addressBooks = new LinkedList<>();

  private List<ContactInfoDTO> contactInfos = new LinkedList<>();

}
