import com.sun.org.apache.xpath.internal.operations.Bool
import java.io.File
import java.nio.charset.Charset

val input = File("./4/input")
    .readText(Charset.forName("UTF8"))
    .split("\n\n")
    .map {
      it.replace("\n", " ")
          .split(" ").map { it.split(":").let { it[0] to it[1] } }
          .toMap()
    }

val needed = listOf(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
// "cid"
)

println(input.filter { needed.all { k -> it.containsKey(k) } }.size)
val eyes = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
val hgt = mapOf(
    "in" to 59..76,
    "cm" to 150..193
)

val validators = listOf(
    "byr" to { y: String -> y.toInt() in 1910..2002 },
    "iyr" to { y: String -> y.toInt() in 2010..2020 },
    "eyr" to { y: String -> y.toInt() in 2020..2030 },
    "hgt" to { v: String -> v.dropLast(2).toIntOrNull() ?: 0 in hgt[v.takeLast(2)] ?: -2 .. -1 },
    "hcl" to { v: String -> v[0] == '#' && v.drop(1).let { it.length == 6 && it.all { it.isHexDigit() }}},
    "ecl" to { v: String -> v in eyes },
    "pid" to { v: String -> v.length == 9 && v.all { it.isDigit() }},
// "cid"
)

println(input.filter { validators.all { (k, validator) -> it[k]?.let { validator(it) } == true} }.size)

fun Char.isHexDigit() = isDigit() || toLowerCase() in 'a'..'f'