import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pin.combineSolutions
import pin.getPINs
import java.util.stream.Stream


class PinGuesserTest {

    @Test
    fun testCombineSolutions() {
        val actual = combineSolutions(setOf("12", "34"), setOf("8", "0"))
        val expected = setOf("128", "120", "348", "340")
        assertEquals(actual, expected)
    }

    @ParameterizedTest
    @MethodSource("testSingleDigitParameters")
    fun testSingleDigit(observedPin: String, expected: Set<String>) {
        assertEquals(expected, getPINs(observedPin))
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    fun testInvalidInput(invalidInput: String) {
        assertThrows(RuntimeException::class.java) {
            getPINs(invalidInput)
        }
    }

    companion object {

        // Help to create a set by copying from the console and pasting it here
        private fun setOf(input: String): Set<String> =
            input.split(", ".toRegex()).toSortedSet()

        @JvmStatic
        fun testSingleDigitParameters(): Stream<Arguments> = Stream.of(
            Arguments.of("1", setOf("1", "2", "4")),
            Arguments.of("2", setOf("1", "2", "3", "5")),
            Arguments.of("3", setOf("2", "3", "6")),
            Arguments.of("4", setOf("1", "4", "5", "7")),
            Arguments.of("5", setOf("2", "5", "4", "6", "8")),
            Arguments.of("6", setOf("3", "5", "6", "9")),
            Arguments.of("7", setOf("4", "7", "8")),
            Arguments.of("8", setOf("5", "7", "8", "0")),
            Arguments.of("9", setOf("6", "8", "9")),
            Arguments.of("0", setOf("0", "8")),
            Arguments.of("00", setOf("00", "08", "80", "88")),
            Arguments.of("09", setOf("06", "08", "09", "86", "88", "89")),
            Arguments.of("", setOf("")),
            Arguments.of("090", setOf("088, 880, 068, 860, 090, 898, 080, 888, 060, 868, 098, 890")),
            Arguments.of(
                "0900",
                setOf("8880, 8980, 8600, 8688, 0808, 0908, 0680, 8680, 0600, 0688, 8808, 8908, 0880, 0980, 0608, 8800, 8888, 8900, 8988, 8608, 0800, 0888, 0900, 0988")
            )
        )

        @JvmStatic
        fun invalidParams(): Stream<Arguments> = Stream.of(
            Arguments.of("   "),
            Arguments.of("A"),
            Arguments.of("1A"),
            Arguments.of("👎🏻")
        )
    }
}