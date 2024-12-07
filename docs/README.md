# 📝 기능 목록 📝

## 입력 기능

- [x] 코치 이름 입력받기
    - [x] 빈 값을 입력하지 않았는지 검증
    - [x] 이름: 최소 2글자 - 최대 4글자 검증 (도메인에서)
    - [] 중복된 이름 없는지 검증 (도메인에서)
    - [] 인원: 최소 2명 - 최대 5명 검증 (도메인에서)
- [] 코치 당 못먹는 메뉴 입력받기
    - [] 메뉴에 존재하는지 검증 (도메인에서)
    - [] 못먹는 메뉴: 최소 0개(빈값) - 최대 2개 (도메인에서)

## 핵심 기능

- [] 잘못된 입력 받을 시 IllegalArgumentException 을 발생시키고,
  "[ERROR]"로 시작하는 에러 메시지를 출력 후 그 부분부터 입력을 다시 받는다.
- [] 같은 카테고리 최대 2회
- [] 같은 메뉴 중복 x
- [] 카테고리: pickNumberInRange() 사용
    - 1-일식, 2-한식, 3-중식, 4-아시안, 5-양식
- 메뉴
    - [] shuffle() 사용 후 첫 번째 값 사용
    - 코치에게 추천할 메뉴를 정할 때 이미 추천한 메뉴, 먹지 못하는 메뉴도 포함된 리스트를 전달해야 한다.
    - 추천할 수 없는 메뉴인 경우 다시 섞은 후 첫 번째 값을 사용해야 한다.

## 출력 기능

- [] 메뉴 추천 결과 출력
- [] 추천 완료 메세지 출력

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
