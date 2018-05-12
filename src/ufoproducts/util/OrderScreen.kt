package ufoproducts.util

import ufoproducts.order.*
import java.util.*

class OrderScreen constructor(val logged: Employee, val pos: POS) {
    fun start() {
        println("Tenner: Order mode")
        println("Employee logged: ${logged.name}")
        while(true) {
            print("action> ")
            var input = readLine() ?: "order"
            when(input) {
                "order" -> newOrder()
                "exit" -> return
                else -> println("Invalid command.")
            }
        }
    }
    fun newOrder() {
        val scan = Scanner(System.`in`)
        val order = ArrayList<Item>()
        while(true) {
            try {
                print("item> ")
                val p = scan.nextDouble()
                /**
                 * h is used if we find a 0 after the price, which will tell the
                 * POS to set this item as tax exempt.
                 */
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
            } catch(e: InputMismatchException) {
                break
            }
        }
        for(x in 0 until order.size) {
            println("${x + 1}: ${order[x]}")
        }
        val subTotal = pos.orderSubTotal(order)
        val tax = pos.taxCalc(order)
        val total = pos.orderTotal(order)
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
        var change = pos.changeCalc(total, cash)
        println("Change: $change")
    }
}