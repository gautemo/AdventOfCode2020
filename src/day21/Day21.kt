package day21

import shared.getLines

fun nonAllergensCount(input: List<String>): Int{
    val foods = toAllergenInIngredient(input)
    val allergens = mutableSetOf<String>()
    val ingredients = mutableSetOf<String>()
    foods.forEach {
        allergens.addAll(it.allergens)
        ingredients.addAll(it.ingredients)
    }
    val ingredientsWithAllergen = mutableListOf<String>()
    for(allergen in allergens){
        val canContain = foods.filter { it.allergens.contains(allergen) }.map { it.ingredients }.reduce { acc, list -> (acc intersect list).toList() }
        ingredientsWithAllergen.addAll(canContain)
    }
    val ingredientsAllergenFree = ingredients subtract ingredientsWithAllergen
    return ingredientsAllergenFree.sumBy { ingredient -> foods.count { food -> food.ingredients.contains(ingredient) } }
}

fun toAllergenInIngredient(input: List<String>): List<Food>{
    return input.map { line ->
        val allergensSection = Regex("""\(contains.+\)""").find(line)
        val allergens = allergensSection!!.value.replace("(contains ", "").replace(")", "").split(',').map { it.trim() }
        val ingredients = line.replace(Regex("""\(contains.+\)"""), "").split(" ").filter { it.isNotBlank() }
        Food(ingredients, allergens)
    }
}

class Food(val ingredients: List<String>, val allergens: List<String>)

fun main(){
    val input = getLines("day21.txt")
    val nonAllergenIngredientsCount = nonAllergensCount(input)
    println(nonAllergenIngredientsCount)
    val dangerous = dangerousList(input)
    println(dangerous)
}

fun dangerousList(input: List<String>): String{
    val foods = toAllergenInIngredient(input)
    val allergens = mutableSetOf<String>()
    val ingredients = mutableSetOf<String>()
    foods.forEach {
        allergens.addAll(it.allergens)
        ingredients.addAll(it.ingredients)
    }
    val allergenMap = mutableMapOf<String, Set<String>>()
    for(allergen in allergens){
        val canContain = foods.filter { it.allergens.contains(allergen) }.map { it.ingredients }.reduce { acc, list -> (acc intersect list).toList() }
        allergenMap[allergen] = canContain.toSet()
    }
    while (allergenMap.any { it.value.size > 1 }){
        for(allergen in allergenMap){
            if(allergen.value.size == 1){
                for(other in allergenMap.filter { it.key != allergen.key }){
                    allergenMap[other.key] = other.value subtract allergen.value
                }
            }
        }
    }
    return allergenMap.toList().sortedBy { it.first }.joinToString(","){ it.second.first() }
}