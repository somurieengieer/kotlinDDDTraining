package service.application

import domain.value.UserId
import domain.value.UserName
import infrastracture.UserRepository
import service.domain.UserService
import service.presentation.dto.UserData
import service.repository.IUserRepository

internal class UserFindService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService = UserService(userRepository)

    fun get(userId: UserId): UserData? {
        return userRepository.find(userId)?.let { user -> UserData.of(user) }
    }

    fun get(userName: UserName): UserData? {
        return userRepository.findByName(userName)?.let { user -> UserData.of(user) }
    }
}