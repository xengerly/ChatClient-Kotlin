import java.util.Scanner
import kotlin.random.Random

enum class Players {
    X, O, E
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
        println("Ячейка занята")
    }

}

fun checkHorizontalWin(symbol: Players): Boolean {

    for ((i, players) in field.withIndex()) {
        var counter = 0

        for ((j, item) in field.withIndex()) {
            if (field[i][j] == symbol) counter++

            if (counter == field.size) {
                return true
            }

        }
    }
    return false
}

fun checkVerticalWin(playersSymbol: Players): Boolean {

    for ((i, players) in field.withIndex()) {
        var counter = 0
        for ((j, item) in field.withIndex()) {

            if (field[j][i] == playersSymbol) counter++

            if (counter == field.size) {
                return true
            }
        }
    }
    return false
}

fun checkDiagonalLeftWin(playersSymbol: Players): Boolean {
    var counter = 0


    for ((i, players) in field.withIndex()) {
        if (field[i][i] == playersSymbol) counter++

        if (counter == field.size) {
            return true
        }
    }
    return false
}

fun checkDiagonalRightWin(playersSymbol: Players,): Boolean {
    var counter = 0


    for ((i, players) in field.withIndex()) {
        if (field[field.size - 1 - i][i] == playersSymbol) counter++

        if (counter == field.size) {
            return true
        }
    }
    return false
}

fun winCondition(symbol: Players): Boolean {
    return checkHorizontalWin(symbol) ||
            checkVerticalWin(symbol) ||
            checkDiagonalLeftWin(symbol) ||
            checkDiagonalRightWin(symbol)
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
    var winSymbol = playersSymbol

    while (true) {
        userTurnAndCheckCell(playersSymbol)
        winCondition(playersSymbol)
        printField()
        if (winCondition(playersSymbol)){
            winSymbol = playersSymbol
            break
        }
        computerTurnAndCheckCell(computerSymbol)
        winCondition(computerSymbol)
        printField()
        if (winCondition(computerSymbol)){
            winSymbol = computerSymbol
            break
        }
    }

    println()
    //printField()

    println("Выйграл игрок $winSymbol")

    println("Спасибо за партию! ")
}