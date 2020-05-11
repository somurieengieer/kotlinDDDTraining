package main.kotlin.service.application.circle

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.circle.CircleName
import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.postgres.circle.CircleRepository
import main.kotlin.infrastracture.postgres.user.UserRepository
import main.kotlin.service.application.circle.command.CircleCreateCommand
import main.kotlin.service.domain.CircleService
import main.kotlin.service.domain.UserService
import main.kotlin.service.repository.ICircleRepository
import main.kotlin.service.repository.IUserRepository

class CircleRegisterService {

    private val circleRepository: ICircleRepository =
        CircleRepository()

    private val circleService = CircleService(circleRepository)

    private val userRepository: IUserRepository =
        UserRepository()

    private val userService = UserService(userRepository)

    fun register(command: CircleCreateCommand) {

        val owner = userService.find(UserId(command.userId))
            ?: throw AssertionError("ユーザーが存在しません")

        circleService.register(
            Circle.of(
                owner,
                CircleName(command.circleName)
            )
        )
    }

    fun addMember(circle: Circle, member: User) {
        circleService.addUser(circle, member)
    }
}