import ufoproducts.util.*

import java.io.*
import java.util.*
import java.net.*
//This class generated by IntelliJ Java-to-Kotlin translator!
object Main {
    private val VER = 0.9f
    private var pos = POS()
    private var employees = ArrayList<Employee>()
    private val SysProp = SystemConstants()
    private val tennerLog = File("logs${SysProp.SLASH}log.txt")
    private var server = Socket()
    init {
        println("Starting Tenner $VER")
    }
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
        println("Tenner is licensed under the ISC License.")
    }

    private fun setupWizard() {
        var ip: String
        var port: Int
        println("Hi! Looks like it's your first time using Tenner.")
        println("First, we need you to give this Tenner install an ID.")
        println("I recommend you make the ID a sequential number, depending on how many Tenner\n" +
                "installs you have running in your store.")
        val id = readLine()?.toInt() ?: 0
        println("Alright, now we need your store server IP address.")
        while(true) {
            print("IP> ")
            ip = readLine() ?: "127.0.0.1"
            if(pos.IPCheck(ip)) {
                println("OK, now the port that the server is running on.")
                print("port> ")
                port = readLine()?.toInt() ?: 2222 //2222 might as well be default tenner port
                println("Now, we'll test the connection with the Tenner server.")
                Thread.sleep(1000)
                server = Socket(ip, port)
                server.soTimeout = 20000
                val hi = DataOutputStream(server.getOutputStream())
                val bye = DataInputStream(server.getInputStream())
                hi.writeUTF("TEST $id Tenner$VER\n")
                val ret = bye.readUTF() //wait for server ret code
                println(ret)
                val retcode = ret.substring(0, 3)
                if(retcode.toInt() == 200) {
                    println("Looks like it works. We just need one last thing! Sales tax rate for your state.")
                    break
                }
            } else {
                println("Looks like you've entered an invalid address. Try again.")
            }
        }
        val salesTax =
    }
    @JvmStatic
    fun main(args: Array<String>) {
        pos = if(args.isEmpty()) {
            POS()
        } else if(args.size == 1) {
            try {
                POS(args[0].toDouble())
            } catch(e: NumberFormatException) {
                POS()
            }
        } else {
            try {
                POS(args[1].toDouble())
            } catch(e: NumberFormatException) {
                POS(args[0].toDouble())
            }
        }
        val config = File("config.txt")
        if(!config.exists()) {
            setupWizard()
        }
        val filename = try {
            if(args.isEmpty() || args[0].toDouble() == pos.TAX_RATE) {
                "employees.dat"
            } else {
                args[0]
            }
        } catch(e: NumberFormatException) {
            args[0]
        }
        employees = pos.csvParse(filename)
        tennerLog.appendText("Started Tenner v$VER at ${Date()}.\n")
        tennerLog.appendText("Using employee list at $filename.\n")
        tennerLog.appendText("Running on ${SysProp.OS} ${SysProp.VER} ${SysProp.ARCH}.\n")
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
                    "rmlog" -> {
                        print("Enter ID of manager: ")
                        val man = readLine()?.toInt() ?: 0
                        for (x in employees.indices) {
                            if (employees[x].id == man && employees[x].isManager) {
                                val logback = File("logs${SysProp.SLASH}log.backup")
                                tennerLog.renameTo(logback)
                                tennerLog.appendText("Log deleted by ${employees[x].name} on ${Date()}\n")
                                println("Old log removed. You can find the backup at logs${SysProp.SLASH}log.backup.")
                            }
                        }
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
