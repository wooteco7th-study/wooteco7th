package bridge.dto;

import bridge.domain.bridge.BridgeGame;

public record GameResultDto(GameBoardDto gameBoardDto, boolean isSuccess, int attempt) {
    public static GameResultDto from(final BridgeGame game) {
        return new GameResultDto(GameBoardDto.from(game.getBridgeLog()), game.isSuccess(), game.getAttempt());
    }
}
