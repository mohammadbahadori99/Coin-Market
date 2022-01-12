package com.mohammad.bahadori.core

object Utils {

    fun formatToMaxTwoDecimal(f: Float): String {
        //this fun removes 0 from decimal
        val i = f.toInt()
        return if (f == i.toFloat()) i.toString() else String.format("%.2f", f)

    }

    fun formatToMaxFourDecimal(f: Float): String {
        //this fun removes 0 from decimal
        val i = f.toInt()
        return if (f == i.toFloat()) i.toString() else String.format("%.4f", f)

    }

}