package main.kotlin.service.domain

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.circle.CircleId
import main.kotlin.service.repository.ICircleRepository
import main.kotlin.specification.circle.CircleFullSpecification
import main.kotlin.specification.circle.CircleRecommendSpecification

class CircleService(private val circleRepository: ICircleRepository) {

    private val circleFullSpecification = CircleFullSpecification()

    fun register(circle: Circle): Circle {
        return circleRepository.create(circle)
    }

    fun find(circleId: CircleId): Circle? {
        return circleRepository.find(circleId)
    }

    fun findRecommend(): List<Circle> {
        return circleRepository.find(
            listOf(CircleRecommendSpecification())
        )
    }

    fun addUser(circle: Circle, user: User) {

        if (!circleFullSpecification.isSatisfiedBy(circle.members)) {
            val upperLimit = circleFullSpecification.circleUpperLimit(circle.members)
            throw AssertionError("サークルに参加できる人数は${upperLimit}人以下です")
        }

        circleRepository.update(
            circle.addUser(user)
        )
    }
}