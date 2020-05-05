package service.application

import domain.entity.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import service.application.command.UserAppCommand

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