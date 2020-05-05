package service.application

import domain.entity.User
import infrastracture.UserRepository
import service.domain.UserService
import service.presentation.dto.UserData
import service.repository.IUserRepository

internal class UserRegisterService {

    private val userRepository: IUserRepository = UserRepository()

    private val userService: UserService = UserService(userRepository)

    fun register(name: String, address: String? = null): UserData {

        val user = User.create(name, address)

        takeIf { userService.exists(user) }
            ?.let { throw AssertionError("ユーザー（$name）は既に登録済みです") }

        return UserData.of(userRepository.register(user))
    }
}