package main.kotlin.domain.entity

class CircleMembers(val users: List<User>) {
    companion object {
        fun of(users: List<User>): CircleMembers {
            return CircleMembers(users)
        }

        fun empty(): CircleMembers {
            return CircleMembers(emptyList())
        }
    }

    fun addUser(user: User): CircleMembers {
        return of(listOf(users, listOf(user)).flatten())
    }

    fun size(): Int {
        return users.size
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