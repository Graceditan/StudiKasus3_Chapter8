package binar.and.lima.studikasus3_chapter8.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ResponseRegister(

    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
): Parcelable
