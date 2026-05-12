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
    if (fieldSize < 2) fieldSize = 3

    field = MutableList(fieldSize) { MutableList<Players>(fieldSize) { Players.E } }
    field[0][2] = Players.X
    field[1][1] = Players.X
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

    print("Введите номер столбца: ")
    val col = readln().toInt()
    print("Введите номер ряда: ")
    val row = readln().toInt()

    if (field[col][row] == Players.E) {
        field[col][row] = Players.X
    } else {
        println("Ячейка занята, введите заново номер ячейки")
    }

}


fun checkHorizontalWin(): Boolean {

    for ((i, players) in field.withIndex()) {
        var counter = 0
        for ((j, item) in field.withIndex()) {

            if (field[i][j] == Players.X) counter++


            if (counter == field.size) {
                println("Победил игрок Х")
                return true
            }
        }
    }


    return false
}

fun checkVerticalWin(): Boolean {

    for ((i, players) in field.withIndex()) {
        var counter = 0
        for ((j, item) in field.withIndex()) {

            if (field[j][i] == Players.X) counter++


            if (counter == field.size) {
                println("Победил игрок Х")
                return true
            }
        }
    }
    return false
}

fun checkDiagonalLeftWin(): Boolean {
    var counter = 0

    for ((i, players) in field.withIndex()) {

        if (field[i][i] == Players.X) counter++

        if (counter == field.size) {
            println("Победил игрок Х")
            return true
        }
    }
    return false
}

fun checkDiagonalRightWin(): Boolean {
    var counter = 0

    for ((i, players) in field.withIndex()) {

        if (field[field.size - 1 - i][i] == Players.X) counter++

        if (counter == field.size) {
            println("Победил игрок Х")
            return true
        }
    }
    return false
}

fun winCondition(): Boolean {
    return checkHorizontalWin() || checkVerticalWin() || checkDiagonalLeftWin() || checkDiagonalRightWin()
}

fun main() {
    val movesCounts = field.size * field.size
    var counter = 0

    userInputFieldSize()
    printField()


    var isWin: Boolean = false

    while (!isWin) {
        userTurnAndCheckCell()
        printField()
        isWin = winCondition()
    }

}