package dev.nxonxon.exampleview.instagram

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import dev.nxonxon.exampleview.R
import kotlinx.android.synthetic.main.activity_instagram.*

class InstagramActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram)

        val view = LayoutInflater
                .from(this)
                .inflate(R.layout.item_new_feed, root, false)
    }
}