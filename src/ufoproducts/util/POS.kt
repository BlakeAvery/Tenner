package ufoproducts.util

import  ufoproducts.order.*
import java.io.File

class POS constructor(val TAX_RATE: Double = 0.07) {
    /**
     * POS: Main class for POS utilities.
     * What would a POS utility be considered as? In this case,
     * it would mean methods that should be abstracted away from
     * the Main class. Stuff that would make the Main class look all
     * ugly. On a side note, isn't it nice how Kotlin and Java can
     * work together?
     */
    fun taxCalc(transaction: ArrayList<Item>): Double {
        /**
         * taxCalc: Returns the amout of sales tax applied to the order.
         * Totals up each list passed to it, based off the isTaxExempt flag.
         * Then, based on those totals, applies tax and returns a double
         * with the appropriate tax amount.
         */
        //This first loop counts taxable items
        var count = 0.0
        for(x in 0 until transaction.size) {
            if(!transaction[x].isTaxExempt) {
                count += transaction[x].price
            }
        }
        //Now, grab our full tax amount
        return count * TAX_RATE
    }
    fun orderTotal(transaction: ArrayList<Item>): Double {
        /**
         * orderTotal: called to total the current order. Takes the current items in
         * the order, and returns the final total. Calls the taxCalc function to grab
         * sales tax for the order.
         */
        var ret = 0.0
        for(x in 0 until transaction.size) {
            ret += transaction[x].price
        }
        ret += taxCalc(transaction)
        return ret
    }
    fun orderSubTotal(transaction: ArrayList<Item>): Double {
        /**
         * orderSubTotal: Returns the subtotal of the order, without tax factored
         * in.
         */
        var ret = 0.0
        for(x in 0 until transaction.size) {
            ret += transaction[x].price
        }
        return ret
    }
    fun changeCalc(total: Double, cash: Double): Double {
        return cash - total
    }
    fun csvParse(fileLocation: String): ArrayList<Employee> {
        /**
         * csvParse: Takes a file in CSV format, and returns an ArrayList of Employees.
         */
        val employees = File(fileLocation)
        val list = ArrayList<Employee>()
        val eachLine = employees.readLines()
        for(x in eachLine.indices) {
            val line = eachLine[x].split(",")
            //!!SCARY LINE OF CODE INCOMING!!
            list.add(Employee(line[0], line[1].toInt(), line[2].toBoolean()))
        }
        return list
    }
    fun makeCSV(employees: ArrayList<Employee>, fileLocation: String) {
        /**
         * makeCSV: Takes the list of employees, and then writes the list to a file
         * in CSV format.
         */
        val file = File(fileLocation)
        /*!!SCARY INIT TEXT MEME INCOMING!!
          also clobbers the file, because appending initially is boring
         */
        file.writeText("${employees[0].name},${employees[0].id},${employees[0].isManager}\n")
        for(x in 1 until employees.size) {
            file.appendText("${employees[x].name},${employees[x].id},${employees[x].isManager}\n")
        }
    }
}