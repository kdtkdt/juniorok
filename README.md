<p align="center">
  <img src="https://github.com/kdtkdt/juniorok/assets/135004614/7c573a63-c281-4ca3-ae46-3926112b9239">
</p>

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

# DB 설계

![juniorok](https://github.com/kdtkdt/juniorok/assets/135004614/f30fd12b-5557-42fc-a04f-f028a680929e)

# 새로운 개발 방식과 실패

## 😟 이전 프로젝트에서의 문제점

- 게시판 단위로 개발을 맡아 게시판이 완성될때까지 팀원이 뭘 하는지 알기가 어려움
- 같은 기능을 작업하면서도 각자 개발하여 시간 낭비
- 맡은 업무 자체가 하나의 큰 덩어리가 돼서 도와주기도 어려움

## 🏗️ UI 컴포넌트 단위 개발

### 작업방법

- Thymeleaf 의 `fragment` 기능을 이용하여 작은 UI 컴포넌트 단위로 작업

![image](https://github.com/kdtkdt/juniorok/assets/135004614/af421790-c2f1-4a99-b382-db8a4ad204eb)

### 기대사항
- 작은 단위로 작업하여 팀원 개발진척사항을 자주 업데이트 기대
- 한 페이지 내 코드량을 줄여 가독성 개선
- 컴포넌트를 재사용하여 개발기간 단축 기대
- 구현에 어려움을 겪는 팀원이 있으면 다른 사람이 컴포넌트를 개발하여 제공

## 🐈‍⬛ Github Issue 기반 프로젝트 관리

### 작업방법

- Github에 본인 작업에 대한 Issue 를 작성하고, Issue에 대한 브랜치를 만든 후, Issue 단위로 PR 수행
- 팀원 1명의 검토 필수

### 기대사항

- 팀원이 작업 중인 기능을 파악하여 중복 기능 개발 방지
- PR을 검토하며 다른 사람의 코드를 보면서 배움

## 현실

- 기존의 게시판 단위 작업 방식으로 회귀
- 다른 사람의 코드 안보고 PR 승인
- 중복 컴포넌트 개발 담당자가 개발이 늦어지면, 뒤에 필요한 사람이 따로 만듬

## 원인

- Issue 작성 기준에 대한 명확한 가이드라인을 정하지 않음
- 컴포넌트 개발 우선순위를 정하지 않음
- 컴포넌트 개발 방식이 장점도 있었지만, 컴포넌트가 많아지면 어디에 어떤 코드가 있는지 파악하기 어려움

# 🏢💼📱기능

## 사용 기술

![Static Badge](https://img.shields.io/badge/HTML5-E34F26?logo=html5&logoColor=white)
![Static Badge](https://img.shields.io/badge/CSS3-1572B6?logo=css3&logoColor=white)
![Static Badge](https://img.shields.io/badge/JavaScript-F7DF1E?logo=javascript&logoColor=white)
![Static Badge](https://img.shields.io/badge/jQuery-0769AD?logo=jquery&logoColor=white)
![Static Badge](https://img.shields.io/badge/Bootstrap-7952B3?logo=bootstrap&logoColor=white)  
  
![Static Badge](https://img.shields.io/badge/SpringBoot-6DB33F?logo=springboot&logoColor=white)
![Static Badge](https://img.shields.io/badge/SpringSecurity-6DB33F?logo=springsecurity&logoColor=white)
![Static Badge](https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=white)
![Static Badge](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf&logoColor=white)  
  
![Static Badge](https://img.shields.io/badge/MySql-4479A1?logo=mysql&logoColor=white)
<img src="https://img.shields.io/badge/Mybatis-000000?style=flat&logo=Fluentd&logoColor=white" />  
  
![Static Badge](https://img.shields.io/badge/Postman-FF6C37?logo=postman&logoColor=white)
![Static Badge](https://img.shields.io/badge/IntelliJidea-000000?logo=intellijidea&logoColor=white)

## 외부 API

- 카카오맵 API
- Github 로그인(OAuth app)
- 국민연금공단_국민연금 가입 사업장 내역([링크](https://www.data.go.kr/data/3046071/openapi.do#/tab_layer_detail_function))
  - 평균연봉
  - 임직원수
  - 입퇴사수
  - 주소지

## 회원
- Github 로그인 : Github 로고만 있으면 로그인 버튼인지 알아차리기 힘들어, 다른 소셜 로그인 아이콘과 함께 배치하고 지원 예정임을 표시하였습니다.
- 역할별 접근 권한 부여 : 관리자, 작성자만 공고를 작성할 수 있도록 권한이 부여됩니다.

<p align="center">
  <img src="https://github.com/kdtkdt/juniorok/assets/135004614/17db884a-6b9d-4c5e-a0ff-9a079f5d5e20">
</p>

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

![image](https://github.com/kdtkdt/juniorok/assets/135004614/4286f803-f8d2-485a-ae05-51fdec511942)

### 공고관리
- 공고조회 : 전체 공고 내용을 보고 관리할 수 있습니다.
- 공고수정 : 관리자 계정으로 공고를 수정합니다. 
- 공고등록 : 공고 작성 페이지로 연동하여 관리자페이지에서 공고 작성이 가능합니다.
- 공고삭제 : 부적절한 게시물 및 기한이 끝난 공고를 삭제합니다.

![image](https://github.com/kdtkdt/juniorok/assets/135004614/eac5ea5d-cf5c-488b-812c-1a5755043ffe)

### 회원관리
- 사용자목록 : 가입된 사용자 전체를 보고 관리할 수 있습니다.
- 권한부여 : 해당 유저에게 글쓰기 권한을 부여하거나 제거할 수 있습니다.
- 사용자삭제 : 필요시 관리자가 사용자를 삭제할 수 있습니다. 
- 사용자검색 : 이메일로 사용자를 검색합니다.

![image](https://github.com/kdtkdt/juniorok/assets/135004614/c12c3be0-a9ec-4103-9882-c5f2abc4df98)

### 사이드바 
- 유저 편의를 위하여 메인페이지에서 다양한 사이트로 이동가능하도록 만들었습니다.

<p align="center">
  <img src="https://github.com/kdtkdt/juniorok/assets/135004614/7d546de9-12fa-4da4-b526-829e2818dcaf">
</p>
