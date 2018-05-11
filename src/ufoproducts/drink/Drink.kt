package ufoproducts.drink

abstract class Drink constructor(n: String, t: Int, p: Double, iTX: Boolean) {
    val name: String
    val id: Int
    val price: Double
    val isTaxExempt: Boolean
    init {
        name = n
        id = t
        price = p
        isTaxExempt = iTX
    }
    override fun toString(): String {
        return "$name: $$price"
    }
}