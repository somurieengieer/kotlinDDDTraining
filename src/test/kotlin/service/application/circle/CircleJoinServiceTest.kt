package service.application.circle

import main.kotlin.domain.entity.User
import main.kotlin.service.application.circle.CircleJoinService
import main.kotlin.service.application.circle.command.CircleJoinCommand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import test.service.applications.CircleServiceCommonTest

internal class CircleJoinServiceTest : CircleServiceCommonTest() {

    @Test
    fun join() {
        val circle = createCircle("111")

        val addUser = User.of("2222", "名前１")
        userDB.insert(addUser)

        val service = CircleJoinService()
        service.join(CircleJoinCommand(circle.circleId!!.value(), addUser.id.value()))

        val userList = circleDB.getCircle(circle.circleId!!)!!.members

        assertEquals(userList.countMembers(), 2)
        assertEquals(userList.list()[0], addUser)
    }

    @Test
    fun join_failed() {
        val circle = createCircle("111")

        val service = CircleJoinService()

        try {
            for (i in 1..50) {
                val addUser = User.of("user$i", "名前$i")
                userDB.insert(addUser)

                service.join(CircleJoinCommand(circle.circleId!!.value(), addUser.id.value()))
            }
            kotlin.test.fail()
        } catch (e: AssertionError) {
            assertEquals(e.message, "サークルに参加できる人数は50人以下です")
        }

        // 代表を除くと49人
        assertEquals(circleDB.getCircle(circle.circleId!!)!!.members.countMembers(), 50)
    }
}