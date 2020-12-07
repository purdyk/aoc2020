import java.io.File
import java.nio.charset.Charset

val input: List<List<String>> = File("./2/input")
    .readText(Charset.forName("UTF8"))
    .split("\n")
    .filter { it.isNotBlank() }
    .map { it.split(": ") }
    .map { it[0].split(" ").let { lead -> listOf(lead[0], lead[1], it[1]) } }

val valid = input.filter { (span, char, password) ->
  span.split("-").map { it.toInt() }.let { (min, max) ->
    (password as CharSequence)
        .filter { it == char[0] }.map { 1 }.sum()
        .let { size -> size >= min && size <= max }
  }
}

println(valid.size)

val valid2 = input.filter { (span, char, password) ->
  span.split("-").map { it.toInt() }.map { password[it - 1] }
      .filter { it == char[0] }.size == 1
}

println(valid2.size)