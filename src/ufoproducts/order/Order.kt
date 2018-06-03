package ufoproducts.order
import java.util.*

class Order constructor(val itemList: ArrayList<Item>, val time: Date) {
    override fun toString(): String {
        return "Order at $time"
    }
}