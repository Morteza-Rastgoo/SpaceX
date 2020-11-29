package com.dynamo.spacex.data.repository.model

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.dynamo.spacex.databinding.RowPastLaunchBinding
import com.mikepenz.fastadapter.binding.AbstractBindingItem
import kotlinx.android.parcel.Parcelize

/**
 * @author : Morteza Rastgoo
 * @since : 28/11/2020 AD
 **/
@Parcelize
data class PastLaunch(
    val flightNumber: Int?,
    val missionName: String?,
    val date: String?,
    val description: String,
    val youtubeId: String?,
    val imageLink: String?,
) : AbstractBindingItem<RowPastLaunchBinding>(), Parcelable {
    override val type: Int
        get() = 0

    override fun bindView(binding: RowPastLaunchBinding, payloads: List<Any>) {
        binding.textViewMissionNameValue.text = missionName
        binding.textViewLaunchDateValue.text = date
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): RowPastLaunchBinding {
        return RowPastLaunchBinding.inflate(inflater, parent, false)
    }
}