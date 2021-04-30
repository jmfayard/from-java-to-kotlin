import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pin.PinGuesser;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PinGuesserTest {
    PinGuesser pinGuesser = new PinGuesser();

    private static Stream<Arguments> testSingleDigitParameters() {
        return Stream.of(
                Arguments.of("1", Set.of("1", "2", "4")),
                Arguments.of("2", Set.of("1", "2", "3", "5")),
                Arguments.of("3", Set.of("2", "3", "6"))
        );
    }

    @ParameterizedTest
    @MethodSource("testSingleDigitParameters")
    void testSingleDigit(String observedPin, Set<String> expected) {
        Set<String> actual = pinGuesser.getPINs(observedPin);
        assertEquals(expected, actual);
    }

}
