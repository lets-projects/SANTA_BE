# <center>등산을 사랑하는 당신을 위한 서비스, 산타</center>
---
![스크린샷_2024-05-11_오전_3.31.03](/uploads/1ef2a348ba3cbc13164aab11751061f2/스크린샷_2024-05-11_오전_3.31.03.png)
- 산타
- 등산을 좋아하는 사람을 위한 모임 서비스

## **서비스 목적**
---
**등산을 좋아하는 유저들 간의 모임을 활성화 하여 더욱 즐거운 등산생활을 즐길 수 있도록 한다.**

## 서비스 목표
---
- 유저가 직접 참여하여 다양한 카테고리의 모임을 생성/참여하여 커뮤니티를 이룸
- 사용자가 선호 카테고리를 선택하여 선호 카테고리별 모임 조회를 통한 모임 선택의 어려움 감소
- 생성된 다양한 챌린지를 통해 사용자들이 사이트를 이용함에 있어 동기부여 제공
- 랭킹 시스템(유저가 완료한 업적 + 유저가 등반한 산의 누적 높이)를 통한 등산에 대한 동기부여 제공

## 프로젝트 구조 및 아키텍처
---
![스크린샷_2024-05-11_오전_3.36.30](/uploads/71aea802f6dfa5b7f075ac997e9b6268/스크린샷_2024-05-11_오전_3.36.30.png)

## ERD
---
![스크린샷_2024-05-11_오전_3.39.04](/uploads/82e034565814eda95f48849f43cbd125/스크린샷_2024-05-11_오전_3.39.04.png)

## 플로우차트
---
![스크린샷_2024-05-11_오전_3.41.00](/uploads/aef3a700a86ae7adb3e6ec9057c1cecb/스크린샷_2024-05-11_오전_3.41.00.png)

## 와이어프레임
---
![스크린샷_2024-05-11_오전_3.42.31](/uploads/266b50cc2fd2ba0b7b83a39d57243507/스크린샷_2024-05-11_오전_3.42.31.png)

## 세부 기술스텍
---
프론트엔드
> - 타입스크립트
> - Redux 상태관리
> - SCSS

<br/>

백엔드
> - JAVA
>    - OpenJDK 17
> - Spring Boot 3.2.4
>    - Spring Web 3.2.4
>    - Spring Data JPA 3.2.4
>    - Spring Data Redis 3.2.4
>    - Spring mail 3.2.4
>    - Spring Security 3.2.4
> - JUnit5
> - MySQL 8

<br/>

서버
> - Docker
> - NginX
> - AWS(EC2, S3, ElastiCache)

<br/>
<img alt="typescript" src ="https://img.shields.io/badge/typescript-3178C6.svg?&style=for-the-badge&logo=typescript&logoColor=white"/> <img alt="react" src ="https://img.shields.io/badge/react-61DAFB.svg?&style=for-the-badge&logo=react&logoColor=white"/> <img alt="TanStack Query" src ="https://img.shields.io/badge/TanStack Query-FF4154.svg?&style=for-the-badge&logo=reactquery&logoColor=white"/>, 
<img alt="gitlab" src ="https://img.shields.io/badge/gitlab-FC6D26.svg?&style=for-the-badge&logo=gitlab&logoColor=white"/>
<img alt="sass" src ="https://img.shields.io/badge/sass-CC6699.svg?&style=for-the-badge&logo=sass&logoColor=white"/>
<img alt="reacthookform" src ="https://img.shields.io/badge/reacthookform-EC5990.svg?&style=for-the-badge&logo=reacthookform&logoColor=white"/>

