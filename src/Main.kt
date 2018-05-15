import ufoproducts.util.*

import java.io.*
import java.net.*
import java.util.*
//This class generated by IntelliJ Java-to-Kotlin translator!
object Main {
    private val VER = 0.7f
    private val pos = POS()
    private val employees = ArrayList<Employee>()
    private val tennerLog = File("logs${System.getProperty("file.separator")}log.txt")
    private fun loginPrompt() {
        while(true) {
            print("login: ")
            try {
                val employee = readLine()?.toInt() ?: 0
                for (x in employees.indices) {
                    if (employees[x].id == employee) {
                        val oS = OrderScreen(employees[x], pos, employees, tennerLog)
                        oS.start()
                    }
                }
            } catch(e: NumberFormatException) {
                break
            }
        }
    }

    private fun tennerAbout() {
        println("Tenner Point-of-Sale")
        println("Version $VER")
        println("©UFO Products 2018")
        println("Tenner is licensed under the MIT License.")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        employees.add(Employee("Admin", 0, true))
        employees.add(Employee("Jared Simmons", 1, false))
        tennerLog.appendText("Started Tenner at ${Date()}.\n")
        println("Welcome to Tenner v$VER")
        while(true) {
            try {
                print("tenner> ")
                val input = readLine() ?: throw IOException("Error in input stream")
                when(input) {
                    "login" -> loginPrompt()
                    "info" -> tennerAbout()
                    "exit" -> {
                        println("Terminating Tenner.")
                        tennerLog.appendText("Exiting Tenner at ${Date()}.\n\n")
                        System.exit(0)
                    }
                    else -> println("Invalid command.")
                }
            } catch(e: Exception) {
                println("Terminating Tenner due to $e.")
                tennerLog.appendText("Exception in Tenner: $e. Exiting at ${Date()}.\n\n")
                System.exit(0)
            }
        }
    }
}