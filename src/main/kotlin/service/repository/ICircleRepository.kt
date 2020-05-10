package main.kotlin.service.repository

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.value.circle.CircleId
import main.kotlin.specification.ISpecification

interface ICircleRepository {

    fun create(circle: Circle): Circle

    fun update(circle: Circle): Circle

    fun find(circleId: CircleId): Circle?

    fun find(specs: List<ISpecification<Circle>>): List<Circle>

}