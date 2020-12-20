package day19

import shared.getText
import shared.separateByBlankLine

fun validMessages(input: String): Int{
    val (rulesRead, messages) = separateByBlankLine(input).map { it.lines() }
    val rules = rulesRead.map { Rule(it) }

    val regex = toRegex(rules, "0")

    return messages.count {
        it.matches(Regex(regex))
    }
}

class Rule(ruleInput: String){
    val index: String
    val ruleset: String

    init{
        val (indexRead, rulesRead) = ruleInput.split(':')
        index = indexRead
        ruleset = "(" + rulesRead.trim().replace("\"", "") + ")"
    }
}

fun toRegex(rules: List<Rule>, check: String): String{
    val lookup = Regex("""\d+""").find(check) ?: return check.replace(" ", "")
    val rule = rules.find { it.index == lookup.value }!!
    return toRegex(rules, check.replaceRange(lookup.range, rule.ruleset))
}

fun validMessagesAltered(input: String): Int{
    val (rulesRead, messages) = separateByBlankLine(input).map { it.lines() }
    val rules = rulesRead.map { Rule(it) }

    val regex42 = toRegex(rules, "42")
    val regex31 = toRegex(rules, "31")

    val regex8 = "${regex42}+"
    var regex11 = ""
    repeat(5){
        regex11 += "(${regex42}{${it+1}}${regex31}{${it+1}})|"
    }
    regex11 = regex11.trimEnd('|')

    return messages.count {
        it.matches(Regex("($regex8)($regex11)"))
    }
}

fun main(){
    val input = getText("day19.txt")
    var validNr = validMessages(input)
    println(validNr)
    validNr = validMessagesAltered(input)
    println(validNr)
}
