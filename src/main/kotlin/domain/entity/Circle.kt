package main.kotlin.domain.entity

import main.kotlin.domain.value.circle.CircleId
import main.kotlin.domain.value.circle.CircleName
import main.kotlin.infrastracture.CircleRepository
import main.kotlin.service.domain.CircleService
import main.kotlin.service.repository.ICircleRepository

class Circle(val circleId: CircleId?, val circleName: CircleName, val members: CircleMembers) {

    private val circleRepository: ICircleRepository = CircleRepository()

    private val circleService: CircleService = CircleService(circleRepository)

    companion object {
        fun of(owner: User, circleName: CircleName): Circle {
            return Circle(null, circleName, CircleMembers.empty(null, owner))
        }

        fun of(circleId: CircleId, circleName: CircleName, members: CircleMembers): Circle {
            return Circle(circleId, circleName, members)
        }
    }

    fun owner(): User {
        return members.owner
    }

    fun addUser(user: User): Circle {
        val newMembers = members.addUser(user)
        return Circle(circleId, circleName, newMembers)
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