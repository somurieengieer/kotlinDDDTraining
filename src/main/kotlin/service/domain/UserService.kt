package main.kotlin.service.domain

import main.kotlin.domain.entity.User
import main.kotlin.domain.value.UserAddress
import main.kotlin.domain.value.UserId
import main.kotlin.domain.value.UserName
import main.kotlin.service.repository.IUserRepository

internal class UserService(private val userRepository: IUserRepository) {

    fun register(user: User): User {
        return userRepository.register(user)
    }

    fun exists(userId: UserId): Boolean {
        return userRepository.find(userId) != null
    }

    fun exists(userName: UserName): Boolean {
        return userRepository.findByName(userName) != null
    }

    private fun exists(userAddress: UserAddress): Boolean {
        return userRepository.findByAddress(userAddress) != null
    }

    fun exists(user: User): Boolean {

        userRepository.findByName(user.name)?.let { u -> u.id != user.id }?.takeIf { return true }
        user.address?.let { address ->
            userRepository.findByAddress(user.address)?.let { u -> u.id != user.id }?.takeIf { return true }
        }

        return false
    }

    fun find(userId: UserId): User? {
        return userRepository.find(userId)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun delete(user: User) {
        userRepository.delete(user)
    }
}