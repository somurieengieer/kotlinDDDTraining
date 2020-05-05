package service.application

import domain.value.UserId
import infrastracture.UserRepository
import service.application.command.UserAppCommand
import service.domain.UserService
import service.repository.IUserRepository

internal class UserDeleteService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService = UserService(userRepository)

    fun delete(command: UserAppCommand) {
        val user = userService.find(UserId(command.id))

        user?.let { userService.delete(user) }
    }
}