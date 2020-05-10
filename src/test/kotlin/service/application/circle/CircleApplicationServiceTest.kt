package service.application.circle

import main.kotlin.service.application.circle.CircleApplicationService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import test.service.applications.CircleServiceCommonTest

internal class CircleApplicationServiceTest : CircleServiceCommonTest() {

    @Test
    fun recommend() {
        createCircle("111")
        var circle2 = createCircle("222")

        for (i in 1..10) {
            val user = createUser("user$i")
            circle2 = circle2.addUser(user)
        }
        circle2 = updateCircle(circle2)

        val service = CircleApplicationService()
        val circles = service.getRecommend()

        assertEquals(circles.size, 1)
        assertEquals(circles[0], circle2)
    }
}