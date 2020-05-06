package main.kotlin.service.domain

import main.kotlin.domain.entity.Baggage
import main.kotlin.domain.entity.PhysicalDistributionBase

internal class TransportService {

    fun transport(from: PhysicalDistributionBase, to: PhysicalDistributionBase, baggage: Baggage): Baggage {
        val shippedBaggage = from.ship(baggage)
        return to.receive(shippedBaggage)
    }

}