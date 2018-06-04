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
  
  2. 전체 설명(Overall Description)

  3. 유스케이스(Usecase)
  
  4. 시스템(System Feature)
  
  5. 외부 인터페이스 요구사항(External Interface Requirement)
  
  6. 기능 이외의 다른 요구사항(Other Nonfuctional Requirement)
  

### 1       소개(Introduction)

##### 1.1     목적(Purpose)

이 프로젝트의 목적은 파일을 비교하고 병합하는 것이 주요 기능인 SimpleMerge를 구현하는 것이다.

##### 1.2     참조(Reference)

WinMerge, WinDiff, BeyondCompare와 같은 유사한 현존하는 제품을 참조한다.

### 2      전체 설명(Overall Description)

##### 2.1     제품기능(ProductFeature)

(1) 두개의 편집 패널과 버튼

  두개의 편집 패널이 메인 윈도우에 표시된다.
  각 편집 패널 위에는 "불러오기", "편집", "저장" 버튼이 존재한다.
  메인 윈도우에는 "비교", "병합" 버튼이 존재한다.

(2) 파일의 불러오기 / 편집 / 저장 기능

  불러오기 : 시스템에서 파일 내용을 로드하고 해당 패널에 내용을 표시
  편집 : 토글 버튼으로 활성화 된 상태에서 해당 패널 내용을 편집할 수 있다.
  저장 : 해당 패널 내용을 시스템에 저장한다.
  
(3) 두개의 파일을 비교하는 기능

  비교 : 버튼을 누르면 라인별로 지정된 글꼴과 색으로 두 파일을 비교한다. 비교 결과는 "diff"프로그램에서 생성된 결과와 같다.

(4) 두개의 파일을 병합하는 기능

  병합 : 두가지의 기능이 존재한다. "CopytoRight"는 왼쪽패널에서 선택한 블록을 오른쪽 패널에 표시된 파일로 복사한다.
  "CopytoLeft"는 왼쪽패널에 표시된 파일이 복사된다.

##### 2.2     운영환경(OperationEnvironment)

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
로드 버튼을 클릭시 파일 선택창이 나타나 유저가 파일[E1]을 선택해 온다.

1.3 Alternative Flows:
[E1] 파일은 텍스트 파일이어야 한다. 만약 텍스트 파일이 아닌 경우, 경고창을 띄운 후 파일을 다시 선택하게 한다.


#### UC2 Flow of Events for the Edit Use Case

2.1 Preconditions:
파일이 로드 된 상태여야 한다.

2.2 Main Flow:
편집 버튼을 누르면 파일의 내용을 편집한다. 

2.3 Subflows:
[S1] 저장버튼이 활성화 된다. 저장버튼을 제외한 모든 버튼은 비활성화 된다.

#### UC3 Flow of Events for the Save Use Case

3.1 Preconditions:
파일이 로드 되어 있어야하고, 편집 사항이 있어서 저장버튼이 활성화 되어있어야한다.

3.2 Main Flow:
저장 버튼을 누르면 편집된 내용이 저장된다.

#### UC4 Flow of Events for the Diff Use Case

4.1 Preconditions: 
두 개의 파일이 로드된 상태여야 한다.

4.2 Main Flow: 
비교 버튼을 클릭시 LCS(Longest Common Subsequence) 알고리즘을 사용하여 두 파일을 비교한다.

4.3 Subflows: 
비교 버튼을 누르면 비교된 내역이 지정된 글꼴과 배경으로 파일 내용창에 표시된다.

#### UC5 Flow of Events for the Merge Use Case

5.1 Preconditions:
두개의 파일이 비교된 상태여야 한다.

5.2 Main Flow:
병합 버튼은 2가지가 있으며, CopytoRight 버튼과 CopytoLeft 버튼으로 나뉜다.
차이를 나타내는 블록을 통과하고 그 차이를 병합한다.

#### UC6 Flow of Events for the CopytoRight Use Case

6.1 Preconditions:
왼쪽 파일이 로드된후, 비교된 상태가 되어야 한다.

6.2 Main Flow:
CopytoRight 버튼을 누르면 왼쪽 패널에서 선택한 블록을 오른쪽 패널에 표시된 파일로 복사한다.

#### UC7 Flow of Events for the CopytoLeft Use Case

7.1 Preconditions:
오른쪽 파일이 로드된후, 비교된 상태가 되어야 한다.

7.2 Main Flow:
CopytoLeft 버튼을 누르면 오른쪽 패널에서 선택한 블록을 왼쪽 패널에 표시된 파일로 복사한다.


### 4      시스템 특징(System Feature)

##### 4.1     시스템특징 X

3.X 철자확인과 같은몇 단어만으로특징을 설명한다. 각각의 시스템 특징에 대해서는 3.x.1 에서 3.x.3까지의 하위절을 반복한다.

###### 가       설명과 우선순위(Description and Priority)

​	기능에 대해간단하게 설명하고그것이 높은우선순위인지 낮은우선순위인지를 나타낸다. 우선순위는 프로젝트 중에변할 수 있는 동적인 것으로, 요구사항관리 툴을사용한다면 요구사항특성의 우선순위를정의한다.

###### 나       자극/응답 순서(Stimulus/Response Sequence)

​	입력 자극(사용자 행동, 외부 장비의 신호 또는 다른 자극)의순서와 이기능에 대한동작을 정의하는시스템 반응을나열한다. 이 자극들은 유스케이스의 초기 대화단계 또는 외부 시스템 이벤트에 해당한다.

###### 다       기능요구사항(Functional requirement)

​	이 기능과관련된 상세한기능 요구사항을항목으로 나열한다. 이것들은 사용자가 기능의 서비스를 수행하기 위해또는 유스케이스를 수행하기 위해 사용하는 소프트웨어의 기능들이다. 제품이 예상되는 에러 상황, 무효한입력과동작에대해 어떻게응답해야 하는지를 설명한다. 각각의 기능 요구사항에 유일한 레이블을 붙인다.

### 5      외부 인터페이스 요구사항(External Interface Requirement)

##### 5.1     사용자 인터페이스

l  시스템이 요구하는 각각의 사용자 인터페이스의 논리적인 특징을 설명한다. 따라야 할 GUI표준또는 제품스타일가이드에대한 참조

l  폰트, 아이콘, 버튼 레이블, 이미지, 색상 체계, 필드탭 순서, 공통으로 사용되는 컨트롤 등에 대한 표준

l  화면 레이아웃 또는 해상도 제약 조건

사용자 인터페이스의설계 상세내용은 SRS가 아닌 별도의 사용자 인터페이스 명세에 문서로 정리한다.

##### 5.2     소프트웨어 인터페이스(Software Interface)

이 제품과다른 소프트웨어컴포넌트(데이터베이스, 운영체제, 툴, 라이브러리, 통합 상업용 컴포넌트)간의 연결을설명한다. 소프트웨어 컴포넌트 간에 교환되는 메시지, 데이터와컨트롤 항목을설명한다. 외부 소프트웨어 컴포넌트가 요구하는 서비스와 컴포넌트 간의 통신 성격을 설명하고 소프트웨어 컴포넌트들이 공유할 데이터를 파악한다.

=> JUnit 및 EasyMock를 이용하여 프레임워크 테스트를 한다. MVC Architecture 기반으로 설계한다.

### 6      기능 이외의 다른 요구사항(Other Nonfunctional Requirement)

##### 6.1     성능 요구사항(PerformanceRequirement)

JAVA를 이용하기 때문에 최신버젼의 JDK 필요


