package test.service.applications

import main.kotlin.domain.entity.User
import main.kotlin.infrastracture.UserDB
import org.junit.jupiter.api.BeforeEach

internal open class UserServiceCommonTest {

    val userDB = UserDB

    @BeforeEach
    fun before() {
        clearDB()
    }

    fun clearDB() {
        userDB.clear()
    }

    fun addDB(user: User): User {
        userDB.insert(user)
        return user
    }

    fun getUserList(): List<User> {
        return userDB.getAll()
    }
}