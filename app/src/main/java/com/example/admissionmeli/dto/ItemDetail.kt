package com.example.admissionmeli.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDetail(
    val id: String,
    val title: String,
    val price: Double,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val condition: String,
    val thumbnail: String,
    val currencyId: String
) : Parcelable

fun Product.toParcelable(): ItemDetail {
    return ItemDetail(
        id = id,
        title = title,
        price = price,
        availableQuantity = availableQuantity,
        soldQuantity = soldQuantity,
        condition = condition,
        thumbnail = thumbnail,
        currencyId = currencyId
    )
}
