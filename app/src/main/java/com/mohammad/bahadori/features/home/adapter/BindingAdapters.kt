package com.mohammad.bahadori.features.home.adapter

import android.graphics.Color
import android.text.Html
import android.text.format.DateFormat
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import com.mohammad.bahadori.R
import com.mohammad.bahadori.core.Utils


@BindingAdapter("app:percentTextColor")
fun setPercentColor(textView: TextView, text: String?) {
    val number = text?.toFloatOrNull()
    number?.let {
        val color = if (number < 0) Color.parseColor("#eb5e2c") else Color.parseColor("#13b2b2")
        textView.setTextColor(color)
    }
}



@BindingAdapter("app:textNumInMillionOrBillion")
fun bindTxtNumInMillionOrBillion(textView: TextView, text: String?) {
    var num = text?.toFloatOrNull()

    lateinit var str: String
    num?.let {
        if (num < 99_999_999) {
            num /= 1000000
            val twoDecimalTxt = Utils.formatToMaxTwoDecimal(num)
            str = textView.context.resources.getString(
                R.string.generic_dollar_sign_with_million_label,
                twoDecimalTxt
            )

        } else {
            num /= 1_000_000_000
            val twoDecimalTxt = Utils.formatToMaxTwoDecimal(num)
            str = textView.context.resources.getString(
                R.string.generic_dollar_sign_with_billion_label,
                twoDecimalTxt
            )
        }
        textView.text = str
    }
}

@BindingAdapter("app:TextTwoDecimalNumWithDollarSign")
fun bindTextTwoDecimalNumWithDollarSign(textView: TextView, number: String?) {
    try {
        val num = number?.toFloat()
        num?.let {
            val formattedTxt =
                textView.context.resources.getString(R.string.generic_dollar_sign, num.toString())
            textView.text = Html.fromHtml(formattedTxt)
        }
    }catch (e:Exception){
        Log.e("Mohammad", e.toString())
    }
}

@BindingAdapter("app:textTwoDecimalNumWithPercentSign")
fun textTwoDecimalNumWithPercentSign(textView: TextView, number: String?) {
    val num = number?.toFloat()

    num?.let {
        val twoDecNumText = Utils.formatToMaxTwoDecimal(num)
        var formattedTxt =
            textView.context.resources.getString(R.string.generic_percent, twoDecNumText)

        if (!formattedTxt.contains("-")) {
            formattedTxt = "+$formattedTxt"
        }
        textView.text = formattedTxt
    }
}


@BindingAdapter("app:imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val baseUrl =
    url?.let {
        imageView.load(it) {
            diskCachePolicy(CachePolicy.ENABLED)
        }
    }


}

@BindingAdapter("app:visibilityBasedOnList")
fun visibilityBasedOnList(view: View, list: List<*>?) {
    if (list.isNullOrEmpty())
        view.visibility = View.GONE
    else
        view.visibility = View.VISIBLE
}

@BindingAdapter("app:setDateTextWithTimestamp")
fun setDateTextWithTimestamp(textView: TextView, time: Long) {
    var dateText = DateFormat.format("MM/dd/yyyy", time * 1000).toString()
    dateText = "on $dateText"
    textView.text = dateText
}