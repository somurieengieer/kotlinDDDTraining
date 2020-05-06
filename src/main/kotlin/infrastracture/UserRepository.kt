package main.kotlin.infrastracture

import main.kotlin.domain.entity.User
import main.kotlin.domain.value.UserAddress
import main.kotlin.domain.value.UserId
import main.kotlin.domain.value.UserName
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

    private val userDB: UserDB = UserDB

    override fun register(user: User): User {
        if (userDB.getAll().contains(user)) {
            this.delete(user)
        }
        userDB.insert(user)
        return user
    }

    override fun find(userId: UserId): User? {
        return userDB.getAll().find { user -> user.id == userId }
    }

    override fun findByName(userName: UserName): User? {
        return userDB.getAll().find { user -> user.name == userName }
    }

    override fun findByAddress(userAddress: UserAddress): User? {
        return userDB.getAll().find { user -> user.address == userAddress }
    }

    override fun findAll(): List<User> {
        return userDB.getAll()
    }

    override fun delete(user: User) {
        userDB.delete(user)
    }
}