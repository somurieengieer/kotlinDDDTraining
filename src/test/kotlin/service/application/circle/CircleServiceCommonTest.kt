package test.service.applications

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.entity.CircleMembers
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.circle.CircleId
import main.kotlin.domain.value.circle.CircleName
import main.kotlin.infrastracture.postgres.circle.CircleDB
import main.kotlin.infrastracture.postgres.user.UserDB
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

    fun updateCircle(circle: Circle): Circle {
        circleDB.delete(circle)
        return addCircleDB(circle)
    }

    fun addUserDB(user: User): User {
        userDB.insert(user)
        return user
    }

    fun getCircleList(): List<Circle> {
        return circleDB.getAll()
    }

    fun createCircle(circleIdStr: String): Circle {
        val circleId = CircleId(circleIdStr)
        val owner = User.of("1111", "オーナー名", "address_value")
        val circleMembers = CircleMembers.of(circleId, owner, emptyList())
        val circleName = CircleName("サークル$circleId")
        val circle = Circle.of(circleId, circleName, circleMembers)
        circleDB.insert(circle)
        return circle
    }

    fun createUser(id: String): User {
        return User.of(id, "テストユーザー$id", "address$id")
            .also { addUserDB(it) }
    }
}