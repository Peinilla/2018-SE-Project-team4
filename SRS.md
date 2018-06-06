# SoftwareRequirements Specification
==수정시작==

#### Version 1.0

#### May 14, 2018

#### Project Team:
  
  <4조>
  
  20131961 김민조
  
  20132569 조성주
  
  20132862 김현빈
  
  20134220 우정현
  
  20134695 김명규
  
  20162379 김성연
  
#### Document Author(s):

  김성연
  
  
  김현빈
  
  
  김민조
  
  
  김명규
  
  
  조성주
  
  
  우정현
  
  
  ##### 목차
  
  1. 소개(Introduction)
  
    1.1 목적(Purpose)
    1.2 참조(Reference)
    1.3 독자대상(Intend Audience)
  
  2. 전체 설명(Overall Description)
    
    2.1 제품기능(Product Feature)
    2.2 운영환경(Operation Environment)

  3. 유스케이스(Usecase)
  
  4. 외부 인터페이스 요구사항(External Interface Requirement)
  
    5.1 사용자 인터페이스(User Interface)
    5.2 소프트웨어 인터페이스(Software Interface)
  
  5. 기능 이외의 다른 요구사항(Other Nonfuctional Requirement)
  
    5.1 성능 요구사항(Performance Requirement)
    5.2 성능(Performance)
    5.3 사용성(Usability)
  
  6. 부록(Appendix)

### 1       소개(Introduction)

##### 1.1     목적(Purpose)

이 프로젝트의 목적은 파일을 비교하고 병합하는 것이 주요 기능인 SimpleMerge를 구현하는 것이다.

##### 1.2     참조(Reference)

WinMerge, WinDiff, BeyondCompare와 같은 유사한 현존하는 제품을 참조한다.

##### 1.3     독자대상(Intend Audience)

이 SRS 문서는 중앙대학교 ICT 소프트웨어공학 팀 프로젝트를 위해서 작성되었고, 팀 프로젝트에 참여한 팀원들과 교수님 그리고 그 외의 수강생들이 독자 대상이다.


### 2      전체 설명(Overall Description)

##### 2.1     제품기능(Product Feature)

(1) 두개의 편집 패널과 버튼

  두개의 편집 패널이 메인 윈도우에 표시된다.
  
  각 편집 패널 위에는 "불러오기", "편집", "저장" 버튼이 존재한다.
  
  메인 윈도우에는 "비교", "병합" 버튼이 존재한다.
  
  
(2) 파일의 불러오기 / 편집 / 저장 기능

  불러오기 : 시스템에서 파일 내용을 로드하고 해당 패널에 내용을 표시한다.
  
  편집 : 토글 버튼으로 활성화 된 상태에서 해당 패널 내용을 편집할 수 있다.
  
  저장 : 해당 패널 내용을 시스템에 저장한다.
  
 
(3) 두개의 파일을 비교하는 기능

  비교 : 버튼을 누르면 라인별로 지정된 글꼴과 색으로 두 파일을 비교한다.
  
  비교 결과는 "diff"프로그램에서 생성된 결과와 같다.
  

(4) 두개의 파일을 병합하는 기능

  병합 : 두가지의 기능이 존재한다. 
  
  "CopytoRight"는 왼쪽패널에서 선택한 블록을 오른쪽 패널에 표시된 파일로 복사한다.
  
  "CopytoLeft"는 왼쪽패널에 표시된 파일이 복사된다.


##### 2.2     운영환경(Operation Environment)

Java Window form을 이용한 개발


### 3      유스케이스(Usecase)

