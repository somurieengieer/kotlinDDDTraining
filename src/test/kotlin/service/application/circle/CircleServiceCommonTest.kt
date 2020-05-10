package test.service.applications

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.entity.CircleMembers
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.circle.CircleId
import main.kotlin.domain.value.circle.CircleName
import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.CircleDB
import main.kotlin.infrastracture.UserDB
import org.junit.jupiter.api.BeforeEach

internal open class CircleServiceCommonTest {

    val circleDB = CircleDB
    val userDB = UserDB

    @BeforeEach
    fun before() {
        clearDB()
    }

    fun clearDB() {
        circleDB.clear()
        userDB.clear()
    }

    fun addCircleDB(circle: Circle): Circle {
        circleDB.insert(circle)
        return circle
    }

    fun addUserDB(user: User): User {
        userDB.insert(user)
        return user
    }

    fun getCircleList(): List<Circle> {
        return circleDB.getAll()
    }

    fun createCircle(): Circle {
        val circleId = CircleId("circle1")
        val creatorId = UserId("1111")
        val circleName = CircleName("サークル名")
        val circle = Circle.of(circleId, creatorId, circleName, CircleMembers.empty())
        circleDB.insert(circle)
        return circle
    }
}