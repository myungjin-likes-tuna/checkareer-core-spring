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
        try {
            authInteractor.authenticate(request)
        } catch (e: Exception) {
            println(e.message)
            response.sendError(403, "접근할 수 없는 사용자 입니다.")
            return false
        }
        return true
    }
}