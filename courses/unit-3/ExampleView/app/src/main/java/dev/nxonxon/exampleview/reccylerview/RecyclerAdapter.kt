package dev.nxonxon.exampleview.reccylerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.nxonxon.exampleview.R
import kotlinx.android.synthetic.main.item_contact_full.view.*

class RecyclerAdapter(private var items: List<ProductItem>) : RecyclerView.Adapter<BaseItem>() {

    companion object {
        val TYPE_EMPTY = 0
        val TYPE_CATEGORY = 1
        val TYPE_PRODUCT = 2
    }

    override fun onCreateViewHolder(p0: ViewGroup, type: Int): BaseItem {
        return when (type) {
            TYPE_PRODUCT -> {
                val view = LayoutInflater
                        .from(p0.context)
                        .inflate(R.layout.item_contact_full, p0, false)
                ProductionViewHolder(view)
            }
            TYPE_CATEGORY -> {
                val view = LayoutInflater
                        .from(p0.context)
                        .inflate(R.layout.item_category, p0, false)
                CategoryViewHolder(view)
            }
            else -> {
                val view = LayoutInflater
                        .from(p0.context)
                        .inflate(R.layout.item_empty, p0, false)
                EmptyHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty()) 1 else items.size
    }

    override fun onBindViewHolder(holder: BaseItem, position: Int) {
        holder.bind(position)
    }

    override fun getItemViewType(position: Int): Int {
        if (items.isEmpty()) return TYPE_EMPTY

        return items[position].type
    }

    fun updateDateSetChanged(items: List<ProductItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ProductionViewHolder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {
            val product = items[position]
            itemView.tvName.text = product.name
            itemView.tvRole.text = "Ma: ${product.code}"
            itemView.tvDes.text = product.desc
        }
    }

    inner class CategoryViewHolder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {

        }
    }

    inner class EmptyHolder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {}
    }
}