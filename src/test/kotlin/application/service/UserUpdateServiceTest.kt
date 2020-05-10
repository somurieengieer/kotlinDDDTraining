package test.service.applications

import main.kotlin.domain.entity.User
import main.kotlin.service.application.command.UserAppCommand
import main.kotlin.service.application.user.UserUpdateService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class UserUpdateServiceTest : UserServiceCommonTest() {

    @Test
    fun update_address_success() {

        val userName = "ユーザー名テスト"
        val user = User.create(userName)
        addDB(user)

        val userUpdateService = UserUpdateService()

        val address = "ユーザー住所"
        val updatedUserData = userUpdateService.update(
            UserAppCommand(
                user.id.value(),
                address = address
            )
        )
        assertEquals(address, updatedUserData.address)
        assertEquals(user.id.value(), updatedUserData.id)
        assertEquals(user.name.value(), updatedUserData.name)
    }

    @Test
    fun update_name_success() {

        val userName = "ユーザー名テスト"
        val user = User.create(userName)
        addDB(user)

        val userUpdateService = UserUpdateService()

        val updatedUserName = "ユーザー名テストUpdate"
        val updatedUserData = userUpdateService.update(
            UserAppCommand(
                user.id.value(),
                name = updatedUserName
            )
        )
        assertNull(updatedUserData.address)
        assertEquals(user.id.value(), updatedUserData.id)
        assertEquals(updatedUserName, updatedUserData.name)
    }
}