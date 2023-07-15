package com.xorko.pokedex.common.data

import com.xorko.pokedex.common.utils.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response

class NoInternetInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtils.isOnline()) {
            throw NoInternetException()
        }

        return chain.proceed(chain.request())
    }
}