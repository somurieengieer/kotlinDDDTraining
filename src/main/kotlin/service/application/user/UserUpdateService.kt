package main.kotlin.service.application.user

import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.application.command.UserAppCommand
import main.kotlin.service.domain.UserService
import main.kotlin.service.presentation.dto.UserData
import main.kotlin.service.repository.IUserRepository

internal class UserUpdateService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService =
        UserService(userRepository)

    fun update(command: UserAppCommand): UserData {

        val user = userService.find(UserId(command.id))!!
            .let { command.name?.let { name -> it.changeName(name) } ?: it }
            .let { command.address?.let { address -> it.changeAddress(address) } ?: it }

        return userService.register(user).let { user -> UserData.of(user) }
    }
}