package dev.nxonxon.learnaboutview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import dev.nxonxon.learnaboutview.list.ListViewActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onViewClicked(view: View) {

    }

    fun onTextViewClicked(view: View) {
        start(ViewActivity::class.java)
    }

    fun onButtonClicked(view: View) {

    }

    fun onEditTextClicked(view: View) {
        start(EditTextActivity::class.java)
    }

    fun onImageViewClicked(view: View) {

    }

    fun onCheckBoxClicked(view: View) {

    }

    fun onRadioGroupClicked(view: View) {

    }

    fun onRatingBarClicked(view: View) {

    }

    fun onSwitchClicked(view: View) {

    }

    fun onSeekBarClicked(view: View) {

    }

    fun onSearchViewClicked(view: View) {

    }

    fun onProgressBarClicked(view: View) {

    }

    fun onListViewClicked(view: View) {
        start(ListViewActivity::class.java)
    }

    private fun start(clazz: Class<*>) = startActivity(Intent(this, clazz))

}
