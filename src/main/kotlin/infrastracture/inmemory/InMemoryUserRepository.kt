package main.kotlin.infrastracture.inmemory

import main.kotlin.domain.entity.User
import main.kotlin.domain.value.user.UserAddress
import main.kotlin.domain.value.user.UserId
import main.kotlin.domain.value.user.UserName
import main.kotlin.service.repository.IUserRepository

internal class InMemoryUserRepository : IUserRepository {

    // DB代わりのMap（シングルトンでない場合、複数箇所でRepositoryが使われたら整合性は取れない）
    private val userMap: MutableMap<UserId, User> = mutableMapOf()

    override fun register(user: User): User {
        if (userMap.containsKey(user.id)) {
            userMap.remove(user.id)
        }
        userMap[user.id] = user
        return clone(user)
    }

    override fun find(userId: UserId): User? {
        // TODO: let以外の良い書き方ない？ map(this::clone)みたいな
        return userMap[userId]?.let { user -> clone(user) }
    }

    override fun findByName(userName: UserName): User? {
        return userMap.values.find { user -> user.name == userName }
    }

    override fun findByAddress(userAddress: UserAddress): User? {
        return userMap.values.find { user -> user.address == userAddress }
    }

    override fun findAll(): List<User> {
        return userMap.values.map { user -> clone(user) }
    }

    override fun delete(user: User) {
        userMap.remove(user.id)
    }

    private fun clone(user: User): User {
        return User(user.id, user.name)
    }
}