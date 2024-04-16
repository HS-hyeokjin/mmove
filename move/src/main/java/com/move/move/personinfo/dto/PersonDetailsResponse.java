package com.move.move.personinfo.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDetailsResponse {


    private List<PersonDetailResult> results;


}