package ufoproducts.order

class Item constructor(var price: Double, var isTaxExempt: Boolean = false) {
    override fun toString(): String {
        return "[$price]"
    }
}