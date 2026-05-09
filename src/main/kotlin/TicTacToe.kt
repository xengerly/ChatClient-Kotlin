enum class Players {
    X,
    O,
    E
}


var fieldSize = 3
var field = MutableList(fieldSize) { MutableList<Players>(fieldSize) { Players.E } }


fun userInputFieldSize() {
    print("Введите размер поля(по умолчанию 3): ")
    fieldSize = readln().toIntOrNull() ?: 3

    field = MutableList(fieldSize) { MutableList<Players>(fieldSize) { Players.E } }
}

fun userTurn(){
    var size = 0

        print("Введите номер столбца: ")
        val col = readln().toInt()
        print("Введите номер ряда: ")
        val row = readln().toInt()

        field[col][row] = Players.X


}


fun printField() {
    field.forEach {
        it.forEach {
            print(" $it |")
        }
        println()
    }
}

fun horizontal() {
    var counter = 0

    for ((i, players) in field.withIndex()) {
        for ((j, item) in field.withIndex()) {

            if (field[i][j] == Players.X) counter++

            if (counter == field.size) {
                println("Победил игрок Х")
                return
            }
        }
    }
}

fun vertical() {
    var counter = 0

    for ((i, players) in field.withIndex()) {
        for ((j, item) in field.withIndex()) {

            if (field[j][i] == Players.X) counter++

            if (counter == field.size) {
                println("Победил игрок Х")
                return
            }
        }
    }
}

fun diagonalLeft() {
    var counter = 0

    for ((i, players) in field.withIndex()) {

        if (field[i][i] == Players.X) counter++

        if (counter == field.size) {
            println("Победил игрок Х")
            return
        }
    }
}

fun diagonalRight() {
    var counter = 0

    for ((i, players) in field.withIndex()) {

        if (field[field.size - 1 - i][i] == Players.X) counter++

        if (counter == field.size) {
            println("Победил игрок Х")
            return
        }
    }


}

fun main() {
    userInputFieldSize()
    printField()

    var counter = 0
    while (true){
        userTurn()
        printField()
        counter++

        if (counter == field.size * field.size) return
    }

}