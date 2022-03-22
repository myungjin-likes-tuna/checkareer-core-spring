package com.mlt.checkareercorespring.interceptor

import com.mlt.checkareercorespring.domain.auth.AuthInteractor
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CheckPermissionInterceptor(
    private val authInteractor: AuthInteractor
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        authInteractor.authenticate(request)
        return true
    }
}