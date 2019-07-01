package dev.nxonxon.exampleview.reccylerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseItem(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(position: Int)
}