import java.io.File
import java.nio.charset.Charset

val input = File("./3/input")
    .readText(Charset.forName("UTF8"))
    .split("\n")
    .filter { it.isNotBlank() }

val mod = input[0].length
fun get(x: Int, y: Int) = input[y][x.rem(mod)]

println((1..input.lastIndex).map { get(3 * it, it) }.filter { it == '#'}.size)

val slopes = listOf(
    1 to 1,
    3 to 1,
    5 to 1,
    7 to 1,
    1 to 2
)

println(slopes.map { (xS, yS) ->
  (1..(input.lastIndex / yS)).map { get( it * xS, it * yS) }.filter { it == '#'}.size.toLong()
}.reduce { acc, it -> acc * it })