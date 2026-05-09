package test.nikita

import kotlin.io.println


fun main() {

    val arrayHorizontal = listOf(
        listOf(0, 0, 0),
        listOf(1, 1, 1),
        listOf(0, 0, 0)
    )

    val arrayVertical = listOf(
        listOf(1, 0, 0),
        listOf(1, 0, 0),
        listOf(1, 0, 0)
    )

    val diagonalLeft = listOf(
        listOf(1, 0, 0),
        listOf(0, 1, 0),
        listOf(0, 0, 1)
    )

    val diagonalRight = listOf(
        listOf(0, 0, 1),
        listOf(0, 1, 0),
        listOf(1, 0, 0)
    )


    //println(horizontal(arrayHorizontal))
    // println(horizontal(arrayVertical))
    // println(horizontal(diagonalLeft))
    // println(horizontal(diagonalRight))

//println(vertical(arrayHorizontal))
//println(vertical(arrayVertical))
//println(vertical(diagonalRight))
//println(vertical(diagonalLeft))

//println(diagonalLeft(arrayHorizontal))
//println(diagonalLeft(arrayVertical))
//println(diagonalLeft(diagonalLeft))
//println(diagonalLeft(diagonalRight))

    // println(diagonalRight(arrayHorizontal))
    // println(diagonalRight(arrayVertical))
    // println(diagonalRight(diagonalLeft))
    //println(diagonalRight(diagonalRight))


}

fun horizontal(array: List<List<Int>>): Boolean {
    for (line in array.indices) {
        var counter = 0
        for (column in array.indices) {
            val result = array[line][column]

            if (result == 1) {
                counter++
            }

            if (counter == array.size) {
                return true
            }
        }
    }

    return false
}

fun vertical(array: List<List<Int>>): Boolean {
    for (line in array.indices) {
        var counter = 0
        for (column in array.indices) {
            val result = array[column][line]

            if (result == 1) {
                counter++
            }

            if (counter == 3) {
                return true
            }

        }
    }
    return false
}


fun diagonalLeft(array: List<List<Int>>): Boolean {
    var counter = 0
    for (i in array.indices) {
        val result = array[i][i]

        if (result == 1) {
            counter++
        }

        if (counter == array.size) {
            return true
        }
    }
    return false
}

fun diagonalRight(array: List<List<Int>>): Boolean {
    var counter = 0
    for (i in array.indices) {
        val result = array[array.size - 1 - i][i]

        if (result == 1) {
            counter++
        }

        if (counter == array.size) {
            return true
        }
    }
    return false
}








