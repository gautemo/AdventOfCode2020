package day16

import shared.getLines

enum class ReadMode{
    RULES, MY_TICKET, NEARBY_TICKETS
}

fun ticketInvalid(input: List<String>): Int{
    val sections = NotesSections(input)

    val invalids = mutableListOf<Int>()
    for(ticket in sections.otherTickets){
        invalids.addAll(ticket.split(',').map { it.toInt() }.filter { field -> !sections.rules.any { rule -> rule.valid(field) } })
    }
    return invalids.sum()
}

class NotesSections(input: List<String>){
    val rules = mutableListOf<Rule>()
    lateinit var myTicket: String
    val otherTickets = mutableListOf<String>()

    init {
        var readmode = ReadMode.RULES
        for(line in input){
            when{
                line.isBlank() -> continue
                line == "your ticket:" -> readmode = ReadMode.MY_TICKET
                line == "nearby tickets:" -> readmode = ReadMode.NEARBY_TICKETS
                readmode == ReadMode.MY_TICKET -> myTicket = line
                readmode == ReadMode.NEARBY_TICKETS -> otherTickets.add(line)
                else -> rules.add(Rule(line))
            }
        }
    }
}

class Rule(line: String){
    val name: String
    private val ranges: List<Range>
    init {
        val colonSplit = line.split(':').map { it.trim() }
        val rules = colonSplit[1].split("or").map {
            val (min, max) = it.trim().split('-').map { number -> number.toInt() }
            Range(min, max)
        }
        ranges = rules
        name = colonSplit[0]
    }

    fun valid(field: Int):Boolean{
        for(range in ranges){
            if(field >= range.min && field <= range.max) return true
        }
        return false
    }
}

data class Range(val min: Int, val max: Int)

fun departure(input: List<String>): Long{
    val sections = NotesSections(input)

    val possibleRules = mutableMapOf<Int, List<Rule>>()
    val myFields = sections.myTicket.split(',').map { it.toInt() }
    val validTickets = sections.otherTickets.filter { ticket ->
        ticket.split(',').map { it.toInt() }.all { field -> sections.rules.any { rule -> rule.valid(field) } }
    }
    for(i in myFields.indices){
        val fieldsForOthers = validTickets.map { ticket -> ticket.split(',').map { it.toInt() }[i] }
        val matchRules = sections.rules.filter { rule -> fieldsForOthers.all { rule.valid(it) } }
        possibleRules[i] = matchRules
    }

    while(possibleRules.any { it.value.size != 1 }){
        possibleRules.forEach { (index, rules) ->
            if(rules.size == 1){
                for(i in myFields.indices){
                    if(index != i){
                        possibleRules[i] = possibleRules[i]!!.filter { it.name != rules[0].name }
                    }
                }
            }
        }
    }

    var departure = 1L

    possibleRules.forEach { (index, rules) ->
        if(rules[0].name.startsWith("departure")){
            departure *= myFields[index]
        }
    }

    return departure
}

fun main(){
    val input = getLines("day16.txt")
    val errorRate = ticketInvalid(input)
    println(errorRate)
    val departure = departure(input)
    println(departure)
}