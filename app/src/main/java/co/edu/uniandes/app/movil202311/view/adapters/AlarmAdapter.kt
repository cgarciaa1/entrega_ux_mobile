package co.edu.uniandes.app.movil202311.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.app.movil202311.R
import co.edu.uniandes.app.movil202311.databinding.AlarmItemBinding
import co.edu.uniandes.app.movil202311.models.Alarm
import co.edu.uniandes.app.movil202311.models.Global
import co.edu.uniandes.app.movil202311.view.AlarmFragmentDirections


class AlarmAdapter : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>(){

    var alarms :List<Alarm> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val withDataBinding: AlarmItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlarmViewHolder.LAYOUT,
            parent,
            false)
        return AlarmViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.alarm = alarms[position]
        }

        holder.viewDataBinding.root.setOnClickListener {
            if(Global.login) {
                val action = AlarmFragmentDirections.actionAlbumFragmentToPublishFragment()
                // Navigate using that action
                holder.viewDataBinding.root.findNavController().navigate(action)
            }
        }


    }

    override fun getItemCount(): Int {
        return alarms.size
    }


    class AlarmViewHolder(val viewDataBinding: AlarmItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.alarm_item
        }

    }


}
