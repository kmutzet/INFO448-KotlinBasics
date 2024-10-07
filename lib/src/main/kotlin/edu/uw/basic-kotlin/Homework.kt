package edu.uw.basickotlin

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg: Any): String {  
    when (arg) {
        is String -> {
            when (arg) {
                "Hello" -> return "world"
                else -> return "Say what?"
            }
        }
        is Int -> {
            when (arg) {
                0 -> return "zero"
                1 -> return "one"
                in 2..10 -> return "low number"
                else -> return "a number"
            }
        }
        else -> return "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(lhs: Int, rhs: Int): Int = lhs + rhs

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(lhs: Int, rhs: Int): Int = lhs - rhs

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(lhs: Int, rhs: Int, op: (Int, Int) -> Int) = op(lhs, rhs)

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:${this.firstName} lastName:${this.lastName} age:${this.age}]"
}

// write a class "Money"
class Money(val amount: Int, val currency: String)
{
    init {
        if (currency !in listOf("USD", "GBP", "CAN", "EUR")) {
            throw IllegalArgumentException("Invalid currency")
        }
        if (amount < 0) {
            throw IllegalArgumentException("Ammount cannot be negative")
        }
    }

    private fun getConversionRate(from: String, to: String): Double {
        return when (from to to) {
            "USD" to "EUR" -> 1.5
            "USD" to "CAN" -> 1.25
            "USD" to "GBP" -> 0.5

            "EUR" to "USD" -> 0.6667
            "EUR" to "GBP" -> 0.3333
            "EUR" to "CAN" -> 1.6667

            "CAN" to "EUR" -> 0.6
            "CAN" to "GBP" -> 0.4
            "CAN" to "USD" -> 0.8

            "GBP" to "USD" -> 2.0
            "GBP" to "EUR" -> 3.0
            "GBP" to "CAN" -> 2.5

            else -> 1.0
        }
    }

    fun convert(toCurrency: String): Money {
        if (currency == toCurrency) return this
        val conversionRate = getConversionRate(currency, toCurrency)
        val convertedAmount = ((amount * conversionRate)+0.5).toInt()
        return Money(convertedAmount, toCurrency)
    }

    operator fun plus(other: Money): Money {
        if (this.currency != other.currency)
            return this.plus(other.convert(this.currency))
        return Money(this.amount + other.amount, this.currency)
    }
}
