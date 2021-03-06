package ufoproducts.util

import ufoproducts.order.*
import java.util.*; import java.io.*; import java.net.*;

class OrderScreen constructor(private var logged: Employee, private val pos: POS, private val list: ArrayList<Employee>, private val posLog: File) {
    private val SysProp = SystemConstants()
    private val transactionLog = File("logs${SysProp.SLASH}transactions.txt")
    private val orderList = ArrayList<Order>()
    fun start() {
        println("Tenner: Order mode")
        println("Employee logged: $logged")
        posLog.appendText("Tenner in order mode.\nEmployee is $logged at ${Date()}.\n")
        while(true) {
            print("action> ")
            var input = readLine() ?: "order"
            when(input) {
                "order" -> newOrder()
                "exit" -> {
                    posLog.appendText("Order mode exited by $logged at ${Date()}.\n")
                    return
                }
                "chuser" -> changeUser()
                "refund" -> refund()
                else -> println("Invalid command.")
            }
        }
    }
    private fun changeUser() {
        print("login: ")
        val log = readLine()?.toInt() ?: 0
        for(x in 0 until list.size) {
            if(list[x].id == log) {
                logged = list[x]
                println("Employee logged: $logged")
                posLog.appendText("Logged employee changed to $logged at ${Date()}.\n")
                break
            }
        }
    }
    private fun refund() {
        println("Select order to refund:")
        for(x in orderList.indices) {
            println("$x: ${orderList[x]}")
        }
        print("choice> ")
        val choice = readLine()?.toInt() ?: throw OutOfMemoryError("Excuse me")
        for(x in orderList[choice].itemList.indices) {
            println(orderList[choice].itemList[x])
        }
        print("Refund this order? ")
        val y = readLine()?.get(0) ?: 'y'
        when(y) {
            'y' -> {
                val refund = pos.orderTotal(orderList[choice].itemList)
                transactionLog.appendText("Refund at ${Date()}:\n")
                posLog.appendText("[Refund at ${Date()}]\n")
                for(x in orderList[choice].itemList.indices) {
                    transactionLog.appendText("${orderList[choice].itemList[x]}\n")
                    posLog.appendText("${orderList[choice].itemList[x]}\n")
                }
                println("Refund total: $refund")
                transactionLog.appendText("Refund: $refund\n")
                posLog.appendText("Refund: $refund\n")
                transactionLog.appendText("End refund at ${Date()}.\n\n")
                posLog.appendText("[End refund at ${Date()}]\n")
                orderList.removeAt(choice)
            }
            else -> {
                println("Refund cancelled.")
            }
        }
    }
    private fun newOrder() {
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
                    val int = scan.nextInt()
                    if(int == 0) h = true
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
        val date = Date()
        println("Purchase at $date:")
        transactionLog.appendText("Purchase at $date:\n")
        posLog.appendText("[New transaction at $date]\n")
        for(x in 0 until order.size) {
            var logcat = "${x + 1}: ${order[x]}\n"
            print("$logcat")
            transactionLog.appendText(logcat)
            posLog.appendText(logcat)
        }
        //TODO: FIX FLOATING POINT ERRORS HERE(example: with tax rate of 0.7, 6.49 should be 6.95 not 6.94)
        val subTotal = String.format("%.2f", pos.orderSubTotal(order)).toDouble()
        val tax = String.format("%.2f", pos.taxCalc(order)).toDouble()
        val total = String.format("%.2f", pos.orderTotal(order)).toDouble()
        println("Subtotal: $subTotal")
        transactionLog.appendText("Subtotal: $subTotal\n")
        posLog.appendText("Subtotal: $subTotal\n")
        println("Sales tax: $tax")
        transactionLog.appendText("Sales tax: $tax\n")
        posLog.appendText("Sales tax: $tax\n")
        println("Total: $total")
        transactionLog.appendText("Total: $total\n")
        posLog.appendText("Total: $total\n")
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
        transactionLog.appendText("Cash: $cash\nChange: $change\n\n")
        posLog.appendText("Cash: $cash\nChange: $change\n[End transaction at ${Date()}]\n")
        orderList.add(Order(order, Date()))
    }
}