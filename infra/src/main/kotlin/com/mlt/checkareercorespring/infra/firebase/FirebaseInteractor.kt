package com.mlt.checkareercorespring.infra.firebase

import com.google.firebase.auth.FirebaseAuth
import com.mlt.checkareercorespring.domain.auth.AuthInteractor
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class FirebaseInteractor : AuthInteractor {

    override fun authenticate(request: HttpServletRequest) {
        val accessToken = request.getHeader("Authorization").replace("Bearer ", "")
        FirebaseAuth.getInstance().verifyIdToken(accessToken)
    }
}