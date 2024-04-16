package com.move.move.personinfo.adapter;

import com.move.move.personinfo.dto.PersonDetailRequest;
import com.move.move.personinfo.dto.PersonDetailResponse;

public interface PersonDetailAdapter {

    PersonDetailResponse getPersonDetail(PersonDetailRequest personDetailRequest);

    String personImageUrl(String personName);


}
