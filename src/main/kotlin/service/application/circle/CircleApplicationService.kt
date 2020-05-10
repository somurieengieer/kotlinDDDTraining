package main.kotlin.service.application.circle

import main.kotlin.domain.entity.Circle
import main.kotlin.infrastracture.CircleRepository
import main.kotlin.service.domain.CircleService
import main.kotlin.service.repository.ICircleRepository

class CircleApplicationService {

    private val circleRepository: ICircleRepository = CircleRepository()

    val circleService = CircleService(circleRepository)

    fun getRecommend(): List<Circle> {
        return circleService.findRecommend()
    }
}