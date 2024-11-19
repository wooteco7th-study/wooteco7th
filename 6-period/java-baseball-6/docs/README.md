📝 java-baseball 기능 목록 📝

## 입력 기능

사용자가 잘못된 값을 입력할 경우 IllegalArgumentException을 발생시킨 후 애플리케이션 종료

- [x] 숫자 입력받기
    - [x] 숫자인지 검증
    - [x] 숫자 범위 1-9 검증 (도메인에서)
    - [x] 서로 다른 숫자인지 검증 (도메인에서)
    - [x] 3자리인지 검증 (도메인에서)
- [] 게임 재시작 or 종료 입력받기
    - [] 숫자인지 검증
    - [] 1 또는 2만 입력하였는지 검증 (도메인에서)

## 핵심 기능

- [] 랜덤 숫자 3개 생성하기
- [] 컴퓨터 숫자와 사용자 입력 숫자 비교하기
    - [] 스트라이크: 같은 수 같은 자리
    - [] 볼: 같은 수 다른 자리
    - [] 낫싱: 일치하는 숫자 없는 경우

## 출력 기능

- [] 게임 결과 표시
- [] 3개 숫자 모두 맞추었을 시, 게임종료 출력

✅ 과제 제출 전 확인 사항 ✅

- [] camp.nextstep.edu.missionutils에서 제공하는 Randoms 및 Console API를 사용하여 구현
    - [] Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 활용한다.
    - [] 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.