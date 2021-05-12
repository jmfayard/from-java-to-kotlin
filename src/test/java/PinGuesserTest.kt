import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pin.PinGuesser
import java.util.stream.Stream

internal class PinGuesserTest {
    var pinGuesser = PinGuesser()
    @ParameterizedTest
    @MethodSource("testSingleDigitParameters")
    fun testSingleDigit(observedPin: String?, expected: Set<String?>?) {
        val actual = pinGuesser.getPINs(observedPin!!)
        Assertions.assertEquals(expected, actual)
    }

    @ParameterizedTest
    @MethodSource("invalidParams")
    fun testInvalidInput(invalidInput: String?) {
        Assertions.assertThrows(RuntimeException::class.java) {
            pinGuesser.getPINs(
                invalidInput!!
            )
        }
    }

    fun testCombineSolutions() {
        val actual = pinGuesser.combineSolutions(java.util.Set.of("12", "34"), java.util.Set.of("8", "0"))
        val expected = java.util.Set.of("128", "120", "348", "340")
        Assertions.assertEquals(actual, expected)
    }

    companion object {
        private fun setOf(input: String): Set<String> {
            return java.util.Set.of(*input.split(", ".toRegex()).toTypedArray())
        }

        @JvmStatic fun testSingleDigitParameters(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("1", java.util.Set.of("1", "2", "4")),
                Arguments.of("2", java.util.Set.of("1", "2", "3", "5")),
                Arguments.of("3", java.util.Set.of("2", "3", "6")),
                Arguments.of("4", java.util.Set.of("1", "4", "5", "7")),
                Arguments.of("5", java.util.Set.of("2", "5", "4", "6", "8")),
                Arguments.of("6", java.util.Set.of("3", "5", "6", "9")),
                Arguments.of("7", java.util.Set.of("4", "7", "8")),
                Arguments.of("8", java.util.Set.of("5", "7", "8", "0")),
                Arguments.of("9", java.util.Set.of("6", "8", "9")),
                Arguments.of("0", java.util.Set.of("0", "8")),
                Arguments.of("00", java.util.Set.of("00", "08", "80", "88")),
                Arguments.of("09", java.util.Set.of("06", "08", "09", "86", "88", "89")),
                Arguments.of("", java.util.Set.of<Any>()),
                Arguments.of("090", setOf("088, 880, 068, 860, 090, 898, 080, 888, 060, 868, 098, 890")),
                Arguments.of(
                    "0900",
                    setOf("8880, 8980, 8600, 8688, 0808, 0908, 0680, 8680, 0600, 0688, 8808, 8908, 0880, 0980, 0608, 8800, 8888, 8900, 8988, 8608, 0800, 0888, 0900, 0988")
                )
            )
        }

        @JvmStatic  fun invalidParams(): Stream<Arguments> {
            return Stream.of(
                Arguments.of("   "),
                Arguments.of("A"),
                Arguments.of("1A"),
                Arguments.of("👎🏻")
            )
        }
    }
}