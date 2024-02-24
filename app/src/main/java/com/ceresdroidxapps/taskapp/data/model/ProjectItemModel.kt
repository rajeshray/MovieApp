package com.ceresdroidxapps.taskapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class ProjectItemModel(
    var title       : String? = null,
    var description : String? = null,
    var earning     : Double? = null,
    var logo        : String? = null
): Parcelable
