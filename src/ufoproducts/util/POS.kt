package ufoproducts.util

import ufoproducts.drink.*; import ufoproducts.food.*

class POS {
    /**
     * POS: Main class for POS utilities.
     * What would a POS utility be considered as? In this case,
     * it would mean methods that should be abstracted away from
     * the Main class. Stuff that would make the Main class look all
     * ugly. On a side note, isn't it nice how Kotlin and Java can
     * work together?
     */
    fun taxCalc(food: ArrayList<Food>, drink: ArrayList<Drink>): Double {
        /**
         * taxCalc: Returns the amout of sales tax applied to the order.
         * Totals up each list passed to it, based off the isTaxExempt flag.
         * Then, based on those totals, applies tax and returns a double
         * with the appropriate tax amount.
         */
        val TAX_RATE = 0.07 //Set to whatever your area's sales tax is
        var ret = 0.0
        //This first loop counts taxable items in the food list
        var Foodcount = 0
        for(x in 0 until food.size) {
            if(!food[x].isTaxExempt) {
                Foodcount++
            }
        }
        //and this one totals our drinks
        var Drinkcount = 0
        for(x in 0 until drink.size) {
            if(!drink[x].isTaxExempt) {
                Drinkcount++
            }
        }
        //Now, grab our full tax amount
        val total = Drinkcount + Foodcount
        ret = total * TAX_RATE
        return ret
    }
    fun orderTotal(food: ArrayList<Food>, drink: ArrayList<Drink>): Double {
        /**
         * orderTotal: called to total the current order. Takes the current items in
         * the order, and returns the final total. Calls the taxCalc function to grab
         * sales tax for the order.
         */
        var ret = 0.0
        for(x in 0 until food.size) {
            ret += food[x].price
        }
        for(x in 0 until drink.size) {
            ret += drink[x].price
        }
        ret += taxCalc(food, drink)
        return ret
    }
}