package main.kotlin.service.application.circle

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.circle.CircleName
import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.CircleRepository
import main.kotlin.service.application.circle.command.CircleCreateCommand
import main.kotlin.service.domain.CircleService
import main.kotlin.service.repository.ICircleRepository

class CircleRegisterService {

    private val circleRepository: ICircleRepository = CircleRepository()

    private val circleService = CircleService(circleRepository)

    fun register(command: CircleCreateCommand) {
        circleService.register(
            Circle.of(
                UserId(command.userId),
                CircleName(command.circleName)
            )
        )
    }

    fun addMember(circle: Circle, member: User) {
        circleService.addUser(circle, member)
    }
}