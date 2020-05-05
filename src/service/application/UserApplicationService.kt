package service.application

import domain.entity.User
import domain.value.UserId
import domain.value.UserName
import infrastracture.UserRepository
import service.application.command.UserAppCommand
import service.domain.UserService
import service.presentation.dto.UserData
import service.repository.IUserRepository

internal class UserApplicationService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService = UserService(userRepository)

    fun register(name: String, address: String? = null): UserData {

        val user = User.create(name, address)

        takeIf { userService.exists(user) }
            ?.let { throw AssertionError("ユーザー（$name）は既に登録済みです") }

        return UserData.of(userRepository.register(user))
    }

    fun get(userId: UserId): UserData? {
        return userRepository.find(userId)?.let { user -> UserData.of(user) }
    }

    fun get(userName: UserName): UserData? {
        return userRepository.findByName(userName)?.let { user -> UserData.of(user) }
    }

    fun update(command: UserAppCommand): UserData {

        val user = userService.find(UserId(command.id))!!
            .let { command.name?.let { name -> it.changeName(name) } ?: it }
            .let { command.address?.let { address -> it.changeAddress(address) } ?: it }

        return userService.register(user).let { user -> UserData.of(user) }
    }

    fun delete(command: UserAppCommand) {
        val user = userService.find(UserId(command.id))

        user?.let { userService.delete(user) }
    }
}