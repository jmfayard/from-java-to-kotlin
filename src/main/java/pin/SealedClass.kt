package pin

interface CounterOperation

enum class Options : CounterOperation {
    INCREMENT, DECREMENT
}
data class ResetCounter(val value: Int) : CounterOperation

var currentCounter = 0

fun handleOperation(operation: CounterOperation): Unit {
    when(operation) {
        Options.INCREMENT -> currentCounter++
        Options.DECREMENT -> currentCounter--
        is ResetCounter -> currentCounter = operation.value
        else ->  throw IllegalStateException("This should never happen")
    }
}