enum class Players {
    X,
    O,
    E
}

var fieldSize = 3
var field = MutableList(fieldSize) { MutableList<Players>(fieldSize) { Players.E } }


fun userInputFieldSize() {
    println("Если ввод будет некоректный  то размер поля будет по умолчанию")
    print("Введите размер поля(по умолчанию 3): ")
    fieldSize = readln().toIntOrNull() ?: 3

    field = MutableList(fieldSize) { MutableList<Players>(fieldSize) { Players.E } }
    field[2][1] = Players.O
}

fun printField() {
    field.forEach {
        it.forEach {
            print(" $it |")
        }
        println()
    }
}

fun userTurnAndCheckCell() {
    var size = 0

    print("Введите номер столбца: ")
    val col = readln().toInt()
    print("Введите номер ряда: ")
    val row = readln().toInt()


    if (field[col][row] == Players.O) {
        println("Ячейка занята, введите заново номер ячейки")
    } else {
        field[col][row] = Players.X
    }

}


fun horizontal() {
    var counter = 0

    for ((i, players) in field.withIndex()) {
        for ((j, item) in field.withIndex()) {

            if (field[i][j] == Players.X) counter++

            if (counter == field.size) {
                println("Победил игрок Х")
                break
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
    while (true) {
        //checkCell()
        userTurnAndCheckCell()
        printField()
        horizontal()
        counter++

        if (counter == field.size * field.size) return
    }

}