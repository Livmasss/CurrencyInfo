package com.livmas.data.currency.models

import com.google.gson.annotations.SerializedName

internal data class CurrencyGetResponse (
    @SerializedName("Valute")
    val valute: Map<String, CurrencyResponseModel>
)
    {
    data class CurrencyResponseModel (
        @SerializedName("ID")
        val id: String,
        @SerializedName("NumCode")
        val numCode: String,
        @SerializedName("CharCode")
        val charCode: String,
        @SerializedName("Nominal")
        val nominal: Int,
        @SerializedName("Name")
        val name: String,
        @SerializedName("Value")
        val value: Float,
        @SerializedName("Previous")
        val previous: Float
    )
}
