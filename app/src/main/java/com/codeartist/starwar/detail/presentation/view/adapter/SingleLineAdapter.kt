package com.codeartist.starwar.detail.presentation.view.adapter

import androidx.databinding.ViewDataBinding
import com.codeartist.starwar.R
import com.codeartist.starwar.databinding.ItemSingleLineBinding
import javax.inject.Inject



class SingleLineAdapter @Inject constructor() :
    BaseAdapter<String, SingleLineAdapter.ViewHolder>() {
    override fun layoutId(viewType: Int) = R.layout.item_single_line

    override fun createViewHolder(
        bind: ViewDataBinding,
        viewType: Int
    ) = ViewHolder(bind as ItemSingleLineBinding)

    inner class ViewHolder(
        val bind: ItemSingleLineBinding
    ) : BaseAdapter.ViewHolder<String, ViewHolder>(bind) {
        override fun bindView(input: String, position: Int) {
            bind.text = input
            bind.executePendingBindings()
        }
    }

}
