import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pin.PinGuesser;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PinGuesserTest {
    PinGuesser pinGuesser = new PinGuesser();

    private static Set<String> setOf(String input) {
        return Set.of(input.split(", "));
    }

    private static Stream<Arguments> testSingleDigitParameters() {
        return Stream.of(
                Arguments.of("1", Set.of("1", "2", "4")),
                Arguments.of("2", Set.of("1", "2", "3", "5")),
                Arguments.of("3", Set.of("2", "3", "6")),
                Arguments.of("4", Set.of("1", "4", "5", "7")),
                Arguments.of("5", Set.of("2", "5", "4", "6", "8")),
                Arguments.of("6", Set.of("3", "5", "6", "9")),
                Arguments.of("7", Set.of("4", "7", "8")),
                Arguments.of("8", Set.of("5", "7", "8", "0")),
                Arguments.of("9", Set.of("6", "8", "9")),
                Arguments.of("0", Set.of("0", "8")),
                Arguments.of("00", Set.of("00", "08", "80", "88")),
                Arguments.of("09", Set.of("06", "08", "09", "86", "88", "89")),
                Arguments.of("", Set.of()),
                Arguments.of("090", setOf("088, 880, 068, 860, 090, 898, 080, 888, 060, 868, 098, 890")),
                Arguments.of("0900", setOf("8880, 8980, 8600, 8688, 0808, 0908, 0680, 8680, 0600, 0688, 8808, 8908, 0880, 0980, 0608, 8800, 8888, 8900, 8988, 8608, 0800, 0888, 0900, 0988"))
        );

    }

    private static Stream<Arguments> invalidParams() {
        return Stream.of(
                Arguments.of("   "),
                Arguments.of("A"),
                Arguments.of("1A"),
                Arguments.of("👎🏻")
        );
    }

    @ParameterizedTest
    @MethodSource("testSingleDigitParameters")
    void testSingleDigit(String observedPin, Set<String> expected) {
        Set<String> actual = pinGuesser.getPINs(observedPin);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    void testInvalidInput(String invalidInput) {
        assertThrows(RuntimeException.class, () -> pinGuesser.getPINs(invalidInput));
    }

    void testCombineSolutions() {
        Set<String> actual = pinGuesser.combineSolutions(Set.of("12", "34"), Set.of("8", "0"));
        Set<String> expected = Set.of("128", "120", "348", "340");
        assertEquals(actual, expected);
    }
}
