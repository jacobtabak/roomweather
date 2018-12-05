package me.tabak.jacob.roomweather

import okhttp3.Interceptor
import okhttp3.Response

class OwmApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter(QUERY_API_KEY, BuildConfig.OWM_API_KEY)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        const val QUERY_API_KEY = "APPID"
    }
}
