# 숫자 야구
## 기능 목록

### ✅ 랜덤 숫자 생성하기
- 1에서 9까지 **서로 다른 임의의 수 3개**를 선택한다.

### ✅ 숫자 입력하기
-  게임 플레이어는 컴퓨터가 생각하고 있는 **서로 다른 3개의 숫자**를 입력한다.
  - 1에서 9 사이 숫자여야한다.

### ✅ 결과 출력하기
- 입력한 수에 대한 결과를 볼, 스트라이크 개수로 표시한다.
    - 같은 수가 같은 자리에 있으면 스트라이크,
    - 다른 자리에 있으면 볼,
    - 같은 수가 전혀 없으면 낫싱
- 맞추지 못할 경우 다시 숫자를 입력받는다.
- 볼 -> 스트라이크 순으로 출력하며, 아무것도 없을 경우 낫싱을 출력한다.

### ✅ 재시작 여부 입력받기
- 게임이 끝난 경우 재시작/종료를 구분하는 1과 2 중 하나의 수를 입력받는다.


### 예시 출력
```  
숫자 야구 게임을 시작합니다.  
숫자를 입력해주세요 : 123
1볼 1스트라이크  
숫자를 입력해주세요 : 145
1볼  
숫자를 입력해주세요 : 671
2볼  
숫자를 입력해주세요 : 216
1스트라이크  
숫자를 입력해주세요 : 713
3스트라이크  
3개의 숫자를 모두 맞히셨습니다! 게임 종료  
게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.  
1  
숫자를 입력해주세요 : 123
1볼  
...  

``` 

### 추가 요구사항
- 이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
- 게임을 종료한 후 게임을 다시 시작하거나 완전히 종료할 수 있다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

### 라이브러리
- `camp.nextstep.edu.missionutils`에서 제공하는 `Randoms` 및 `Console` API를 사용하여 구현해야 한다.
    - Random 값 추출은 `camp.nextstep.edu.missionutils.Randoms`의 `pickNumberInRange()`를 활용한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.

#### 사용 예시

```java  
List<Integer> computer = new ArrayList<>();  
while (computer.size() < 3) {  
    int randomNumber = Randoms.pickNumberInRange(1, 9);    
    if (!computer.contains(randomNumber)) {        computer.add(randomNumber);    }}  
```  
