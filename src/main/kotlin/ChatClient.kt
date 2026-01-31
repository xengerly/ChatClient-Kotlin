package test.nikita

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.sql.Time
import java.time.LocalTime
import java.util.Scanner
import java.time.Instant

class ChatClient {

    companion object {
        private const val SERVER_ADDRESS = "localhost"
        private const val SERVER_PORT = 12345

        @JvmStatic
        fun main(args: Array<String>) {
            ChatClient().start()
        }
    }

    private lateinit var socket: Socket
    private lateinit var input: BufferedReader
    private lateinit var output: PrintWriter
    private lateinit var scanner: Scanner
    var clientName: String = ""


    fun start() {
        try {
            connectToServer()

            setupUserName()

            startThread()

        } catch (e: IOException) {
            println("Ошибка подключения ${e.message}")
        }
    }


    private fun connectToServer() {
        println("Подключаемся к серверу $SERVER_ADDRESS : $SERVER_PORT ...")
        socket = Socket(SERVER_ADDRESS, SERVER_PORT)
        input = BufferedReader(InputStreamReader(socket.getInputStream()))
        output = PrintWriter(socket.getOutputStream(), true)
        scanner = Scanner(System.`in`)

        println("Подключение успешно!")
    }

    private fun setupUserName() {

        print("Введите ваше имя : ")
        clientName = scanner.nextLine()
        output.println("Имя клиента :$clientName")
        println("Добро пожаловать, $clientName !")
        println("=".repeat(30 - 1))


    }

    private fun startThread() {
        var readThread = Thread(ReadThread())
        readThread.isDaemon = true
        readThread.start()


        val writeMessages = ReadThread()
        writeMessages.writeMessages()
    }


    private inner class ReadThread : Runnable {
        override fun run() {
            try {
                var message: String = ""
                while (true) {
                    val message = input?.readLine() ?: break
                    println(message)
                }
            } catch (e: IOException) {
                println("Соединение с сервером разорвано")
            } finally {
                closeConnection()
            }
        }


        fun writeMessages() {
            try {
                while (true) {

                    val scanner = Scanner(System.`in`)
                    val userInput = scanner.nextLine()
                    print("Вы :")

                    if ("/exit".equals(userInput.trim(), ignoreCase = true)) {
                        output.println("/exit")
                        break
                    }

                    if (!userInput.trim().isEmpty()) {
                        output.println(clientName + " :" + userInput)
                    }
                }
            } finally {
                closeConnection()
            }
        }

        private fun closeConnection() {
            try {
                if (scanner != null) scanner.close()
                if (input != null) input.close()
                if (output != null) output.close()
                if (socket != null && !socket.isClosed) {
                    socket.close()
                }
                println("Клиент завершает работу...")
            } catch (e: IOException) {
                println("Ошибка при закрытии соединения : ${e.message}")
            }
        }
    }
}
