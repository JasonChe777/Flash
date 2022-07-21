package com.example.flash.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flash.R
import com.example.flash.model.remote.data.address.Address

class AddressAdapter(private val context: Context, private val infoArrayList: ArrayList<Address>) :
    RecyclerView.Adapter<AddressAdapter.AddressHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.view_address, parent, false)
        return AddressHolder(view)
    }

    override fun onBindViewHolder(holder: AddressHolder, position: Int) {
        holder.apply {
            val info = infoArrayList[position]
            tvAddressTitle.text = info.title
            tvAddressAddress.text = info.address
            itemView.setOnClickListener {
                Log.e("address_id", "${info.address_id}")
            }

        }
    }

    override fun getItemCount(): Int {
        return infoArrayList.size
    }


    inner class AddressHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvAddressTitle: TextView = view.findViewById(R.id.tv_address_title)
        val tvAddressAddress: TextView = view.findViewById(R.id.tv_address_address)
    }
}
