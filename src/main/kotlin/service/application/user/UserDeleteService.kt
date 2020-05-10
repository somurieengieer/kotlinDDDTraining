package main.kotlin.service.application.user

import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.application.command.UserAppCommand
import main.kotlin.service.domain.UserService
import main.kotlin.service.repository.IUserRepository

internal class UserDeleteService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService =
        UserService(userRepository)

    fun delete(command: UserAppCommand) {
        val user = userService.find(UserId(command.id))

        user?.let { userService.delete(user) }
    }
}