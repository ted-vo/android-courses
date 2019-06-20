package dev.nxonxon.learnaboutview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_text.*

class EditTextActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)


        checkedTextView.setOnClickListener {
            val checked = !checkedTextView.isChecked
            if (checked) {
                checkedTextView.isChecked = true
                checkedTextView.setCheckMarkDrawable(android.R.drawable.checkbox_on_background)
            } else {
                checkedTextView.isChecked = false
                checkedTextView.checkMarkDrawable = null
            }
        }
    }
}
