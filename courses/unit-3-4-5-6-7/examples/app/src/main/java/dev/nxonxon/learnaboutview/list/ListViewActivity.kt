package dev.nxonxon.learnaboutview.list

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import dev.nxonxon.learnaboutview.R
import kotlinx.android.synthetic.main.activity_list_view.*
import kotlinx.android.synthetic.main.item_contact.view.*
import kotlinx.android.synthetic.main.item_custom.view.*

class ListViewActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        initWithBasic()
//        initWithCustomAdapter()
//        initWithCursorAdapter()
    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<Cursor> {
        // Run query
        val uri = ContactsContract.Contacts.CONTENT_URI
        val projection = arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME)
        val selection = (ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
                + "1" + "'")
        val selectionArgs: Array<String>? = null
        val sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC"
        return CursorLoader(
                this,
                uri,
                projection,
                selection,
                selectionArgs,
                sortOrder)
    }

    override fun onLoadFinished(p0: Loader<Cursor>, p1: Cursor?) {
        val items = ArrayList<Contact>()
        while (p1?.moveToNext() == true) {
            val id = p1.getInt(p1.getColumnIndex(ContactsContract.Contacts._ID))
            val name = p1.getString(p1.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            items.add(Contact(id, name))
        }
        val itemAdapter = ContactAdapter(
                this,
                R.layout.item_contact,
                items)
        lvList.adapter = itemAdapter
        LoaderManager.getInstance(this).destroyLoader(1)
    }

    override fun onLoaderReset(p0: Loader<Cursor>) {}

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        return when (requestCode) {
            1 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                            baseContext,
                            "Please grant permission for read Contact",
                            Toast.LENGTH_LONG
                    ).show()
                } else {
                    initWithCursorAdapter()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun initWithBasic() {
        val items = getItems()
        val itemAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                items)
        lvList.adapter = itemAdapter
    }

    private fun initWithCursorAdapter() {
        val s = arrayOf(Manifest.permission.READ_CONTACTS)
        if (hasPermissions(s)) {
            LoaderManager.getInstance(this).initLoader(1, null, this)
        } else {
            requestPermissionsSafely(s, 1)
        }
    }

    private fun initWithCustomAdapter() {
        val items = getItems()
        val itemAdapter = ItemAdapter(
                this,
                R.layout.item_custom,
                items)
        lvList.adapter = itemAdapter
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

    @TargetApi(Build.VERSION_CODES.M)
    private fun hasPermission(permission: String) =
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED

    private fun hasPermissions(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (!hasPermission(permission)) return false
        }
        return true
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    inner class ItemAdapter(context: Context,
                            private var layoutRes: Int,
                            items: List<String>) : ArrayAdapter<String>(context, layoutRes, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView
                    ?: LayoutInflater.from(context).inflate(layoutRes, parent, false)
            view.tvName.text = getItem(position)
            return view
        }
    }

    inner class ContactAdapter(context: Context,
                               private var layoutRes: Int,
                               items: List<Contact>) : ArrayAdapter<Contact>(context, layoutRes, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView
                    ?: LayoutInflater.from(context).inflate(layoutRes, parent, false)
            val item = getItem(position)
            view.tvId.text = item?.id?.toString()
            view.tvDisplayName.text = item?.displayName
            return view
        }
    }

    data class Contact(val id: Int, val displayName: String)
}