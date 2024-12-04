# 📋 기능 목록

## 메인 화면 출력 및 기능 선택

- [x]  초기 설정
- [x]  1. 경로 조회 또는 Q. 종료 입력받기
  - Q일 경우 종료
- [x]  기능 선택
- 입력/ 출력 형식
  - 1 또는 Q
- 예외
  - 문자 -> Q가 아닌 문자
  - 숫자 -> 1이 아닌 문자

> Q -> 문자인지 확인
>

## 경로 기준 선택

- [x]  1. 최단 거리 2. 최소 시간 B. 돌아가기 입력받기
- [x]  기능 선택
- 입력/ 출력 형식
  - 1, 2, B
- 예외
  - [x]  B 또는 1, 2가 아닌 문자

### 초기 설정

- [ ]  초기 설정을 수행한다.

### 초기 설정

- 프로그램 시작시 역, 노선, 구간 정보를 초기 설정한다.
- `거리`
  - km
  - 양의 정수
- `소요 시간`
  - 분
  - 양의 정수

## 출발역, 도착역 입력

- [ ]  출발역, 도착역 입력받기
- 입력/ 출력 형식
  - 지하철역 이름
- 예외
  - [ ]  존재하지 않는 지하철역 (각 입력에 대해)
  - [ ]  출발역과 도착역이 같은 경우
    - `[ERROR] 출발역과 도착역이 동일합니다.`
  - [ ]  출발역과 도착역이 연결되어 있지 않은 경우

### 지하철 노선도

- 지하철역
  - 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역
- 지하철 노선
  - 2호선, 3호선, 신분당선
- `2호선`
  - `교대역` - ( 2km / 3분 ) - `강남역` - ( 2km / 3분 ) - `역삼역`
- `3호선`
  - `교대역` - ( 3km / 2분 ) - `남부터미널역` - ( 6km / 5분 ) - `양재역` - ( 1km / 1분 ) - `매봉역`
- `신분당선`
  - `강남역` - ( 2km / 8분 ) - `양재역` - ( 10km / 3분 ) - `양재시민의숲역`

> [ ] Station - 지하철역, Line - 노선 클래스 제공
>

> [ ] StationRepository - 지하철역 상태 저장, LineRepository - 노선 상태 저장
>

### Station

- 지하철역 이름

### Line

- 출발 지하철역
- 종료 지하철역
- 걸린 시간
- 이동 거리

> 기준별 가중치 다르게 하여 총 시간을 구하고, 최단 시간 구하기(라이브러리 사용)
>

## 총 거리, 총 소요 시간 계산 및 출력

- [ ]  총 거리, 총 소요 시간 계산
  - 경로를 조회하는 기능을 구현
- [ ]  출력
- 입력/ 출력 형식
  - 출력 형식대로 [INFO]
- 예외
  - [ ]  [ ]

### 경로 조회 기능

- `출발역`과 `도착역`을 입력받아 경로를 조회
- `총 거리`, `총 소요 시간` 출력
- 기준:
  - `최단 거리`
  - `최소 시간`
- 예외

> [ ] jgrapht 라이브러리를 활용하면 간편하게 최단거리를 조회할 수 있음
>

## 재입력

- [ ]  초기와 같이 메인 화면 출력
- [ ]  초기화는 하지 않음

### 예외 처리

- 그 외 정상적으로 프로그램이 수행되지 않은 경우 에러를 출력한다.

# 🎱 프로그래밍 요구사항

### 코드 컨벤션

- [ ]  indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현 (2까지만 허용)
- [ ]  3항 연산자 사용 금지
- [ ]  함수(또는 메서드)의 길이가 **15라인**을 넘지 않도록 구현
- [ ]  else 예약어 사용 금지
- [ ]  단, 들여쓰기는 '2 spaces'가 아닌 '4 spaces'로 한다.

### 구현

- [ ]  클래스 활용
  - [ ]  `Application` 클래스를 활용해 구현해야 한다
  - `Application`의 패키지 구조는 변경하지 않는다.
  - Application 클래스에 있는 `Scanner`를 사용하고 별도의 Scanner 객체를 만들지 않는다.

```java
public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        ...
    }
}

```

