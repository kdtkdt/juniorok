![image](https://github.com/kdtkdt/juniorok/assets/135004614/7c573a63-c281-4ca3-ae46-3926112b9239)

![Static Badge](https://img.shields.io/badge/jQuery-0769AD?logo=jquery&logoColor=white)
![Static Badge](https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=white)
![Static Badge](https://img.shields.io/badge/Bootstrap-7952B3?logo=bootstrap&logoColor=white)  
  
![Static Badge](https://img.shields.io/badge/SpringBoot-6DB33F?logo=springboot&logoColor=white)
![Static Badge](https://img.shields.io/badge/SpringSecurity-6DB33F?logo=springsecurity&logoColor=white)
![Static Badge](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)  
  
![Static Badge](https://img.shields.io/badge/MySql-4479A1?logo=mysql&logoColor=white)
<img src="https://img.shields.io/badge/Mybatis-000000?style=flat&logo=Fluentd&logoColor=white" />  
  
![Static Badge](https://img.shields.io/badge/Postman-FF6C37?logo=postman&logoColor=white)
![Static Badge](https://img.shields.io/badge/IntelliJidea-000000?logo=intellijidea&logoColor=white)

# 🤔 서비스 기획 의도
## 5조, Junior 5k 의 `Junior Ok`는 주니어 개발자 채용공고 큐레이션 사이트 입니다.
### 나에게 맞는 공고를 찾는 어려움
- 기술이 나와 맞는 것 같아 눌러보면 `최소 n년차 이상`
- 회사 소개를 읽으며 마음에 들어서 자격 요건을 확인해보면 `내가 사용하는 기술`이 아니네?
- 마음에 들어서 열심히 준비했더니, 희망 없는 `적자` 행진에 `연봉`은 쥐꼬리...

### 나에게 맞는 공고를 찾는 시간과 회사 정보를 수집하는 시간을 줄여보자
- 찾아다니기 귀찮은 회사 정보(평균 연봉, 임직원수, 입/퇴사자수 등)를 `자동 수집`
- 지역, 평균 연봉, 사용 기술 등을 `전면에 배치`하여 굳이 눌러보지 않아도 될 공고는 빠르게 패스
- 회사에 대해 알아보기 전 나에게 맞는 공고인지 판단할 수 있는 `정리된 정보`를 한눈에 보면서 시간 절약 

# 👨‍👧‍👧 팀원
- 정성국
- 이현지
- 차유빈
- 오유택

# 🗓 개발 기간
- 2023.08.10 ~ 2023.08.24

# 🏢💼📱기능

## 회원
- Github 로그인 : Github 로고만 있으면 로그인 버튼인지 알아차리기 힘들어, 다른 소셜 로그인 아이콘과 함께 배치하고 지원 예정임을 표시하였습니다.
- 역할별 접근 권한 부여 : 관리자, 작성자만 공고를 작성할 수 있도록 권한이 부여됩니다.
![image](https://github.com/kdtkdt/juniorok/assets/135004614/17db884a-6b9d-4c5e-a0ff-9a079f5d5e20)

## 메인페이지
- 공고 목록 조회 : 회사, 기술스택, 지역, 연봉, 모집기한이 표시됩니다.
- 북마크 : 자신이 마음에 드는 공고를 북마크 할 수 있습니다.
- 추천공고 : 추천되는 공고를 확인할 수 있습니다.

![image](https://github.com/kdtkdt/juniorok/assets/135004614/b0f24c3f-8662-4b8a-b608-cb768cdb81e5)

## 마이페이지
- 북마크한 공고 목록 조회 : 자신이 북마크한 공고 목록이 표시됩니다.
- 북마크 목록 제외 : 북마크 했던 목록에서 제외할 수 있습니다.
- 검색 및 정렬 : 원하는 키워드로 검색하거나 원하는 기술을 선택하여 정렬할 수 있습니다.
- 무한스크롤 페이징 : 지나친 공고를 다시 편하게 확인할 수 있도록 무한스크롤 방식의 페이징을 적용하였습니다.

![image](https://github.com/kdtkdt/juniorok/assets/135004614/dcb5f8d0-4ddc-41ce-848a-010f6e1f5db3)

## 공고 상세 페이지
- 공고 상세 정보 조회 : 필수적인 정보를 가로로 배치하여 한눈에 상세한 정보를 파악할 수 있습니다.
![image](https://github.com/kdtkdt/juniorok/assets/135004614/838605be-4a97-4061-9c16-9d075c9195b2)

- 근무지 위치 조회 : 카카오 지도 API를 이용하여 실제 근무지의 위치를 확인할 수 있습니다.
![image](https://github.com/kdtkdt/juniorok/assets/135004614/86ff61a8-814d-43ee-9d3b-ce4ef965b95f)

## 관리자페이지

### 공고관리
- 공고조회 : 전체 공고 내용을 보고 관리할 수 있습니다.
- 공고수정 : 관리자 계정으로 공고를 수정합니다. 
- 공고등록 : 공고 작성 페이지로 연동하여 관리자페이지에서 공고 작성이 가능합니다.
- 공고삭제 : 부적절한 게시물 및 기한이 끝난 공고를 삭제합니다. 

### 회원관리
- 사용자목록 : 가입된 사용자 전체를 보고 관리할 수 있습니다.
- 권한부여 : 해당 유저에게 글쓰기 권한을 부여하거나 제거할 수 있습니다.
- 사용자삭제 : 필요시 관리자가 사용자를 삭제할 수 있습니다. 
- 사용자검색 : 이메일로 사용자를 검색합니다.

![image](https://github.com/kdtkdt/juniorok/assets/135004614/c12c3be0-a9ec-4103-9882-c5f2abc4df98)

### 사이드바 
- 유저 편의를 위하여 메인페이지에서 다양한 사이트로 이동가능하도록 만들었습니다.

![image](https://github.com/kdtkdt/juniorok/assets/135004614/7d546de9-12fa-4da4-b526-829e2818dcaf)
