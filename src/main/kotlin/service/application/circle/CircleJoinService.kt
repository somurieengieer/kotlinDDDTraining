package main.kotlin.service.application.circle

import main.kotlin.domain.value.circle.CircleId
import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.CircleRepository
import main.kotlin.infrastracture.UserRepository
import main.kotlin.service.application.circle.command.CircleJoinCommand
import main.kotlin.service.domain.CircleService
import main.kotlin.service.domain.UserService
import main.kotlin.service.repository.ICircleRepository

class CircleJoinService {

    private val circleRepository: ICircleRepository = CircleRepository()

    private val circleService: CircleService = CircleService(circleRepository)

    private val userRepository: UserRepository = UserRepository()

    private val userService: UserService = UserService(userRepository)

    fun join(command: CircleJoinCommand) {
        circleService.addUser(
            circleService.find(CircleId(command.circleId))!!,
            userService.find(UserId(command.userId))!! // ユーザーは存在する前提とする
        )
    }
}