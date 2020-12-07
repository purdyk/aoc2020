import java.io.File
import java.nio.charset.Charset

val input = File("./1/input")
    .readText(Charset.forName("UTF8"))
    .split("\n")
    .filter { it.isNotBlank() }
    .map { it.toInt() }

val (a, b) = input.dropLast(1).asSequence().mapIndexedNotNull { aI, a ->
  input.subList(aI + 1, input.lastIndex).firstOrNull { b -> a + b == 2020 }?.let { a to it }
}.first()

println("${a*b}")


val (d, e, f) = input.subList(0, input.lastIndex - 2).asSequence().mapIndexedNotNull { aI, a ->
  input.subList(aI + 1, input.lastIndex - 1).asSequence().mapIndexedNotNull { bI, b ->
    input.subList(bI + 1, input.lastIndex).asSequence().firstOrNull { c -> a + b + c == 2020 }?.let { listOf(a, b, it) }
  }.firstOrNull()
}.first()

println("${d * e * f}")