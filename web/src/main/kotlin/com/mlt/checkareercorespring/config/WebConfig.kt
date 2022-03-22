package com.mlt.checkareercorespring.config

import com.mlt.checkareercorespring.interceptor.CheckPermissionInterceptor
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@RequiredArgsConstructor
@Configuration
class WebConfig(
    private val checkPermissionInterceptor: CheckPermissionInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(checkPermissionInterceptor)
    }
}