package com.example.k2025_02_24_simpleretrofit.models

import android.os.Parcel
import android.os.Parcelable


class User(val name: String, val number: Int) : Parcelable  {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",  // Read the 'name' field
        parcel.readInt()            // Read the 'number' field
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)  // Write a String field
        dest.writeInt(number)
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}