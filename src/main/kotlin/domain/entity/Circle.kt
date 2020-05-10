package main.kotlin.domain.entity

import main.kotlin.domain.value.circle.CircleId
import main.kotlin.domain.value.circle.CircleName
import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.CircleRepository
import main.kotlin.service.domain.CircleService
import main.kotlin.service.repository.ICircleRepository

class Circle(val circleId: CircleId?, val creatorId: UserId, val circleName: CircleName, val members: CircleMembers) {

    private val circleRepository: ICircleRepository = CircleRepository()

    private val circleService: CircleService = CircleService(circleRepository)

    companion object {
        fun of(userId: UserId, circleName: CircleName): Circle {
            return Circle(null, userId, circleName, CircleMembers.empty())
        }

        fun of(circleId: CircleId, userId: UserId, circleName: CircleName, members: CircleMembers): Circle {
            return Circle(circleId, userId, circleName, members)
        }
    }

    fun addUser(user: User): Circle {
        val newMembers = members.addUser(user)
        return Circle(circleId, creatorId, circleName, newMembers)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Circle

        if (circleId != other.circleId) return false

        return true
    }

    override fun hashCode(): Int {
        return circleId?.hashCode() ?: 0
    }
}