package dev.nxonxon.exampleview.image

import android.content.Context
import android.widget.ImageView
import okhttp3.OkHttpClient
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object ImageLoader {

    fun load(context: Context, url: String?, imageView: ImageView) {
        url?.let {
            GlideApp.with(context)
                .load(url)
                .into(imageView)
        }
    }

    fun unsafeOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) {
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return mutableListOf<java.security.cert.X509Certificate>().toTypedArray()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            okHttpClientBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            okHttpClientBuilder.hostnameVerifier(HostnameVerifier { _, _ -> true })
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}