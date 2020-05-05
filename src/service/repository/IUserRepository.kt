package service.repository

import domain.entity.User
import domain.value.UserAddress
import domain.value.UserId
import domain.value.UserName

internal interface IUserRepository {

    fun register(user: User): User

    fun find(userId: UserId): User?

    fun findByName(userName: UserName): User?

    fun findByAddress(userAddress: UserAddress): User?

    fun findAll(): List<User>

    fun delete(user: User)
}