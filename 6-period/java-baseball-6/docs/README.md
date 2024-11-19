

#### 시작 : 20:00
#### 구현 완료 : 22:33

## 기능 구현 목록 📝

### 클래스 및 기능

### ✅ domain

#### UserNumber
- [X] 사용자의 숫자를 저장

##### 예외 처리
- [X] 숫자가 중복된 경우
- [X] 숫자의 범위가 1~9를 벗어난 경우
- [X] 숫자의 갯수가 3이 아닌 경우

#### ComputerNumber
- [X] 컴퓨터가 생성한 숫자를 저장
- [X] 컴퓨터 숫자가 특정 숫자를 포함하는지 확인
- [X] 컴퓨터 숫자가 특정 idx의 값이 특정 숫자와 일치하는지 확인

#### GameScore
- [X] 볼과 스트라이크를 저장

#### BaseBallGame
- [X] 게임 스코어를 저장
- [X] 올 스트라이크인지 확인
- [X] 스코어 타입 계산
- [X] 컴퓨터 숫자 생성

#### BallStrikeChecker
- [X] 볼 스트라이크 계산

### ✅ util

#### StringParser
- [X] 문자열을 숫자 리스트로 변환
- [X] 문자열을 숫자로 변환

#### ListValidator
- [X] 리스트 사이즈 검증
- [X] 리스트 중복 검증

#### NumberValidator
- [X] 숫자 범위 검증

### ✅ constant
#### GameRule
- [X] 게임 룰 정의

#### ScoreType
- [X] 스코어 타입 정의

#### GameCommand
- [X] 게임 커맨드 정의

### ✅ error

#### ErrorMessage
- [X] 에러 메세지 정의

#### AppException
- [X] `IllegalArgumentException` 하위 클래스

### ✅ controller

#### BaseBallGameController
- [X] 사용자에게 숫자 요청
- [X] 게임 스코어 응답
- [X] 사용자에게 게임 재시작 여부 요청 

### ✅ view

#### ConsoleInputView
- [X] 사용자가 입력한 숫자를 읽음
- [X] 사용자가 입력한 커멘드를 읽음

#### ConsoleOutputView
- [X] 사용자에게 숫자 요청 메세지 출력
- [X] 사용자에게 볼 스트라이크 결과 출력
- [X] 사용자에게 게임 종료 메세지 출력
- [X] 사용자에게 게임 재시작 여부 메세지 출력
