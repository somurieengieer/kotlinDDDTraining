package main.kotlin.domain.entity

internal class PhysicalDistributionBase(val baseName: String) {

    fun ship(baggage: Baggage): Baggage {
        println("ship: ${baggage.baggageName} from $baseName")
        return baggage
    }

    fun receive(baggage: Baggage): Baggage {
        println("receive: ${baggage.baggageName} at $baseName")
        return baggage
    }
}