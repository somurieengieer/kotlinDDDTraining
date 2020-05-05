package service.application

import domain.value.UserId
import infrastracture.UserRepository
import service.application.command.UserAppCommand
import service.domain.UserService
import service.presentation.dto.UserData
import service.repository.IUserRepository

internal class UserUpdateService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService = UserService(userRepository)

    fun update(command: UserAppCommand): UserData {

        val user = userService.find(UserId(command.id))!!
            .let { command.name?.let { name -> it.changeName(name) } ?: it }
            .let { command.address?.let { address -> it.changeAddress(address) } ?: it }

        return userService.register(user).let { user -> UserData.of(user) }
    }
}