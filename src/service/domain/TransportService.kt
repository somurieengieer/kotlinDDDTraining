package service.domain

import domain.entity.Baggage
import domain.entity.PhysicalDistributionBase

internal class TransportService {

    fun transport(from: PhysicalDistributionBase, to: PhysicalDistributionBase, baggage: Baggage): Baggage {
        val shippedBaggage = from.ship(baggage)
        return to.receive(shippedBaggage)
    }

}