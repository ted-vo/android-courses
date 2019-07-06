package dev.nxonxon.exampleview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactAdapter(
        context: Context,
        private val layoutRes: Int) : ArrayAdapter<Contact>(context, layoutRes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var view = convertView
        val layoutInflater = LayoutInflater.from(context)
        if (view == null) {
            view = layoutInflater.inflate(layoutRes, parent, false)
        }
        val contact = getItem(position)

        view?.tvName?.text = contact?.name ?: "Unknown"
        view?.tvPhoneNumber?.text = contact?.phoneNumber ?: "+84"
        view?.tvAge?.text = "${contact?.age ?: "NaN"}"

        return view
    }
}