package com.conkers.siceapp.Network

import android.content.Context

class ReceivedCookiesInterceptor {
    (private val context: Context) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalResponse = chain.proceed(chain.request())
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                val cookies = PreferenceManager.getDefaultSharedPreferences(
                    context
                ).getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>?
                for (header in originalResponse.headers("Set-Cookie")) {
                    cookies!!.add(header)
                }
                val memes = PreferenceManager.getDefaultSharedPreferences(
                    context
                ).edit()
                memes.putStringSet("PREF_COOKIES", cookies).apply()
                memes.commit()
            }
            return originalResponse
        }

        companion object {
            const val PREF_COOKIES = "PREF_COOKIES"
        }
}