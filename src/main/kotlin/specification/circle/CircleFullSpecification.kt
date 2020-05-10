package main.kotlin.specification.circle

import main.kotlin.domain.entity.Circle
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.repository.IUserRepository

class CircleFullSpecification {

    private val userRepository: IUserRepository = UserRepository()

    fun isSatisfiedBy(circle: Circle): Boolean {
        return premiumMemberSum(circle) + 1 < circleUpperLimit(circle)
    }

    fun circleUpperLimit(circle: Circle): Int {
        if (premiumMemberSum(circle) > 10) {
            return 50
        }
        return 30
    }

    private fun premiumMemberSum(circle: Circle): Int {
        return circle.members
            .map { user -> userRepository.find(user.id)!! }
            .count { user -> user.isPremium }
    }
}