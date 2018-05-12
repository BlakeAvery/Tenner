package ufoproducts.util

import  ufoproducts.order.*

class POS {
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
        val TAX_RATE = 0.07 //Set to whatever your area's sales tax is
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
}