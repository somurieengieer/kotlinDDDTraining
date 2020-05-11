package main.kotlin.specification.circle

import main.kotlin.domain.entity.CircleMembers
import main.kotlin.infrastracture.postgres.user.UserRepository
import main.kotlin.service.repository.IUserRepository
import main.kotlin.specification.ISpecification

class CircleFullSpecification : ISpecification<CircleMembers> {

    private val userRepository: IUserRepository =
        UserRepository()

    override fun isSatisfiedBy(circleMembers: CircleMembers): Boolean {
        return circleMembers.countMembers() < circleUpperLimit(circleMembers)
    }

    fun circleUpperLimit(circleMembers: CircleMembers): Int {
        if (circleMembers.premiumMemberSum() > 10) {
            return 50
        }
        return 30
    }
}