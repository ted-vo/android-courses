package dev.nxonxon.exampleview

import android.content.Context

object AssetLoader {

    fun getAsset(context: Context?, fileName: String): String? {
        if (context == null) return null
        val json = try {
            val inputSteam = context.resources.assets.open(fileName)
            val length = inputSteam.available()
            val buffer = ByteArray(length)
            inputSteam.read(buffer)
            inputSteam.close()
            String(buffer, 0, buffer.size)
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
        return json
    }
}