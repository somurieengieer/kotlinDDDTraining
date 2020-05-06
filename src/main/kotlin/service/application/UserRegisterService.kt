package main.kotlin.service.application

import main.kotlin.domain.entity.User
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.domain.UserService
import main.kotlin.service.presentation.dto.UserData
import main.kotlin.service.repository.IUserRepository

internal class UserRegisterService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService =
        UserService(userRepository)

    fun register(name: String, address: String? = null): UserData {

        val user = User.create(name, address)

        takeIf { userService.exists(user) }
            ?.let { throw AssertionError("ユーザー（$name）は既に登録済みです") }

        return UserData.of(userRepository.register(user))
    }
}