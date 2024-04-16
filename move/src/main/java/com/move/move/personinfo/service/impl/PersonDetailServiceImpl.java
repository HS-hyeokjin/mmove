package com.move.move.personinfo.service.impl;

import com.move.move.personinfo.adapter.PersonDetailAdapter;
import com.move.move.personinfo.dto.PersonDetailRequest;
import com.move.move.personinfo.dto.PersonDetailResponse;
import com.move.move.personinfo.service.PersonDetailService;
import org.springframework.stereotype.Service;

/**
 * 영화인 상세 정보를 가져오기 위한 서비스
 */
@Service
public class PersonDetailServiceImpl implements PersonDetailService {

    private final PersonDetailAdapter personDetailAdapter;

    public PersonDetailServiceImpl(PersonDetailAdapter personDetailAdapter) {
        this.personDetailAdapter = personDetailAdapter;
    }

    /**
     * 영화인 상세정보를 가져는 메서드
     *
     * @param personName 영화인 이름
     * @return PersonDetailResponse 영화인 정보 객체
     */
    @Override
    public PersonDetailResponse getPersonDetail(String personName) {
        PersonDetailRequest personDetailRequest = new PersonDetailRequest();
        personDetailRequest.setPersonEnNm(personName);
        return personDetailAdapter.getPersonDetail(personDetailRequest);
    }
}
