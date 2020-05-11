package main.kotlin.infrastracture.postgres.user

import main.kotlin.domain.entity.User
import main.kotlin.domain.value.user.UserAddress
import main.kotlin.domain.value.user.UserId
import main.kotlin.domain.value.user.UserName
import main.kotlin.service.repository.IUserRepository

// DBの代わり
internal object UserDB {

    private var list: MutableList<User> = mutableListOf()

    fun getAll(): List<User> {
        return list
    }

    fun insert(user: User) {
        list.add(user)
    }

    fun delete(user: User) {
        list.remove(user)
    }

    fun clear() {
        list = mutableListOf()
    }
}

internal class UserRepository : IUserRepository {

    private val userDB: UserDB =
        UserDB

    override fun register(user: User): User {
        if (UserDB.getAll().contains(user)) {
            this.delete(user)
        }
        UserDB.insert(user)
        return user
    }

    override fun find(userId: UserId): User? {
        return UserDB.getAll().find { user -> user.id == userId }
    }

    override fun findByName(userName: UserName): User? {
        return UserDB.getAll().find { user -> user.name == userName }
    }

    override fun findByAddress(userAddress: UserAddress): User? {
        return UserDB.getAll().find { user -> user.address == userAddress }
    }

    override fun findAll(): List<User> {
        return UserDB.getAll()
    }

    override fun delete(user: User) {
        UserDB.delete(user)
    }
}