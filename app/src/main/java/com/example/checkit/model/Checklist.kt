package com.example.checkit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Checklist(
    var title: String,
    var checkBoxes: ArrayList<String>,
    var date: String
) : Parcelable