- [ ]  `Station` - 지하철역, `Line` - 노선
  - [ ]  Station, Line 클래스를 활용하여 지하철역과 노선을 구현해야 한다.
  - [ ]  제공하는 각 클래스의 기본 생성자를 추가할 수 없다.
  - [ ]  필드(인스턴스 변수)인 name의 접근 제어자 private을 변경할 수 없다.
  - [ ]  가능하면 setter 메소드(ex. setXXX)를 추가하지 않고 구현한다.
- [ ]  `StationRepository` - 지하철역 상태 저장, `LineRepository` - 노선 상태 저장
  - [ ]  Station과 Line의 상태를 저장할 수 있는 StationRepository, LineRepository를 제공한다.
  - [ ]  필요 시 StationRepository, LineRepository 이 외 추가로 Repository를 만들 수 있다.
  - [ ]  추가로 생성되는 객체에 대해서 `XXXRepository` 네이밍으로 저장 클래스를 추가한다.
  - [ ]  객체들의 상태를 관리하기 위해서 `XXXRepository` 클래스를 활용해 저장 로직을 구현해야 한다.
  - [ ]  **작성된 메서드는 수정할 수 없고**, 필요에 따라 메서드를 자유롭게 `추가`할 수 있다.
- [ ]  도메인 로직에 단위 테스트 구현 (UI 로직 제외)
- [ ]  핵심 로직과 UI 로직을 분리해 구현
  - [ ]  `InputView`: 입력 처리
  - [ ]  `OutputView`: 출력 처리

### 입력 요구사항

- [ ]  재입력
  - [ ]  사용자가 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, `"[ERROR]"`로 시작하는 에러 메시지를 출력 후 **해당 부분부터 다시 입력을 받는다.**

### 출력 요구사항

- [ ]  프로그래밍 실행 결과 예시와 동일하게 입력과 출력이 이루어져야 한다.
- 기대하는 출력 결과는 [INFO] 를 붙여서 출력한다.
  - 출력값의 형식은 예시와 동일하게 한다.
- 에러 발생 시 [ERROR]를 붙여서 출력한다. 에러의 문구는 자유롭게 작성한다.

### 💻 프로그래밍 실행 결과 예시

- 경로 조회

```
## 메인 화면
1. 경로 조회
Q. 종료

## 원하는 기능을 선택하세요.
1

## 경로 기준
1. 최단 거리
2. 최소 시간
B. 돌아가기

## 원하는 기능을 선택하세요.
1

## 출발역을 입력하세요.
교대역

## 도착역을 입력하세요.
양재역

## 조회 결과
[INFO] ---
[INFO] 총 거리: 4km
[INFO] 총 소요 시간: 11분
[INFO] ---
[INFO] 교대역
[INFO] 강남역
[INFO] 양재역

## 메인 화면
1. 경로 조회
Q. 종료

...

```

### 라이브러리

- [ ]  jgrapht 라이브러리를 활용하면 간편하게 `최단거리`를 조회할 수 있음
  - [ ]  Dijkstra 알고리즘을 반드시 이해할 필요는 없고 미션에 적용할 정도로만 이해하면 됨
  - [ ]  `JGraphtTest` 클래스의 테스트를 활용하여 미션에 필요한 라이브러리의 기능을 학습할 수 있음
  - [ ]  `정점`(vertex)과 `간선`(edge), 그리고 가중치 개념을 이용
    - 정점: 지하철역
    - 간선: 지하철역 연결정보
    - 가중치: 거리 or 소요 시간
  - 최단 거리 기준 조회 시 가중치를 거리로 설정

```java
@Test
public void getDijkstraShortestPath() {
    WeightedMultigraph<String, DefaultWeightedEdge> graph
            = new WeightedMultigraph(DefaultWeightedEdge.class);
    graph.addVertex("v1");
    graph.addVertex("v2");
    graph.addVertex("v3");
    graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
    graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
    graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);

    DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
    List<String> shortestPath = dijkstraShortestPath.getPath("v3", "v1").getVertexList();

    assertThat(shortestPath.size()).isEqualTo(3);
}

```

> 최단거리 조회 -> 거리를 가중치
최단 시간 조회 -> 시간을 가중치
>
