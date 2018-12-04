package com.gart.common.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.gart.common.R
import kotlinx.android.synthetic.main.label_value_widget.view.*

class LabelValueWidget@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private val view: View = LayoutInflater.from(context).inflate(R.layout.label_value_widget, this, true)

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelValueWidget, defStyle, 0)

        try {
            view.labelValueTopTextView.text = typedArray.getString(R.styleable.LabelValueWidget_top_label) ?: null
            view.labelValueBottomTextView.text = typedArray.getString(R.styleable.LabelValueWidget_bottom_label) ?: null
        } finally {
            typedArray.recycle()
        }
    }

    fun setTopText(topText: String) {
        view.labelValueTopTextView.text = topText
    }

    fun setBottomText(bottomText: String) {
        view.labelValueBottomTextView.text = bottomText
    }

}