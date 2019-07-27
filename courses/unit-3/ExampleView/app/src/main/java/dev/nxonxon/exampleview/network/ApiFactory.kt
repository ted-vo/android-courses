package dev.nxonxon.exampleview.network

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiFactory(
    private val success: (String) -> Unit,
    private val failure: (String) -> Unit
) : AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String?): String? {
        return fetchData(params[0]!!)
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)

    }

    override fun onPostExecute(result: String?) {
        if (result == null || result.startsWith("error")) failure(result ?: "Unknown Error")
        else success(result)
    }

    private fun fetchData(apiUrl: String): String? {
        try {
            val url = URL(apiUrl)
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            with(urlConnection) {
                requestMethod = "GET"
                connectTimeout = 15000 // <= milliseconds
                readTimeout = 10000
                doOutput = true
                connect()
            }

            val inputStreamReader = InputStreamReader(url.openStream())
            val bufferReader = BufferedReader(inputStreamReader)

            val stringBuilder = StringBuffer()
            var line = bufferReader.readLine()
            // ["h","e","l","l","o"]
            while (line != null) {
                stringBuilder.append(line)
                line = bufferReader.readLine()
            }

            bufferReader.close()
            inputStreamReader.close()
            urlConnection.disconnect()

            return stringBuilder.toString()
        } catch (exception: IOException) {
            exception.printStackTrace()
            return "error=${exception.message}"
        }
    }
}