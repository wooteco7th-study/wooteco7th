## 🎯 비즈니스 로직 분석

- **초기 설정(핵심)**
    - 프로그램 시작 시 역, 노선, 구간 정보를 초기 설정
    - 거리와 소요 시간은 양의 정수이며 단위는 km와 분을 의미

- 경로 조회 기능
  - 출발역과 도착역을 입력받아 경로를 조회
  - 경로 조회 시 [INFO] Prefix와 함께 총 거리, 총 소요 시간도 함께 출력
  - 경로 조회 기준은 최단거리 ,최소시간이 있음

- 예외 처리
  - 경로 조회 시 출발역과 도착역이 같으면 에러를 출력
  - 경로 조회 시 출발역과 도착역이 연결되어 있지 않으면 에러 출력
  - 그 외 정상적으로 프로그램이 수행되지 않은 경우 에러 출력

- **최단 경로 로직 구현시 제공된 라이브러리 활용(핵심)**
  - 라이브러리로 제공된 다익스트라 알고리즘을 사용
  - 최단 거리를 구할 시 거리를 가중치로
  - 최소 시간을 구할 시 시간을 가중치로
  - 출발지, 도착지 입력시 모든 그래프의 정보를 다 입력해야함

- 프로그래밍 요구사항 Station, Line
  - 각 클래스의 기본 생성자를 추가할 수 없음
  - 필드 name 접근 제어자를 변경할 수 없음
  - 가능한 세터를 추가하지 않고 구현

- 프로그래밍 요구사항 StationRepository, LineRepository
  - 작성된 메서드는 수정할 수 없고, 필요에 따라 메서드를 자유롭게 추가할 수 있음


## 🎯 클래스 및 기능

### ✅ domain

#### Station
- [] 지하철 역의 정보를 저장

#### Line
- [] 노선의 정보를 저장

#### Edge
- [] 간선의 정보를 저장
- [] 시작역, 도착역, 노선
- [] 거리 및 소요 시간

#### EdgeInfo
- [] 초기화를 위한 간선 데이터 정의

#### Subway
- [] 간선들의 정보를 저장
- [] 다익스트라를 통해 최단 거리를 찾음
- [] 다익스트라를 통해 최단 시간을 찾음

#### MainCommand
- [] 메인 화면의 동작을 정의

#### PathCommand
- [] 경로 기준 동작을 정의

#### StationType
- [] 역 타입을 정의

#### LineType
- [] 노선 타입을 정의

### ✅ util

#### ListValidator
- [] 리스트 중복 검증

#### LoopTemplate
- [] try-catch 루프 템플릿

### ✅ view

#### ConsoleInputView
- [] 사용자가 입력한 문자열을 읽음

#### ConsoleOutputView
- [] 사용자에게 메세지들을 출력

### ✅ controller

#### SubwayController
- [] 애플리케이션의 흐름을 제어하는 클래스

### ✅ error

#### ErrorMessage
- [] 에러 메세지 정의

#### CustomIllegalArgumentException
- [] IllegalArgumentException의 하위 클래스

#### CustomIllegalStateException
- [] IllegalStateException의 하위 클래스
