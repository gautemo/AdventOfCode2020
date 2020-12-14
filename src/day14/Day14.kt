package day14

import shared.getLines

fun initProgram(input: List<String>, isV2: Boolean = false): Long{
    val computer = if(isV2) ComputerV2() else ComputerV1()
    for(line in input){
        if(line.contains("mask")){
            val mask = line.split('=')[1].trim()
            computer.mask = mask
        }else{
            val adress = Regex("""\d+(?=\])""").find(line)!!.value.toLong()
            val number = line.split("=")[1].trim().toLong()
            computer.addValue(adress, number)
        }
    }
    return computer.values().sum()
}

interface Computer {
    var mask: String
    val memory: MutableMap<Long, Long>
    fun values() = memory.values
    fun addValue(adress: Long, input: Long)
}

class ComputerV1: Computer {
    override val memory = mutableMapOf<Long, Long>()

    override var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    override fun addValue(adress: Long, input: Long){
        val binary = input.toString(2).padStart(mask.length, '0').toCharArray()
        for(i in mask.indices){
            if(mask[i] != 'X'){
                binary[i] = mask[i]
            }
        }
        memory[adress] = binary.joinToString("").toLong(2)
    }

    override fun values() = memory.values
}

class ComputerV2: Computer {
    override val memory = mutableMapOf<Long, Long>()

    override var mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"

    override fun addValue(adress: Long, input: Long){
        val adressBinary = adress.toString(2).padStart(mask.length, '0').toCharArray()
        for(i in mask.indices){
            if(mask[i] != '0'){
                adressBinary[i] = mask[i]
            }
        }
        val adresses = combinationsForX(adressBinary)
        for(adr in adresses){
            memory[adr.joinToString("").toLong(2)] = input
        }
    }

    override fun values() = memory.values
}

fun combinationsForX(input: CharArray): List<CharArray>{
    val list = mutableListOf<CharArray>()
    val xIndex = input.indexOfFirst { it == 'X' }
    if(xIndex != -1){
        input[xIndex] = '1'
        list.addAll(combinationsForX(input.clone()))
        input[xIndex] = '0'
        list.addAll(combinationsForX(input.clone()))
    }else{
        list.add(input)
    }
    return list
}

fun main(){
    val input = getLines("day14.txt")
    val sum = initProgram(input)
    println(sum)
    val sum2 = initProgram(input, true)
    println(sum2)
}