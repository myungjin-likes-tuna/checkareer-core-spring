package com.mlt.checkareercorespring.infra.auth

import com.mlt.checkareercorespring.domain.auth.AuthInteractor
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class GoogleOAuthInteractor : AuthInteractor {

    override fun authenticate(request: HttpServletRequest) {
        val cookies = request.cookies
        val accessToken = cookies.first { it.name == "ACCESS_TOKEN" }.value

        println(accessToken)
    }
}