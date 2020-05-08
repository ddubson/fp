package fp.option

/**
 * Value types
 * Private constructors
 * Identity functions
 * fold operations
 */

class Traditional {
    data class Product(val name: String, val price: Double, val weight: Double)

    data class OrderLine(val product: Product, val count: Int) {
        fun weight() = product.weight * count
        fun amount() = product.price * count
    }

    object Store {
        @JvmStatic
        fun main(args: Array<String>) {
            val toothPaste = Product("Tooth paste", 1.5, 0.5)
            val toothBrush = Product("Tooth brush", 3.5, 0.5)

            val orderLines = listOf(OrderLine(toothPaste, 2), OrderLine(toothBrush, 3))

            // An issue arises here since weight and price can be misinterpreted, better to use "value types"
            val weight = orderLines.sumByDouble { it.amount() }
            val price = orderLines.sumByDouble { it.weight() }
            println("Total price: $price")
            println("Total weight: $weight")
        }
    }
}

class Enhanced {
    // Price value type
    data class Price private constructor(private val value: Double) {
        // override + operator specifically for price
        operator fun plus(price: Price) = Price(this.value + price.value)

        // override * operator specifically for price
        operator fun times(num: Int) = Price(this.value * num)

        override fun toString(): String = value.toString()

        companion object {
            val identity = Price(0.0)

            // 'invoke' is used to override '()' function invocation
            operator fun invoke(value: Double) =
                    if(value > 0) Price(value) else throw IllegalArgumentException("Price must be positive")
        }
    }

    // Weight value type
    data class Weight private constructor(private val value: Double) {
        operator fun plus(weight: Weight) = Weight(this.value + weight.value)

        operator fun times(num: Int) = Weight(this.value * num)

        companion object {
            val identity = Weight(0.0)

            // 'invoke' is used to override '()' function invocation
            operator fun invoke(value: Double) =
                    if(value > 0) Weight(value) else throw IllegalArgumentException("Price must be positive")
        }
    }

    data class Product(val name: String, val price: Price, val weight: Weight)

    data class OrderLine(val product: Product, val count: Int) {
        fun weight() = product.weight * count
        fun amount() = product.price * count
    }

    object Store {
        @JvmStatic
        fun main(args: Array<String>) {
            val toothPaste = Product("Tooth paste", Price(1.5), Weight(0.5))
            val toothBrush = Product("Tooth brush", Price(3.5), Weight(0.5))

            val orderLines = listOf(OrderLine(toothPaste, 2), OrderLine(toothBrush, 3))

            // Fold is a reduce operation with a starting value
            // The starting value is a neutral value usually referred to as identity
            val weight = orderLines.fold(Weight.identity) { a, b -> a + b.weight() }
            val price = orderLines.fold(Price.identity) { a, b -> a + b.amount() }
            println("Total price: $price")
            println("Total weight: $weight")
        }
    }
}

