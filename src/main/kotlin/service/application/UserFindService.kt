package main.kotlin.service.application

import main.kotlin.domain.value.UserId
import main.kotlin.domain.value.UserName
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.domain.UserService
import main.kotlin.service.presentation.dto.UserData
import main.kotlin.service.repository.IUserRepository

internal class UserFindService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService =
        UserService(userRepository)

    fun get(userId: UserId): UserData? {
        return userRepository.find(userId)?.let { user -> UserData.of(user) }
    }

    fun get(userName: UserName): UserData? {
        return userRepository.findByName(userName)?.let { user -> UserData.of(user) }
    }
}