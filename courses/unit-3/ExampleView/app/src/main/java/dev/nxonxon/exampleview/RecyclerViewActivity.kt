package dev.nxonxon.exampleview

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import dev.nxonxon.exampleview.reccylerview.ProductItem
import dev.nxonxon.exampleview.reccylerview.RecyclerAdapter
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        /**
         * items: [
         * {
         *  category: "Do gia dung"
         *  products: [ { name: "gia dung}, {code: 1}, {desc: "aasdasd}]
         * ]
         *
         */

        recyclerAdapter = RecyclerAdapter(mutableListOf())
        // LinearLayoutManager(this) == LinearLayoutManager(this, LinearLayout.Vertical, true)
        // LinearLayout(this, LinearLayout.HORIZONTAL, false)
        rvProduct.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false)
        rvProduct.adapter = recyclerAdapter

        Handler().postDelayed({
            val items = getItems()
            recyclerAdapter.updateDateSetChanged(items)
        }, 3000)
    }

    fun getItems(): List<ProductItem> {
        val items = mutableListOf<ProductItem>()

        return items
    }
}

