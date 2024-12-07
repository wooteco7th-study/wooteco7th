## 🎯 비즈니스 로직 분석
- 메뉴 추천 과정
    - 월요일에 추천할 카테고리를 무작위로 추천
    - 각 코치에게 월요일에 먹을 메뉴 추천
    - 화, 수, 목, 금도 동일하게 반복
- 코치의 이름은 최소 2글자, 최대 4글자
- **코치는 최소 2명, 최대 5명까지 식사**
- **각 코치는 최소 0개, 최대 2개의 못먹는 메뉴가 있다 (,로 구분해서 입력)**
    - 먹지 못하는 메뉴가 없으면 빈문자열 입력
    - 추천을 못하는 경우는 발생하지 않는다
- **한 주에 같은 카테고리는 최대 2회까지만 고를 수 있다**
- **각 코치에게 한주에 중복되지 않는 메뉴를 추천**
- **Randoms.pickNumberInRange()의 결과가 1이면 일식, 2면 한식, 3이면 중식, 4면 아시안, 5면 양식을 추천**
- **추천 메뉴는 Randoms.shuffle(menus).get(0) 이렇게 셔플 후 첫번째 요소로 추천**
- **5일 한번에 추천하지 않고, 코치 별로 하루에 하나씩 추천해줘야함** 

## 🎯 클래스 및 기능

### ✅ domain

#### MenuType
- [] 메뉴의 정보를 정의

#### CoachName
- [] 코치의 이름을 저장

##### 예외 처리
- [] 코치의 이름이 2~4글자 범위를 벗어난 경우

#### HateMenu
- [] 못먹는 메뉴들을 정의

##### 예외 처리
- [] 못먹는 메뉴가 2개를 초과하는 경우

#### Coach
- [] 코치의 정보를 저장
- [] 이름, 싫어하는 메뉴, 먹을 메뉴

#### CoachGroup
- [] 코치들의 정보를 저장

#### CoachGroupGenerator
- [] 코치 그룹을 생성

#### RandomMenuTypesGenerator
- [] 메뉴 타입들을 생성

#### RandomMenuGenerator
- [] 랜덤 메뉴를 생성

#### MenuSystem
- [] 코치 그룹을 저장
- [] 추천된 메뉴 타입들을 저장
- [] 각 코치들에게 메뉴를 추천

### ✅ util

#### ListValidator
- [] 리스트 사이즈 검증

#### NumberValidator
- [] 숫자 범위 검증

#### StringParser
- [] 문자열을 문자열 리스트로 변환
- [] 문자열의 특정 패턴을 제거

#### LoopTemplate
- [] 루프 템플릿

### ✅ view

#### ConsoleInputView
- [] 사용자가 입력한 코치의 이름을 읽음
- [] 사용자가 입력한 코치별 못먹는 메뉴를 읽음

#### ConsoleOutputView
- [] 사용자에게 메뉴 추천 관련 메세지를 출력

### ✅ controller

#### MenuController
- [] 애플리케이션 흐름을 제어하는 클래스

### ✅ error

#### AppException
- [] 커스텀 예외 클래스

#### ErrorMessage
- [] 에러 메세지 정의