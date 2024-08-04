package com.rees46.demo_android.ui.countCard.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.rees46.ui.databinding.ViewCountCardBinding

@SuppressLint("ViewConstructor")
open class CountCardView @JvmOverloads constructor(
    context: Context,
    val attrs: AttributeSet? = null,
    val defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr)  {

    private var binding: ViewCountCardBinding =
        ViewCountCardBinding.inflate(LayoutInflater.from(context), this, true)

    fun updateCount(count: Int) {
        binding.valueText.text = count.toString()
    }

    fun setOnClickListener(
        onMinusClick: () -> Unit,
        onPlusClick: () -> Unit
    ) {
        with(binding) {
            minusButton.setOnClickListener { onMinusClick.invoke() }
            plusButton.setOnClickListener { onPlusClick.invoke() }
        }
    }
}
