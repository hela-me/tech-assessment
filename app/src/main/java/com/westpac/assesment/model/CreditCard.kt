package com.westpac.assesment.model

import com.google.gson.annotations.SerializedName

data class CreditCard(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("uid") var uid: String? = null,
    @SerializedName("credit_card_number") var creditCardNumber: String? = null,
    @SerializedName("credit_card_expiry_date") var creditCardExpiryDate: String? = null,
    @SerializedName("credit_card_type") var creditCardType: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreditCard

        if (id != other.id) return false
        if (uid != other.uid) return false
        if (creditCardNumber != other.creditCardNumber) return false
        if (creditCardExpiryDate != other.creditCardExpiryDate) return false
        if (creditCardType != other.creditCardType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (uid?.hashCode() ?: 0)
        result = 31 * result + (creditCardNumber?.hashCode() ?: 0)
        result = 31 * result + (creditCardExpiryDate?.hashCode() ?: 0)
        result = 31 * result + (creditCardType?.hashCode() ?: 0)
        return result
    }
}