package day7

import shared.getLines
import kotlin.math.max

fun nrCanContainGoldBag(bagRules: List<String>): Int {
    val bags = mapToBags(bagRules)
    return bags.count { containGold(it, bags) }
}

fun containGold(bag: Bag, bags: List<Bag>): Boolean{
    return bag.contain.any { inside ->
        inside.color == "shiny gold" || containGold(bags.find { it.color == inside.color }!!, bags)
    }
}

data class Bag(val color: String, val contain: List<BagInside>)

data class BagInside(val color: String, val nr: Int)

fun mapToBags(bagRules: List<String>): List<Bag>{
    return bagRules.map { rule ->
        val (bag, containRule) = rule.split("bags contain").map { it.trim() }
        val contain = containRule.split(",").map { it.trim() }.filter { it != "no other bags." }.map {
            val nr = it[0].toString().toInt()
            val color = it.substring(1).replace(Regex("bag(s)?(.)?\$"), "").trim()
            BagInside(color, nr)
        }
        Bag(bag, contain)
    }
}

fun bagsInsideGold(bagRules: List<String>): Int{
    val bags = mapToBags(bagRules)
    val gold = bags.find { it.color == "shiny gold" }!!
    return gold.contain.sumBy { countBag(it, bags) }
}

fun countBag(bag: BagInside, bags: List<Bag>): Int {
    return bags.find { it.color == bag.color }!!.contain.sumBy { countBag(it, bags) } * bag.nr + bag.nr
}

fun main(){
    val rules = getLines("day7.txt")
    val containsGold = nrCanContainGoldBag(rules)
    println(containsGold)
    val insideGold = bagsInsideGold(rules)
    println(insideGold)
}
