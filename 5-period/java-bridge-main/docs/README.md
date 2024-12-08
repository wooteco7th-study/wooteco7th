# 📋 기능 목록

## 다리 길이 입력
- 
- 입력/ 출력 형식
    - 숫자
- 예외
    - [x] 타입 : 문자열일 경우 → parser
    - [x] 범위 : 0 이하일 경우 → validator
    - [x] 단위 : X → validator
### 다리
- 위아래 둘 중 하나의 칸만 건널 수 있는 다리
- 위아래 두 칸으로 이루어진 다리
    - 왼쪽에서 오른쪽 (다리의 길이만큼 오른쪽으로 가면 종료)
    - 위아래 둘 중 하나의 칸 이동
- **다리의 길이를 숫자로 입력받고 생성**
    - 다리를 생성할 때 위 칸과 아래 칸 중 건널 수 있는 칸은 0과 1 중 랜덤 값을 이용해서 정한다.
        - 랜덤 값이 **0인 경우 아래 칸**, **1인 경우 위 칸**이 건널 수 있는 칸이 된다.
    - **위 칸을 건널 수 있는 경우 U**, **아래 칸을 건널 수 있는 경우 D**값으로 나타낸다.
### 칸
- 요소

### 라이브러리
- 다리 생성
    - `bridgeNumberGenerator` : 0 , 1
    - `bridgeMaker` : 0, 1로 D, U 표시 -> 문자열 리스트 반환

## 이동 칸 선택
- [x] 
- 입력/ 출력 형식
    - U 또는 D
- 종료
    - 다리를 끝까지 건너면 게임이 종료된다.
    - 다리를 건너다 실패하면 게임을 재시작하거나 종료할 수 있다.
- 예외
    - [x] 타입
        - [x] U 또는 D가 아닌 문자
### 플레이어
- 다리가 생성되면 플레이어가 이동할 칸을 선택한다.
    - 이동할 때 **위 칸은 대문자 U**, **아래 칸은 대문자 D**를 입력한다.
    - 이동한 칸을 건널 수 있다면 **O**로 표시한다. 건널 수 없다면 **X**로 표시한다.
- 다리를 끝까지 건너면 게임이 종료된다.
- 다리를 건너다 실패하면 게임을 **재시작**하거나 **종료**할 수 있다.
    - 재시작해도 **처음에 만든 다리로 재사용**한다.
    - 게임 결과의 총 시도한 횟수는 첫 시도를 포함해 **게임을 종료할 때까지 시도한 횟수**를 나타낸다.
#### UpDown
- 위, 아래 표시
- enum
#### LogType
- O, X, " "
#### BridgeLog
- upBridgeLog
- downBridgeLog

> 1. BridgeGame : 다리의 정보와 사용자 입력 정보를 비교하여 LogType을 판단한다.
> 2. BridgeLog: Up인지 Down인지에 따라 BridgeLog가 적절히 저장한다.
     > 판단과 저장을 분리하기!

## 게임 재시작 여부 입력
- 입력/ 출력 형식
    - [x] R 또는 Q
- 예외
    - [x] 타입 : R 또는 Q 외의 문자


## 최종 게임 결과 출력
- [x] 마지막으로 진행한 게임 결과 출력
- [x] 게임 성공 여부 출력
- [x] 총 시도 횟수 출력


# 🎱 프로그래밍 요구사항

### 코드 컨벤션
- [x] indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현 (2까지만 허용)
- [x] 3항 연산자 사용 금지
- [x] 함수(또는 메서드)의 길이가 **10라인**을 넘지 않도록 구현
- [x] else 예약어 사용 금지
- [x] 메서드의 **파라미터 개수는 최대 3개**까지만 허용한다.

### 구현
- [x]  InputView, OutputView, BridgeGame, BridgeMaker, BridgeRandomNumberGenerator 클래스의 요구사항을 참고하여 구현한다.
    - [x] InputView 클래스에서만 camp.nextstep.edu.missionutils.Console 의 readLine() 메서드를 이용해 사용자의 입력을 받을 수 있다.
    - [x] BridgeGame 클래스에서 InputView, OutputView 를 사용하지 않는다.
```java
public class InputView {

    public int readBridgeSize() {
        return 0;
    }

    public String readMoving() {
        return null;
    }

    public String readGameCommand() {
        return null;
    }
}

```
OutputView의 메서드의 **이름은 변경할 수 없고**, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
```java
public class OutputView {

    public void printMap() {
    }

    public void printResult() {
    }
}

```
- BridgeGame의 **메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.**
```java
public class BridgeGame {

    public void move() {
    }

    public void retry() {
    }
}

```
- **`BridgeMaker`의 필드(인스턴스 변수)를 변경할 수 없다.**
- **`BridgeMaker`의 메서드의 시그니처(인자, 이름)와 반환 타입은 변경할 수 없다.**
```java
public class BridgeMaker {

    public List<String> makeBridge(int size) {
        return null;
    }
}

```
- [x] 도메인 로직에 단위 테스트 구현 (UI 로직 제외)
- [x] 핵심 로직과 UI 로직을 분리해 구현
    - [x] InputView: 입력 처리
    - [x] OutputView: 출력 처리

### 입력 요구사항
- [x] 재입력
    -  사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`를 발생시키고, "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
    - `Exception`이 아닌 `IllegalArgumentException`, `IllegalStateException` 등과 같은 명확한 유형을 처리한다.
### 출력 요구사항
- [x] 프로그래밍 실행 결과 예시와 동일하게 입력과 출력이 이루어져야 한다.

#### 💻 프로그래밍 실행 결과 예시
```
다리 건너기 게임을 시작합니다.

다리의 길이를 입력해주세요.
3

이동할 칸을 선택해주세요. (위: U, 아래: D)
U
[ O ]
[   ]

이동할 칸을 선택해주세요. (위: U, 아래: D)
U
[ O | X ]
[   |   ]

게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)
R
이동할 칸을 선택해주세요. (위: U, 아래: D)
U
[ O ]
[   ]

이동할 칸을 선택해주세요. (위: U, 아래: D)
D
[ O |   ]
[   | O ]

이동할 칸을 선택해주세요. (위: U, 아래: D)
D
[ O |   |   ]
[   | O | O ]

최종 게임 결과
[ O |   |   ]
[   | O | O ]

게임 성공 여부: 성공
총 시도한 횟수: 2

```

```
다리 건너기 게임을 시작합니다.

다리의 길이를 입력해주세요.
3

이동할 칸을 선택해주세요. (위: U, 아래: D)
U
[ O ]
[   ]

이동할 칸을 선택해주세요. (위: U, 아래: D)
U
[ O | X ]
[   |   ]

게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)
Q
최종 게임 결과
[ O | X ]
[   |   ]

게임 성공 여부: 실패
총 시도한 횟수: 1

```

### 라이브러리
- [x] Random 값 추출은 제공된 `bridge.BridgeRandomNumberGenerator`의 `generate()`를 활용한다.
```java
int number = bridgeNumberGenerator.generate();
```
- [x] `BridgeRandomNumberGenerator`, `BridgeNumberGenerator` **클래스의 코드는 변경할 수 없다.**
- [x] [`camp.nextstep.edu.missionutils`](https://github.com/woowacourse-projects/mission-utils)에서 제공하는 `Console` API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.
