package com.mlt.checkareercorespring.infra.firebase

import com.google.firebase.auth.FirebaseAuth
import com.mlt.checkareercorespring.domain.auth.AuthInteractor
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

@Component
class FirebaseInteractor : AuthInteractor {

    override fun authenticate(request: HttpServletRequest) {
        val cookies = request.cookies
        val accessToken = cookies.first { it.name == "ACCESS_TOKEN" }.value

        val decodedToken = FirebaseAuth.getInstance().verifyIdToken(accessToken)
        val uid = decodedToken.name

        println(uid)
    }
}