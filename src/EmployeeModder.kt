import java.io.*; import ufoproducts.util.*; import java.util.*

/**
 * This class modifies employee lists. Employee lists are serialized Java objects,
 * in an ArrayList<Employee> format.
 */

object EmployeeModder {
    private val log = File("logs${System.getProperty("file.separator")}admin.txt")
    private fun editList(list: ArrayList<Employee>): ArrayList<Employee> {
        for(x in 0 until list.size) {
            println("Employee $x: ${list[x]}")
        }
        println("Edit? [y, n]")
        var thing = readLine()?.get(0) ?: 'y'
        when(thing) {
            'y' -> {
                log.appendText("Entering edit mode at ${Date()}.\n")
                oh@while(true) {
                    print("Enter operation [r for remove, a for add]: ")
                    thing = readLine()?.get(0) ?: 'a'
                    when(thing) {
                        'r' -> {
                            print("Enter index of employee to drop: ")
                            val x = readLine()?.toInt() ?: throw IllegalArgumentException()
                            log.appendText("Employee $x: ${list[x]} removed at ${Date()}.\n")
                            list.removeAt(x)
                            for(x in 0 until list.size) {
                                println("Employee $x: ${list[x]}")
                            }
                        }
                        'a' -> {
                            print("Enter new name: ")
                            var string = readLine() ?: throw IllegalArgumentException()
                            print("Enter employee ID: ")
                            var id = readLine()?.toInt() ?: throw IllegalArgumentException()
                            print("Is this employee a manager? ")
                            var isManagement = readLine()?.get(0) == 'y'
                            list.add(Employee(string, id, isManagement))
                            log.appendText("New employee $string added with ID $id. Manager status: $isManagement. ${Date()}.\n")
                        }
                        else -> {
                            println("Exiting.")
                            log.appendText("Exiting edit mode at ${Date()}.\n")
                            break@oh
                        }
                    }
                }
            }
            else -> return list
        }
        return list
    }
    @JvmStatic
    fun main(args: Array<String>) {
        log.appendText("Employee Modder started at ${Date()}\n")
        val filename = if(args.isEmpty()) {
            "employees.dat"
        } else {
            args[0]
        }
        log.appendText("Using file $filename.\n")
        val file = File(filename)
        if(file.exists()) {
            var list = POS().csvParse(filename)
            list = editList(list)
            POS().makeCSV(list, filename)
            log.appendText("Wrote new $filename at ${Date()}. Exiting.\n\n")
        } else {
            var list = editList(ArrayList<Employee>())
            POS().makeCSV(list, filename)
            log.appendText("Wrote new $filename at ${Date()}. Exiting.\n\n")
        }
    }
}