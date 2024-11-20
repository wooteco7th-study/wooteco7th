# java-lotto-precourse

### 구현 시작 : 18:10
### 테스트 통과 : 22:35

## 📝 클래스 및 기능

### ✅ domain

#### Lotto
- [X] 로또 번호를 저장
- [X] 특정 숫자가 로또 번호와 일치하는지 확인
- [X] 로또를 생성할 때 오름차순을 수행

##### 예외처리
- [X] 중복 숫자가 존재하는 경우
- [X] 숫자가 1 ~ 45 범위를 벗어나는 경우

#### LottoStore
- [X] 구입 금액 만큼 로또를 구매

#### LottoRank
- [X] 로또 랭크를 정의
- [X] 숫자 매칭 갯수와 보너스 매칭 여부로 랭크를 찾음

#### Money
- [X] 구입 금액을 저장 한다
- [X] 구입 금액을 특정 숫자로 나눈 몫을 반환

##### 예외처리
- [X] 구입 금액이 1,000원 단위가 아닌 경우
- [X] 구입 금액이 1,000원 미만인 경우

#### BonusNumber
- [X] 보너스 숫자를 저장

##### 예외처리
- [X] 숫자가 1 ~ 45 범위를 벗어나는 경우

#### WinningNumber
- [X] 당첨 로또와 보너스 번호의 정보를 저장
- [X] 당첨된 로또 랭크를 계산 한다

##### 예외처리
- [X] 당첨 번호와 보너스 번호가 중복된 경우

#### WinningResult
- [X] 당첨된 로또 랭크 결과를 계산

#### PrizeResult
- [X] 총 수익률을 계산

### ✅ util

#### ListValidator
- [X] 리스트 중복 검증
- [X] 리스트 사이즈 검증
- [X] 리스트 요소 범위 검증

#### NumberValidator
- [X] 숫자 범위 검증
- [X] 숫자 단위 검증
- [X] 숫자 중복 검증

#### StringParser
- [X] 문자열을 구분자 기준으로 숫자 리스트로 파싱
- [X] 문자열을 숫자로 파싱

#### LoopTemplate
- [X] try-catch 루프 정의

### ✅ error

#### ErrorMessage
- [X] 에러 메세지 정의

#### AppException
- [X] `IllegalArgumentException`을 상속 받는 예외 클래스

##### 하위 클래스
- [X] InvalidListDuplicatedException
- [X] InvalidListSizeException
- [X] InvalidNumberFormatException
- [X] InvalidNumberRangeException

### ✅ view

#### ConsoleInputView
- [X] 사용자가 입력한 숫자를 읽음
- [X] 사용자가 입력한 숫자들을 읽음

#### ConsoleOutputView
- [X] 구입 금액 입력 메세지 출력
- [X] 구입한 로또들 출력
- [X] 당첨 번호 입력 메세지 출력
- [X] 보너스 번호 입력 메세지 출력
- [X] 당첨 결과 출력
- [X] 총 수익률 출력

### ✅ controller

#### LottoController
- [X] 사용자에게 구입 금액을 요청
- [X] 사용자에게 구입한 로또를 응답
- [X] 사용자에게 당첨 번호 요청
- [X] 사용자에게 보너스 번호 요청
- [X] 사용자에게 당첨 로또들을 응답
- [X] 사용자에게 총 수익률을 응답
