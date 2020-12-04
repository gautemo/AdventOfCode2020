package day4

import shared.getText

fun validPassports(input: String, validate: Boolean = false): Int{
    val regex = Regex("""^\s*${'$'}""", RegexOption.MULTILINE)
    val passports = input.split(regex)
    return passports.count { if(validate) hasValidRequiredFields(it) else hasRequiredFields(it) }
}

fun hasRequiredFields(passport: String): Boolean{
    val requiredFields = listOf(
            "byr",
            "iyr",
            "eyr",
            "hgt",
            "hcl",
            "ecl",
            "pid"
    )
    return requiredFields.all { passport.contains(it) }
}

fun hasValidRequiredFields(passport: String): Boolean{
    val end = """(\s|${'$'})"""
    val byr = passport.contains(Regex("""byr:((19[2-9]\d)|(200[0-2]))${end}"""))
    val iyr = passport.contains(Regex("""iyr:20((1\d)|20)${end}"""))
    val eyr = passport.contains(Regex("""eyr:20((2\d)|30)${end}"""))
    val hgt = passport.contains(Regex("""hgt:((1[5-8]\d|19[0-3])cm|(59|6\d|7[0-6])in)${end}"""))
    val hcl = passport.contains(Regex("""hcl:#([0-9]|[a-f]){6}${end}"""))
    val ecl = passport.contains(Regex("""ecl:(amb|blu|brn|gry|grn|hzl|oth)${end}"""))
    val pid = passport.contains(Regex("""pid:(\d){9}${end}"""))
    return byr && iyr && eyr && hgt && hcl && ecl && pid
}

fun main(){
    val input = getText("day4.txt")
    val fieldsPresent = validPassports(input)
    println(fieldsPresent)
    val fieldsPresentAndValid = validPassports(input, true)
    println(fieldsPresentAndValid)
}