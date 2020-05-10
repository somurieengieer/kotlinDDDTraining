package main.kotlin.specification.circle

import main.kotlin.domain.entity.Circle
import main.kotlin.specification.ISpecification

class CircleRecommendSpecification : ISpecification<Circle> {

    override fun isSatisfiedBy(value: Circle): Boolean {
        if (value.members.countMembers() < 10) {
            return false
        }
        return true
    }
}