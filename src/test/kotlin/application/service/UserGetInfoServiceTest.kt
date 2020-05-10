package test.service.applications

import main.kotlin.domain.entity.User
import main.kotlin.domain.value.user.UserName
import main.kotlin.service.application.user.UserGetInfoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class UserGetInfoServiceTest : UserServiceCommonTest() {

    @Test
    fun get_success() {
        val userName = "ユーザー名テスト"
        val user = User.create(userName)
        addDB(user)

        val userFindService = UserGetInfoService()

        val gottenUser = userFindService.get(UserName(userName))
        assertNotNull(gottenUser)
        assertEquals(gottenUser?.id, user.id.value())
        assertEquals(gottenUser?.name, user.name.value())
    }
}