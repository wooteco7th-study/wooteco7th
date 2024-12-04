# 📝 java-subway 기능 목록 📝

## 입력 기능

- [] `구입금액을 입력해 주세요.` 출력 후 금액 입력받기
    - [] 빈 값을 입력하지 않았는지 검증
    - [] 숫자 형식인지 검증

## 핵심 기능

- 잘못된 입력 받을 시 IllegalArgumentException 을 발생시키고,
  "[ERROR]"로 시작하는 에러 메시지를 출력
    - [] 경로 조회 시 출발역과 도착역이 같을 때 `[ERROR] 출발역과 도착역이 동일합니다.`
    - [] 경로 조회 시 출발역과 도착역이 연결되어 있지 않을 때
- 초기 설정 (repo 등록)
    - [] 지하철역: 교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역 등록
    - [] 노선: 2, 3, 신분당선
  ```markdown
   - 2호선: 교대역 - ( 2km / 3분 ) - 강남역 - ( 2km / 3분 ) - 역삼역
   - 3호선: 교대역 - ( 3km / 2분 ) - 남부터미널역 - ( 6km / 5분 ) - 양재역 - ( 1km / 1분 ) - 매봉역
   - 신분당선: 강남역 - ( 2km / 8분 ) - 양재역 - ( 10km / 3분 ) - 양재시민의숲역```

- 경로 조회
    - [] 최단 거리
    - [] 최소 시간
- [] 명령어 입력에 따른 프로그램 실행

## 출력 기능

- [] 메인 화면 출력하기
- [] 경로 기준 출력하기
- [] 조회 결과 출력하기: `[INFO]` 붙일 것

## ✅ 과제 제출 전 확인 사항 ✅

- 자바 21 버전 확인
- indent 최대 2까지인지 확인
- 3항 연산자 사용x
- 메서드 라인 최대 15라인
- if-else 사용x
- enum 활용하기
- camp.nextstep.edu.missionutils 에서 제공하는 Randoms 및 Console API 사용하기
    - Random 값 추출은 camp.nextstep.edu.missionutils.Randoms 의 pickUniqueNumbersInRange() 활용
    - 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console 의 readLine() 활용
