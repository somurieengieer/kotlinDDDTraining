package main.kotlin.domain.entity

import main.kotlin.domain.value.circle.CircleId

class CircleMembers(private val circleId: CircleId?, val owner: User, val users: List<User>) {
    companion object {
        fun of(circleId: CircleId?, owner: User, users: List<User>): CircleMembers {
            return CircleMembers(circleId, owner, users)
        }

        fun empty(circleId: CircleId?, owner: User): CircleMembers {
            return CircleMembers(circleId, owner, emptyList())
        }
    }

    fun addUser(user: User): CircleMembers {
        return of(circleId, owner, listOf(users, listOf(user)).flatten())
    }

    fun countMembers(): Int {
        return users.size + 1
    }

    fun list(): List<User> {
        return users
    }

    fun premiumMemberSum(): Int {
        return users.count { user -> user.isPremium }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CircleMembers

        if (users != other.users) return false

        return true
    }

    override fun hashCode(): Int {
        return users.hashCode()
    }


}