![default](https://user-images.githubusercontent.com/33536706/39981770-f10963f6-578c-11e8-83a1-661597217129.jpg)

##### UC1 Load
##### UC2 Edit
##### UC3 Save
##### UC4 Merge
##### UC5 Diff
##### UC6 CopytoRight
##### UC7 CopytoLeft

#### UC1 Flow of Events for the Load Use Case

1.1 Preconditions:
텍스트 파일이어야 한다.

1.2 Main Flow:
프로그램을 실행했을 때 제일 먼저 활성화 되는 기능으로서, 프로그램이 실행되면 2개의 패널 위쪽에 로드 버튼이 활성화된다. 
로드 버튼을 클릭시 파일 선택창이 나타나 사용자가 파일[E1]을 선택해 온다.

1.3 Subflows:
없음.

1.4 Alternative Flows:
[E1] 파일은 텍스트 파일이어야 한다. 만약 텍스트 파일이 아닌 경우, 경고창을 띄운 후 파일을 다시 선택하게 한다.
파일이 로드되어 있지 않은 패널은 편집 버튼이 비활성화 되도록 한다.


#### UC2 Flow of Events for the Edit Use Case

2.1 Preconditions:
패널에 파일이 로드된[UC1] 상태여야 한다.

2.2 Main Flow:
편집 버튼을 누르면 파일의 내용을 편집한다. 
각각의 파일은 편집 기능이 따로 적용되며, 한쪽이 편집되면 다른 한쪽에 영향을 끼치지 않는다. 

2.3 Subflows:
없음.

2.4 Alternative Flows:
[S1] 저장버튼이 활성화 된다. 저장버튼을 제외한 모든 버튼은 비활성화 된다.

#### UC3 Flow of Events for the Save Use Case

3.1 Preconditions:
파일이 로드되어[UC1] 있어야하고, 편집 사항이 있어서 저장버튼이 활성화되어 있어야한다.[UC2]

3.2 Main Flow:
각각의 패널에 저장버튼이 있으며, 각 패널의 저장버튼을 누르면 그 해당 패널의 편집된 내용이 저장된다.

3.3 Subflows:
없음.

3.4 Alternative Flows:
없음.

#### UC4 Flow of Events for the Diff Use Case

4.1 Preconditions: 
두 개의 파일이 로드된 상태여야 한다.[UC1]

4.2 Main Flow: 
비교 버튼을 클릭시 LCS(Longest Common Subsequence) 알고리즘을 사용하여 두 파일을 비교 분석하고, 그 차이점을 하이라이트로 표시된다.

4.3 Subflows: 
비교 버튼을 누르면 비교된 내용이 지정된 글꼴과 배경으로 파일 내용창에 표시된다.

4.4 Alternative Flows:
두 파일이 차이점이 없으면, 두 파일이 일치함을 알려주는 메시지가 표시된다.

#### UC5 Flow of Events for the Merge Use Case

5.1 Preconditions:
양쪽의 패널에 파일이 로드된 상태여야 하고[UC1], 비교가 실행된 상태여야 한다.[UC4]

5.2 Main Flow:
비교가 된 상태일때, 각 패널의 차이가 하이라이트 표시되어 있는 상태이다.
병합 버튼은 2가지가 있으며, CopytoRight 버튼과 CopytoLeft 버튼으로 나뉜다.
차이를 나타내는 블록을 CopytoRight 버튼과 CopytoLeft 버튼을 통해서 한쪽의 차이점인 부분을 반대쪽 패널로 병합한다.

5.3 Subflows:
한 패널의 파일 내용을 반대쪽 패널로 덮어 씌운다.

5.4 Alternative Flows:
없음.

#### UC6 Flow of Events for the CopytoRight Use Case

6.1 Preconditions:
왼쪽 파일이 로드된후[UC1], 비교된 상태가 되어야 한다.[UC4]

6.2 Main Flow:
CopytoRight 버튼을 누르면 왼쪽 패널에서 선택한 블록을 오른쪽 패널에 표시된 파일로 복사한다.

6.3 Subflows:
없음.

6.4 Alternative Flow:
없음.

#### UC7 Flow of Events for the CopytoLeft Use Case

7.1 Preconditions:
오른쪽 파일이 로드된후[UC1], 비교된 상태가 되어야 한다.[UC4]

7.2 Main Flow:
CopytoLeft 버튼을 누르면 오른쪽 패널에서 선택한 블록을 왼쪽 패널에 표시된 파일로 복사한다.

7.3 Subflows:
없음.

7.4 Alternative Flow:
없음.


### 4      외부 인터페이스 요구사항(External Interface Requirement)

##### 4.1     사용자 인터페이스(User Interface)

프로그램은 사용자에게 keyboard, mouse, button event들을 허용해야만 한다.

##### 4.2     소프트웨어 인터페이스(Software Interface)

JUnit 및 EasyMock를 이용하여 프레임워크 테스트를 한다. MVC Architecture 기반으로 설계한다.

### 5      기능 이외의 다른 요구사항(Other Nonfunctional Requirement)

##### 5.1     성능 요구사항(Performance Requirement)

JAVA를 이용하기 때문에 최신버젼의 JDK 필요

##### 5.2     성능(Performance)

Simple Merge 프로그램의 주요기능인 diff와 merge가 빠른 시간내에 신속하게 작동되도록 한다.

##### 5.3     사용성(Usability)

사용자가 Simple Merge 프로그램을 잘 사용하기 위해서 텍스트가 잘 보여야 되며, 텍스트 파일들을 수정할 수 있어야 한다.


### 6      부록(Appendix)

#### 부록 A: 용어집(Glossary)

![screenshot2](https://user-images.githubusercontent.com/38933466/41019605-4dd2c2d4-699a-11e8-9c0f-22dbca438d7c.png)

#### 부록 B: 분석모델(Analysis Model)

Usecase diagram, class diagram 등과 같은 관련된 분석모델을 설명한다.

#### 부록 C: 문제 목록(Issues List)



