package main.kotlin.domain.value

internal class Money(val currency: String, val value: Long) {

    fun add(added: Money): Money {
        takeUnless { currency == added.currency }?.apply { throw AssertionError("should add money with same currency") }

        return Money(currency, value + added.value)
    }

    override fun toString(): String {
        return "$value $currency"
    }
}
