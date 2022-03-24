package com.mlt.checkareercorespring.infra.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import javax.annotation.PostConstruct

@Configuration
class FirebaseConfig {

    @PostConstruct
    fun init() {
        try {
            val serviceAccount = FileInputStream("./infra/src/main/resources/checkareerFirebaseKey.json")

            val options: FirebaseOptions = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://careery-97ac6-default-rtdb.firebaseio.com")
                .build()

            FirebaseApp.initializeApp(options)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}