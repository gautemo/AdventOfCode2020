package shared

fun separateByBlankLine(input: String): List<String>{
    val regex = Regex("""^\s*${'$'}""", RegexOption.MULTILINE)
    return input.split(regex).map { it.trim() }
}