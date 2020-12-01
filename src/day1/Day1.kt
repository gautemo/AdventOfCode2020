package day1

import shared.getLinesAsInt

fun main(){
    val expenses = getLinesAsInt("day1.txt")
    println(findEntriesOf2020AndMultiply(expenses))
    println(findThreeEntriesOf2020AndMultiply(expenses))
}

fun findEntriesOf2020AndMultiply(expenses: List<Int>): Int {
    for((i,expense1) in expenses.withIndex()){
        for((j,expense2) in expenses.withIndex()){
            if(i != j && expense1 + expense2 == 2020){
                return expense1 * expense2
            }
        }
    }
    return 0
}

fun findThreeEntriesOf2020AndMultiply(expenses: List<Int>): Int {
    for((i,expense1) in expenses.withIndex()){
        for((j,expense2) in expenses.withIndex()){
            for((k,expense3) in expenses.withIndex()) {
                if(i != j && j != k && expense1 + expense2 + expense3 == 2020){
                    return expense1 * expense2 * expense3
                }
            }
        }
    }
    return 0
}