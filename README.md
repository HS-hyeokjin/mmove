# 0. 프로젝트 개요

생성자: 혁진 김
생성 일시: 2024년 2월 27일 오후 4:26

## 프로젝트 - MMOVE

대한민국 박스 오피스 일별 데이터 통계 및 최신 영화 순위를 확인 할 수 있는 MMOVE 웹사이트 프로젝트입니다.
<p align="center">
    <img width="1000" src="https://github.com/HS-hyeokjin/mmove/assets/90303458/37c62a20-5f33-4611-b5a8-c3bacb6c4ccd"/>
</p>


### 프로젝트 개요

kofic(영화진흥위원회)와 TMDB(The Movie Database)에서 제공하는 API를 활용하여 사용자에게 영화에 대한 다양한 정보와 국내 박스오피스 정보를 제공하고 있습니다. 또한, 개인 사용자의 취향에 맞는 영화를 추천하고 리뷰 서비스를 제공하여 영화 관련 커뮤니티를 서비스를 제공합니다!

### 특징

1. **일간 박스오피스 Top 10 확인 :** 사용자들은 웹사이트를 통해 대한민국 일간 박스오피스 순위 상위 10개 영화를 확인할 수 있으며, 영화 제목, 포스터 이미지, 순위, 개봉일 등의 정보를 제공합니다.
2. **국내/해외 영화 순위 :** 영화에 대한 순위와 통계를 제공합니다. 이를 통해 국내와 해외 영화 동향을 파악 할 수 있습니다.
3. **주간 박스오피스 데이터 통계** : 일간 뿐만 아니라 주간 상업 영화의 매출액, 관람객 수 등을 직접 확인 할 수 있습니다
4. **영화 상세 정보 서비스 :**  각 영화의 상세 정보를 제공합니다. 포스터 이미지, 개봉일, 매출액, 관객 수 등의 정보를 표시하여 사용자들이 원하는 정보를 쉽게 찾을 수 있습니다.
5. **영화인 상세 정보 서비스 :** 영화뿐만 아니라 영화인( 감독, 배우 등)의 대한 정보도 제공합니다. 유명감독과 배우들의 필모그래피를 확인 할 수 있습니다.
6. **커뮤니티 형성:**  리뷰를 통해 영화 관련 정보를 공유하고 소통할 수 있는 사용자 커뮤니티를 형성하여 영화 팬들 간의 활발한 소통과 교류를 도모합니다.
7. **영화 추천 서비스** : 찜하기 기능을 통해 내가 본 영화에 대한 리뷰와 평점을 기록하고 그걸 기반으로 영화를 추천 받을 수 있는 서비스를 제공합니다.

영화 산업에 관심이 있는 사용자들을 위한 정보 제공 플랫폼으로, 영화의 인기 및 트렌드를 파악하고자 하는 사람들에게 유용한 서비스를 제공합니다.

### 프로젝트 기간

2024년 2월 14일 ~ 2024년 3월 14일 (약 1달)

→ 추가기능 기간 ( ui 수정, 문서 작성, 사용자 기능 등)

### 팀구성

백엔드 1명 ㅠ

### 개발 환경 및 스택

주요 기술 스택 : 스프링부트, 타임리프, mysql

운영체제 : windows 10

개발 도구 : chrome, intellij

주요 기술 : jwt, RestTemplate, bootStrap

스프링부트, 타임리프, 스프링 시큐리티, mysql , RestTemplate, 

## 주요 기능(설계)

### 일간 박스오피스

<p align="center">
    <img width="1000" src="https://github.com/HS-hyeokjin/mmove/assets/90303458/36b282b5-cece-4e59-b61a-ec333b818492"/>
</p>

```java
    public DailyBoxOfficeResponseDto getDailyBoxOffice(String nationCd, String date) {
        if (date == null) {
            date = yesterdayStringDate();
        }
        DailyBoxOfficeRequestDto dailyBoxOfficeRequestDto = new DailyBoxOfficeRequestDto();
        dailyBoxOfficeRequestDto.setTargetDt(date);
        if (!nationCd.equals("A")) {
            dailyBoxOfficeRequestDto.setRepNationCd(nationCd);
        }
        DailyBoxOfficeResponseDto dailyBoxOfficeData = dailyBoxOfficeAdapter.getDailyBoxOfficeData(dailyBoxOfficeRequestDto);

        List<DailyBoxOfficeResponseDto.DailyBoxOffice> dailyBoxOfficeList = dailyBoxOfficeData.getBoxOfficeResult().getDailyBoxOfficeList();
        for (DailyBoxOfficeResponseDto.DailyBoxOffice dailyBoxOffice : dailyBoxOfficeList) {
            String movieTitle = dailyBoxOffice.getMovieNm();
            String posterUrl = movieDetailAdapter.searchMoviePoster(movieTitle);
            dailyBoxOffice.setImageUrl(posterUrl);
        }
        return dailyBoxOfficeData;
    }
```
일간 박스 오피스 서비스는 국가코드와 날짜 정보를 이용해서 세번의 실행으로 각 각 전체, 한국, 외국 순으로 선택된 날짜에 박스오피스 정보를  kofic.api 서버에서 가져옵니다. 가져온 데이터 정보와 tmdb에서 가져온 데이터 정보를 각 각 매핑해주고 클라이언트에게 보여주는 서비스를 구현합니다.

[날짜별 조회 gif]

여기서 일별 조회를 위해 캘린더 기능을 사용했으며, 클라이언트에서 날짜별 요청을 위해 날짜 api를 활용하여 날짜 데이터를 넘겨준다.

### 네비게이션설계

[네비게이션 gif]

상단 네브바로 만들지 사이드바로 만들지 최종적으로 클릭하면 사이드바가 나오는 형식으로 만들었습니다. 이유는 기능적 위에 설명을 추가 할 수 있으며 디자인적으로는 비교적 깔끔한 offcanvas로 결정했습니다.

### 주간 박스 오피스 설계

[주간 박스오피스 gif]

주간 박스오피스는 kofic 에 주간 박스오피스 정보와 tmdb에 이미지 그리고 영화 상세정보로 데이터를 표시했습니다. 데이터 시각화를 위해 char.js 를 사용했으며, 주간별 관객 수와 매출액 등의 정보를 표기하여 수치를 한눈에 볼 수 있도록 하였습니다.

### 영화 상세 정보

[영화 상세 정보 gif]

### 리뷰

[리뷰 작성 gif]

### 인물 상세 정보

[인물 상세 정보 gif]

### 영화 검색 / 영화인 검색

[영화 검색 gif]

[영화인 검색 gif] 

### 회원가입 로그인 쿠키 토큰

[회원가입 로그인 gif]
