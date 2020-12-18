package day18

import shared.getLines

fun calculate(input: String): Long{
    var expression = input
    while(expression.contains(Regex("""\*|\+"""))){
        val first = Regex("""\(?\d+ (\*|\+) \d+\)?""").find(expression)
        val toCalculate = first!!.value.replace("(", "").replace(")", "")
        val toReplace = if(first.value.contains("(") && first.value.contains(")")) {
            first.value
        }else{
            first.value.replace("(", "").replace(")", "")
        }
        val result = if(toCalculate.contains('+')){
            val (f, l) = toCalculate.split('+').map { it.trim().toLong() }
            f + l
        }else{
            val (f, l) = toCalculate.split('*').map { it.trim().toLong()}
            f * l
        }
        expression = expression.replaceFirst(toReplace, result.toString())
    }
    return expression.toLong()
}

fun calculatePrecedence(input: String): Long{
    var expression = input
    while(expression.contains(Regex("""\*|\+"""))){
        val parentheses = Regex("""\([^()]*\)""").find(expression)
        if(parentheses != null){
            val result = calculatePrecedence(parentheses.value.trimStart('(').trimEnd(')'))
            expression = expression.replaceFirst(parentheses.value, result.toString())
            continue
        }
        val addition = Regex("""\d+ (\+) \d+""").find(expression)
        if(addition != null){
            val result = addition.value.split('+').map { it.trim().toLong() }.sum()
            expression = expression.replaceFirst(addition.value, result.toString())
            continue
        }
        val multiplication = Regex("""\d+ (\*) \d+""").find(expression)
        if(multiplication != null){
            val (a, b) = multiplication.value.split('*').map { it.trim().toLong() }
            val result = a * b
            expression = expression.replaceFirst(multiplication.value, result.toString())
            continue
        }
    }
    return expression.toLong()
}

fun sumOfCalculations(calculations: List<String>) = calculations.map { calculate(it) }.sum()

fun sumOfCalculationsPrecedence(calculations: List<String>) = calculations.map { calculatePrecedence(it) }.sum()

fun main(){
    val input = getLines("day18.txt")
    val sum = sumOfCalculations(input)
    println(sum)
    val sumPrecedence = sumOfCalculationsPrecedence(input)
    println(sumPrecedence)
}