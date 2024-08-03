package com.rees46.demo_android.ui.recyclerView.base.view

import android.content.Context
import android.util.AttributeSet
import com.rees46.demo_android.ui.recyclerView.base.models.Item
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.ItemView
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.OnItemClickListener
import com.rees46.demo_android.ui.recyclerView.base.view.adapter.RecyclerItemAdapter

abstract class RecyclerView<I: Item, IV: ItemView> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.recyclerview.widget.RecyclerView(context, attrs, defStyleAttr) {

    fun setup(
        listener: OnItemClickListener
    ) {
        adapter = createAdapter(listener)

        this.layoutManager = createLayoutManager()
    }

    abstract fun createAdapter(
        listener: OnItemClickListener
    ): RecyclerItemAdapter<I, IV>

    abstract fun createLayoutManager(): LayoutManager
}
