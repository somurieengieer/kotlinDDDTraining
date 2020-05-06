package main.kotlin.service.repository

import main.kotlin.domain.entity.User
import main.kotlin.domain.value.UserAddress
import main.kotlin.domain.value.UserId
import main.kotlin.domain.value.UserName

internal interface IUserRepository {

    fun register(user: User): User

    fun find(userId: UserId): User?

    fun findByName(userName: UserName): User?

    fun findByAddress(userAddress: UserAddress): User?

    fun findAll(): List<User>

    fun delete(user: User)
}