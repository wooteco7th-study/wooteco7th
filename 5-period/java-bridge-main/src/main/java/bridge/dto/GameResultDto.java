package bridge.dto;

public record GameResultDto(GameBoardDto gameBoardDto, boolean isSuccess, int attempt) {
}
