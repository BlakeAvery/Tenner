package ufoproducts.util

import ufoproducts.order.*
import java.util.*

class OrderScreen constructor(private var logged: Employee, private val pos: POS, private val list: ArrayList<Employee>) {
    fun start() {
        println("Tenner: Order mode")
        println("Employee logged: ${logged.name}")
        while(true) {
            print("action> ")
            var input = readLine() ?: "order"
            when(input) {
                "order" -> newOrder()
                "exit" -> return
                "chuser" -> changeUser()
                else -> println("Invalid command.")
            }
        }
    }
    fun changeUser() {
        print("login: ")
        val log = readLine()?.toInt() ?: 0
        for(x in 0 until list.size) {
            if(list[x].id == log) {
                logged = list[x]
                break
            }
        }
    }
    fun newOrder() {
        val scan = Scanner(System.`in`) //such a terrible idea :(
        val order = ArrayList<Item>()
        while(true) {
            try {
                print("item> ")
                val p = scan.nextDouble()
                /**
                 * h is used if we find a 0 after the price, which will tell the
                 * POS to set this item as tax exempt.
                 */
                //TODO: Implement better check than this for trailing 0
                var h: Boolean = false
                if(scan.hasNextInt()) {
                    if(scan.nextInt() == 0) {
                        h = true
                    }
                }
                if(h) {
                    order.add(Item(p, h))
                } else {
                    order.add(Item(p))
                }
            } catch(e: InputMismatchException) { //So I don't have to do any actual work in string parsing
                break
            }
        }
        for(x in 0 until order.size) {
            println("${x + 1}: ${order[x]}")
        }
        val subTotal = String.format("%.2f", pos.orderSubTotal(order)).toDouble()
        val tax = String.format("%.2f", pos.taxCalc(order)).toDouble()
        val total = String.format("%.2f", pos.orderTotal(order)).toDouble()
        //TODO: Figure out rounding for prices to nearest cent
        println("Subtotal: $subTotal")
        println("Sales tax: $tax")
        println("Total: $total")
        var cash = 0.0
        while(cash < total) {
            print("cash> ")
            cash = readLine()?.toDouble() ?: 0.0
            if(cash < total) {
                println("Not enough cash")
            }
        }
        var change = String.format("%.2f", pos.changeCalc(total, cash)).toDouble()
        println("Change: $change")
    }
}