package main.kotlin.service.domain

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.circle.CircleId
import main.kotlin.service.repository.ICircleRepository
import main.kotlin.specification.circle.CircleFullSpecification

class CircleService(private val circleRepository: ICircleRepository) {

    private val circleFullSpecification = CircleFullSpecification()

    fun register(circle: Circle): Circle {
        return circleRepository.create(circle)
    }

    fun find(circleId: CircleId): Circle? {
        return circleRepository.find(circleId)
    }

    fun addUser(circle: Circle, user: User) {

        if (!circleFullSpecification.isSatisfiedBy(circle)) {
            val upperLimit = circleFullSpecification.circleUpperLimit(circle)
            throw AssertionError("サークルに参加できる人数は${upperLimit}人以下です")
        }

        circleRepository.update(
            circle.addUser(user)
        )
    }
}