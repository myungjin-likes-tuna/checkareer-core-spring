package com.mlt.checkareercorespring.infra.auth

import com.mlt.checkareercorespring.domain.auth.AuthInteractor
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class GoogleOAuthInteractor : AuthInteractor {

    override fun authenticate(request: HttpServletRequest) {
        // TODO: request header의 access token 검증 기능 추가
        println(request)
    }
}