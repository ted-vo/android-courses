package dev.nxonxon.exampleview.instagram

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.nxonxon.exampleview.R
import dev.nxonxon.exampleview.image.GlideApp
import dev.nxonxon.exampleview.reccylerview.BaseItem
import kotlinx.android.synthetic.main.item_new_feed.view.*

class NewfeedAdapter(
    private val context: Context,
    private var items: List<Newfeed>
) :
    RecyclerView.Adapter<BaseItem>() {

    companion object {
        val TYPE_EMPTY = 0
        val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (items.isEmpty()) TYPE_EMPTY else TYPE_ITEM
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BaseItem {
        return if (viewType == TYPE_EMPTY) {
            val view = LayoutInflater
                .from(viewGroup.context)
                .inflate(R.layout.item_empty, viewGroup, false)
            EmptyHolder(view)
        } else {
            val view = LayoutInflater
                .from(viewGroup.context)
                .inflate(R.layout.item_new_feed, viewGroup, false)
            NeefeedViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return if (items.isEmpty()) 1 else items.size
    }

    override fun onBindViewHolder(holder: BaseItem, position: Int) {
        holder.bind(position)
    }

    fun updateDataSetChange(feedsItems: List<Newfeed>) {
        items = feedsItems
        notifyDataSetChanged()
    }

    inner class NeefeedViewHolder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {
            val item = items[position]
            with(itemView) {
                tvName.text = item.user.name
                GlideApp.with(this@NewfeedAdapter.context)
                    .load(item.imageURL)
                    .error(R.color.colorGrey)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivImageContent)
                btnLike.isSelected = item.like
                btnBookMark.isSelected = item.bookMark
            }
        }
    }

    inner class EmptyHolder(view: View) : BaseItem(view) {
        override fun bind(position: Int) {}
    }
}