package pairmatching.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import pairmatching.dto.TotalPairMatchResultDto;

public class PairFileWriter {


    private static final String BACKEND_FILENAME = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_FILENAME = "src/main/resources/frontend-crew.md";
    private static final String PAIR_FILENAME = "src/main/resources/pair.md";
    private static final String FILE_WRITE_FAILED = "파일 쓰기에 실패했습니다.";
    private static final String COLON = " : ";
    private static final String BLANK = " ";

    private PairFileWriter() {
    }

    public static void writeCrews(List<String> backendNames, List<String> frontendNames) {
        writeToSpecificFile(BACKEND_FILENAME, backendNames);
        writeToSpecificFile(FRONTEND_FILENAME, frontendNames);
    }

    public static void writePairMatching(List<TotalPairMatchResultDto> dtos) {
        writeToSpecificFileForPairMatching(PAIR_FILENAME, dtos);
    }

    public static void writeToSpecificFile(String fileName, List<String> contents) {
        try {
            writeCrewsToFile(fileName, contents);
        } catch (IOException exception) {
            throw new IllegalStateException(FILE_WRITE_FAILED);
        }
    }

    public static void writeToSpecificFileForPairMatching(String fileName, List<TotalPairMatchResultDto> dto) {
        try {
            writePairMatchingToFile(fileName, dto);
        } catch (IOException exception) {
            throw new IllegalStateException(FILE_WRITE_FAILED);
        }
    }

    private static void writeCrewsToFile(String fileName, List<String> contents) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : contents) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static void writePairMatchingToFile(String fileName, List<TotalPairMatchResultDto> dtos)
            throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (TotalPairMatchResultDto dto : dtos) {
                writeMatchingTitle(writer, dto);
                writeMatchingResult(writer, dto);
            }
        }
    }

    private static void writeMatchingResult(final BufferedWriter writer, final TotalPairMatchResultDto dto)
            throws IOException {
        for (List<String> result : dto.pairMatchResultDto().results()) {
            writer.write(String.join(BLANK, result));
            writer.newLine();
        }
        writer.newLine();
    }

    private static void writeMatchingTitle(final BufferedWriter writer, final TotalPairMatchResultDto dto)
            throws IOException {
        writer.write(dto.pairOrder().getCourse().name() + COLON + dto.pairOrder().getLevel().name());
        writer.newLine();
    }

//    // 단일 문자열 쓰기
//    public static void writeString(String content) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILENAME));
//            writer.write(content);
//            writer.close();
//        } catch (IOException exception) {
//            throw new IllegalStateException("파일 쓰기에 실패했습니다.");
//        }
//    }
//
//    // 파일에 내용 추가하기 (append)
//    public static void appendToFile(String content) {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILENAME, true));
//            writer.write(content);
//            writer.newLine();
//            writer.close();
//        } catch (IOException exception) {
//            throw new IllegalStateException("파일 추가 쓰기에 실패했습니다.");
//        }
//    }
}
