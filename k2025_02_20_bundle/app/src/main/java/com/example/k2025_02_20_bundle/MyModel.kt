package com.example.k2025_02_20_bundle

import android.os.Parcel
import android.os.Parcelable

class MyModel() : Parcelable {

    var myInt = -99
    var myText = "Hello!"

    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyModel> {
        override fun createFromParcel(parcel: Parcel): MyModel {
            return MyModel(parcel)
        }

        override fun newArray(size: Int): Array<MyModel?> {
            return arrayOfNulls(size)
        }
    }

}