package service.application

import domain.entity.User
import domain.value.UserName
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class UserFindServiceTest : UserServiceCommonTest() {

    @Test
    fun get_success() {
        val userName = "ユーザー名テスト"
        val user = User.create(userName)
        addDB(user)

        val userFindService = UserFindService()

        val gottenUser = userFindService.get(UserName(userName))
        assertNotNull(gottenUser)
        assertEquals(gottenUser?.id, user.id.value())
        assertEquals(gottenUser?.name, user.name.value())
    }
}