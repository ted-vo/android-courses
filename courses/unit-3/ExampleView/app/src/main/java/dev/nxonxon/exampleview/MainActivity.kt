package dev.nxonxon.exampleview

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var itemsAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListView()

        Log.d("###", "GetData")
        Handler().postDelayed({
            itemsAdapter.addAll(getContacts())
            itemsAdapter.notifyDataSetChanged()
            Log.d("###", "getData success")
        }, 3000)
    }

    private fun initListView() {
        var baseAdapter: BaseAdapter? = null

        var items = getItems()
        val itemArray = getItemArray()

        itemsAdapter = ContactAdapter(this, R.layout.item_contact)
        lvItems.adapter = itemsAdapter
    }

    private fun getContacts(): List<Contact> {
        val items = mutableListOf<Contact>()
        val random = Random()
        repeat(10) {
            val name = random.nextInt()
            val phoneNumer = random.nextInt()
            items.add(Contact(
                    name = "Name $name",
                    phoneNumber = "PhoneNumber $phoneNumer"))
        }
        return items
    }

    private fun getItems(): List<String> {
        return arrayOf(
                "View",
                "TextView",
                "EditText",
                "Button",
                "ImageView",
                "CheckBox",
                "RadioGroup",
                "RadioButton",
                "RatingBar",
                "Switch",
                "SeekBar",
                "SearchView",
                "ProgressBar",
                "LinearLayout",
                "FrameLayout",
                "RelativeLayout",
                "ConstraintLayout",
                "ScrollView",
                "NestedScrollView",
                "ListView",
                "RecyclerView",
                "ExpandableListView").toList()
    }

    private fun getItemArray(): Array<String> {
        return arrayOf(
                "View",
                "TextView",
                "EditText",
                "Button",
                "ImageView",
                "CheckBox",
                "RadioGroup",
                "RadioButton",
                "RatingBar",
                "Switch",
                "SeekBar",
                "SearchView",
                "ProgressBar",
                "LinearLayout",
                "FrameLayout",
                "RelativeLayout",
                "ConstraintLayout",
                "ScrollView",
                "NestedScrollView",
                "ListView",
                "RecyclerView",
                "ExpandableListView")
    }
}
