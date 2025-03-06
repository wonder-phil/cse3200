package com.example.k2025_03_03_retrofit_in_viewmodel.ViewModels

import android.os.Parcel
import android.os.Parcelable


class Bitcoin(val name: String, val hash: String, val number: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
    parcel.readString() ?: "", parcel.readString() ?: "", parcel.readInt()) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)  // Write a String field
        dest.writeString(hash)
        dest.writeInt(number)
    }

    companion object CREATOR : Parcelable.Creator<Bitcoin> {
        override fun createFromParcel(parcel: Parcel): Bitcoin {
            return Bitcoin(parcel)
        }

        override fun newArray(size: Int): Array<Bitcoin?> {
            return arrayOfNulls(size)
        }
    }

}