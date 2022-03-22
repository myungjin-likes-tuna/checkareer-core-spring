package com.mlt.checkareercorespring.domain.auth

import javax.servlet.http.HttpServletRequest

interface AuthInteractor {
    fun authenticate(request: HttpServletRequest)
}