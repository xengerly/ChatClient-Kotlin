import java.util.Scanner
import kotlin.random.Random

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

}

fun printField() {
    field.forEach {
        it.forEach {
            print(" $it |")
        }
        println()
    }
}


fun userTurnAndCheckCell(playersSymbol: Players) {
    println("Ход $playersSymbol")

    print("Введите номер столбца: ")
    val col = readln().toInt()
    print("Введите номер ряда: ")
    val row = readln().toInt()

    if (field[col][row] == Players.E) {
        field[col][row] = playersSymbol
    } else {
        println("Ячейка занята, введите заново номер ячейки")
    }

}

fun computerTurnAndCheckCell(computerSymbol: Players) {
    println("Ход $computerSymbol")

    val col = Random.nextInt(0, field.size)
    val row = Random.nextInt(0, field.size)

    if (field[col][row] == Players.E) {
        field[col][row] = computerSymbol
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
        var counterX = 0
        var counterO = 0
        for ((j, item) in field.withIndex()) {

            if (field[j][i] == Players.X) counterX++
            if (field[j][i] == Players.O) counterO++

            if (counterX == field.size) {
                println("Победил игрок Х")
                return true
            }
            if (counterO == field.size) {
                println("Победил игрок O")
                return true
            }
        }
    }
    return false
}

fun checkDiagonalLeftWin(): Boolean {
    var counterX = 0
    var counterO = 0

    for ((i, players) in field.withIndex()) {

        if (field[i][i] == Players.X) counterX++
        if (field[i][i] == Players.O) counterO++

        if (counterX == field.size) {
            println("Победил игрок Х")
            return true
        }
        if (counterO == field.size) {
            println("Победил игрок O")
            return true
        }
    }
    return false
}

fun checkDiagonalRightWin(): Boolean {
    var counterX = 0
    var counterO = 0

    for ((i, players) in field.withIndex()) {

        if (field[field.size - 1 - i][i] == Players.X) counterX++
        if (field[field.size - 1 - i][i] == Players.O) counterO++

        if (counterX == field.size) {
            println("Победил игрок Х")
            return true
        }
        if (counterO == field.size) {
            println("Победил игрок O")
            return true
        }
    }
    return false
}

fun winCondition(): Boolean {
    return checkHorizontalWin() || checkVerticalWin() || checkDiagonalLeftWin() || checkDiagonalRightWin()
}

fun main() {

    val scanner: Scanner = Scanner(System.`in`)
    print("Выберите за кого хотите играть X/O: ")
    val userInput = scanner.next().first()

    var playersSymbol = if (userInput == 'X') Players.X else Players.O
    var computerSymbol = if (playersSymbol == Players.X) Players.O else Players.X

    userInputFieldSize()
    printField()

    var isWin: Boolean = false

    while (!isWin) {
        userTurnAndCheckCell(playersSymbol)
        printField()
        computerTurnAndCheckCell(computerSymbol)
        printField()
        isWin = winCondition()
    }
    println("Спасибо за партию! ")
}