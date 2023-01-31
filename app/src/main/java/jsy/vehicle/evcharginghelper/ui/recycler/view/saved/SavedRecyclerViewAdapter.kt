package jsy.vehicle.evcharginghelper.ui.recycler.view.saved

import android.util.Log
import androidx.databinding.library.baseAdapters.BR
import jsy.vehicle.evcharginghelper.R
import jsy.vehicle.evcharginghelper.base.BaseRecyclerView
import jsy.vehicle.evcharginghelper.databinding.ItemPathBinding
import jsy.vehicle.evcharginghelper.model.database.entity.RouteHistory


class SavedRecyclerViewAdapter(private val itemClickListener: (routeHistory: RouteHistory) -> Unit) : BaseRecyclerView.Adapter<RouteHistory, ItemPathBinding>(
    layoutResId = R.layout.item_path,
    bindingVariableId = BR.RouteHistory
) {

    override fun onBindViewHolder(
        holder: BaseRecyclerView.ViewHolder<ItemPathBinding>,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.setOnClickListener {
            Log.d(logTag, "itemViewClick : ${holder.binding.tvDestinationName.text}")
            itemClickListener(holder.binding.routeHistory!!)
        }
    }

}