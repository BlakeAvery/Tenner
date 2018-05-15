import java.io.*; import ufoproducts.util.Employee

/**
 * This class modifies employee lists. Employee lists are serialized Java objects,
 * in an ArrayList<Employee> format.
 */
object EmployeeModder: Serializable {
    @JvmStatic
    //TODO: Complete implementation
    fun main(args: Array<String>) {
        val file = File("employees.dat")
        if(file.exists()) {
            val list = ObjectInputStream(file.inputStream())

        }
    }
}