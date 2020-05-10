package main.kotlin.specification.circle

import main.kotlin.domain.entity.CircleMembers
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.repository.IUserRepository

class CircleFullSpecification {

    private val userRepository: IUserRepository = UserRepository()

    fun isSatisfiedBy(circleMembers: CircleMembers): Boolean {
        return circleMembers.size() + 1 < circleUpperLimit(circleMembers)
    }

    fun circleUpperLimit(circleMembers: CircleMembers): Int {
        if (circleMembers.premiumMemberSum() > 10) {
            return 50
        }
        return 30
    }

//    private fun premiumMemberSum(circle: Circle): Int {
//        return circle.members
//            .map { user -> userRepository.find(user.id)!! }
//            .count { user -> user.isPremium }
//    }
}