<br/>
<img alt="springboot" src ="https://img.shields.io/badge/springboot-6DB33F.svg?&style=for-the-badge&logo=springboot&logoColor=white"/>
<img alt="springsecurity" src ="https://img.shields.io/badge/springsecurity-6DB33F.svg?&style=for-the-badge&logo=springsecurity&logoColor=white"/>
<img alt="MySQL" src ="https://img.shields.io/badge/mysql-4479A1.svg?&style=for-the-badge&logo=mysql&logoColor=white"/>
<img alt="jenkins" src ="https://img.shields.io/badge/jenkins-D24939.svg?&style=for-the-badge&logo=jenkins&logoColor=white"/>
<img alt="ubuntu" src ="https://img.shields.io/badge/ubuntu-E95420.svg?&style=for-the-badge&logo=ubuntu&logoColor=white"/>
<img alt="docker" src ="https://img.shields.io/badge/docker-2496ED.svg?&style=for-the-badge&logo=docker&logoColor=white"/>
<img alt="nginx" src ="https://img.shields.io/badge/nginx-009639.svg?&style=for-the-badge&logo=nginx&logoColor=white"/>
<img alt="amazonec2" src ="https://img.shields.io/badge/amazonec2-FF9900.svg?&style=for-the-badge&logo=amazonec2&logoColor=white"/>
<img alt="amazons3" src ="https://img.shields.io/badge/amazons3-569A31.svg?&style=for-the-badge&logo=amazons3&logoColor=white"/>
<img alt="amazonrds" src ="https://img.shields.io/badge/amazonrds-527FFF.svg?&style=for-the-badge&logo=amazonrds&logoColor=white"/>

<br/>
<img alt="gitlab" src ="https://img.shields.io/badge/gitlab-FC6D26.svg?&style=for-the-badge&logo=gitlab&logoColor=white"/>
<img alt="swagger" src ="https://img.shields.io/badge/swagger-85EA2D.svg?&style=for-the-badge&logo=swagger&logoColor=white"/>
<img alt="figma" src ="https://img.shields.io/badge/figma-F24E1E.svg?&style=for-the-badge&logo=figma&logoColor=white"/>
<img alt="notion" src ="https://img.shields.io/badge/notion-000000.svg?&style=for-the-badge&logo=notion&logoColor=white"/>
<img alt="discord" src ="https://img.shields.io/badge/discord-5865F2.svg?&style=for-the-badge&logo=discord&logoColor=white"/>

## 서비스 기능 명세
---
### 1. 유저 기능
> - Spring Security + JWT 기반 사용자 인증/인가
> - 회원
>    - 회원가입시 비밀번호 인코딩
>    - 이메일 인증(비밀번호 재발급)
> - 로그인
>    - AccessToken 과 RefreshToken 발급
>    - AccessToken 만료되면 RefreshToken으로 newAccessToken 발급
>    - 카카오 소셜로그인 성공 후 자체 JWT 발급
> - 선호 키워드 등록

### 2. 모임 기능
> - 회원
>    - 모임 생성
>        - 생성한 회원은 모임장(id) 부여
>        - 생성시 모임카테고리, 선호태그 등 설정
>    - 조회
>        - 선호 카테고리 및 태그 사용자 검색어 기반 모임&검색어 조회
>        - 최신순, 인기순 기반 필터링
>        - 모임 참여인원 조회 가능
>    - 유저 신고 기능
> - 모임장
>    - 모임 수정 가능
>    - 모임페이지 삭제 가능

### 3.  챌린지 기능
> - 챌린지는 자동으로 수행되며 100% 완료 후 업적 트로피 획득

### 4.  인증 기능
> - 산 정상 좌표값 기반으로 정상 및 등반높이 인증
> - 등반한 정상 개수와 누적 높이를 기록

### 5. 랭킹
> - 사용자가 인증한 데이터(높이, 완료한 챌린지)를 기반으로  점수를 산출하여 랭킹시스템 생성

### 6. 관리자(Admin)
> - 회원관리 : 전체 회원 정보 조회(신고 내역 등) 및 회원 정보 삭제 기능
> - 챌린지 관리 : 챌린지 업로드 및 삭제
> - 카테고리 관리 : 카테고리 수정 및 삭제 기능

## 역할분담
---
| 훈련생 | 역할  | 담당업무 |
| --- | --- | ---- |
| 옥찬혁 | 팀장  | 백엔드  |
| 나정균 | 팀원  | 백엔드  |
| 민지원 | 팀원  | 백엔드  |
| 김경혜 | 팀원  | 프론트  |
| 윤혜원 | 팀원  | 프론트  |
| 진채영 | 팀원  | 프론트  |

