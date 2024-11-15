package racingcar.car;

import camp.nextstep.edu.missionutils.Randoms;

public class CarMovePolicy {
    private static final int MIN_PICK_NUM = 0;
    private static final int MAX_PICK_NUM = 9;
    private static final int STOP = 0;
    private static final int MOVE_DISTANCE = 1;
    private static final int MOVE_CRITERIA = 4;

    public int drawOut(){
        int pickNumber = Randoms.pickNumberInRange(MIN_PICK_NUM,MAX_PICK_NUM);

        if(pickNumber >= MOVE_CRITERIA ){
            return MOVE_DISTANCE;
        }
        return  STOP;
    }
}
