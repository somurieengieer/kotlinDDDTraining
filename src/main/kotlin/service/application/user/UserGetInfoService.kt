package main.kotlin.service.application.user

import main.kotlin.domain.value.user.UserId
import main.kotlin.domain.value.user.UserName
import main.kotlin.infrastracture.postgres.user.UserRepository
import main.kotlin.service.domain.UserService
import main.kotlin.service.presentation.dto.UserData
import main.kotlin.service.repository.IUserRepository

internal class UserGetInfoService {

    private val userRepository: IUserRepository =
        UserRepository()

    private val userService: UserService =
        UserService(userRepository)

    fun get(userId: UserId): UserData? {
        return userRepository.find(userId)?.let { user -> UserData.of(user) }
    }

    fun get(userName: UserName): UserData? {
        return userRepository.findByName(userName)?.let { user -> UserData.of(user) }
    }

    fun getAllUsers(): List<UserData> {
        return userRepository.findAll().map { user -> UserData.of(user) }
    }
}