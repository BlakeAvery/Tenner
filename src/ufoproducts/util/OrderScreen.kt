package ufoproducts.util

import ufoproducts.food.*; import ufoproducts.drink.*

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
        val foodList = ArrayList<Food>()
        val drinkList = ArrayList<Drink>()
        while(true) {
            print("type> ")
            var input = readLine() ?: "food"
            when(input) {
                "food" -> {
                    print("code> ")
                    var input = readLine()?.toInt() ?: 0
                    when(input) {
                        0 -> {
                            foodList.add(Burger())
                        }
                        else -> {

                        }
                    }
                }
                "drink" -> {
                    print("code> ")
                    var input = readLine()?.toInt() ?: 0
                    when(input) {
                        0 -> {
                            drinkList.add(Water())
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }
}