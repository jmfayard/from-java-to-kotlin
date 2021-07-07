package pin

fun fizzbuzz(i: Int): String {
    if (i.multipleOf(15)) {
        return "FizzBuzz"
    } else if (i.multipleOf(5)) {
        return "Buzz"
    } else if (i.multipleOf(3)) {
        return "Fizz"
    } else {
        return i.toString()
    }
}

fun Int.multipleOf(n: Int) =
    this.rem(n) == 0