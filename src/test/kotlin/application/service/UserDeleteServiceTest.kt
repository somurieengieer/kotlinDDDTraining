package test.service.applications

import main.kotlin.domain.entity.User
import main.kotlin.service.application.command.UserAppCommand
import main.kotlin.service.application.user.UserDeleteService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserDeleteServiceTest : UserServiceCommonTest() {

    @Test
    fun delete_success() {
        val userName = "ユーザー名テスト"
        val user = User.create(userName)
        addDB(user)

        val userId = user.id.toString()

        val userDeleteService = UserDeleteService()

        assertEquals(getUserList().size, 1)

        userDeleteService.delete(
            UserAppCommand(
                userId
            )
        )

        assertEquals(getUserList().size, 0)
    }
}