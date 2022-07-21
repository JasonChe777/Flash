package com.example.flash.view

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.flash.R
import com.example.flash.UserLogin.Companion.FILE_NAME
import com.example.flash.UserLogin.Companion.USER_ID
import com.example.flash.databinding.DialogAddAddressBinding
import com.example.flash.model.remote.data.address.Address
import com.example.flash.model.remote.data.address.AddressResponse
import com.example.flash.model.remote.volleyhandler.AddressVolleyHandler
import com.example.flash.presenter.addAddress.AddAddressMVP
import com.example.flash.presenter.addAddress.AddAddressPresenter
import com.example.flash.presenter.getAddress.GetAddressMVP
import com.example.flash.presenter.getAddress.GetAddressPresenter

class PlaceOrderDeliveryFragment : Fragment(), GetAddressMVP.GetAddressView, AddAddressMVP.AddAddressView {

    private lateinit var getAddressPresenter: GetAddressPresenter
    private lateinit var addAddressPresenter: AddAddressPresenter
    lateinit var adapter: AddressAdapter
    lateinit var addressList: ArrayList<Address>
    lateinit var currentView: View
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var title: String
    lateinit var address: String
    lateinit var rgAddAddress: RadioGroup

    lateinit var btnIdMap: HashMap<Int, Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_place_order_delivery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentView = view
        getAddressPresenter = GetAddressPresenter(AddressVolleyHandler(view.context), this)
        addAddressPresenter = AddAddressPresenter(AddressVolleyHandler(view.context), this)
        sharedPreferences = this.requireActivity().getSharedPreferences(
            FILE_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        editor = sharedPreferences.edit()
        btnIdMap = HashMap()
        val userId = sharedPreferences.getString(USER_ID, "-1")

        userId?.let {
            getAddressPresenter.getAddress(userId)
        }

        rgAddAddress = currentView.findViewById<RadioGroup>(R.id.rg_choose_address);

        rgAddAddress.setOnCheckedChangeListener{
                group: RadioGroup, _: Int ->
            val checkRadioButton = group.findViewById<RadioButton>(group.checkedRadioButtonId)

            checkRadioButton?.let {
                val index = btnIdMap[checkRadioButton.id]
                if(index != null){
                    title = addressList[index].title
                    address = addressList[index].address
                }
            }
        }

        val btnCheckoutDeliveryNext: Button = currentView.findViewById(R.id.btn_checkout_delivery_next)
        btnCheckoutDeliveryNext.setOnClickListener {
            editor.putString(ADDRESS_TITLE, title)
            editor.putString(ADDRESS_ADDRESS, address)
            editor.apply()
            (this.parentFragment as PlaceOrderFragment).nextPager()
        }

        val btnCheckoutDeliveryAddAddress: Button = currentView.findViewById(R.id.btn_add_address)
        btnCheckoutDeliveryAddAddress.setOnClickListener {
            showAddBookDialog()

        }
    }

    private fun showAddBookDialog() {
        val dialogBinding = DialogAddAddressBinding.inflate(layoutInflater)

        val builder = AlertDialog.Builder(currentView.context).apply {
            setView(dialogBinding.root)
            setCancelable(false)
        }

        val dialog = builder.create()
        dialogBinding.apply {
            btnAddAddressSave.setOnClickListener {
                val title = etAddAddressTitle.text.toString()
                val address = etAddAddressAddress.text.toString()
                val userId = sharedPreferences.getString(USER_ID, "-1")
                userId?.let {
                    addAddressPresenter.addAddress(userId, title, address)
                }
                dialog.dismiss()
            }

            btnAddAddressCancel.setOnClickListener {
                dialog.dismiss()
            }
        }
        dialog.show()
    }


    @SuppressLint("ResourceAsColor")
    override fun setResult(addressResponse: AddressResponse?) {
        addressResponse?.let {

            addressList = addressResponse.addresses
            if (addressList.size > 0) {
                title = addressList[0].title
                address = addressList[0].address
            } else {
                title = ""
                address = ""
            }

            rgAddAddress.removeAllViews()



            for (i in 0 until addressList.size) {
                val radioButton = RadioButton(currentView.context);
                val lp: RadioGroup.LayoutParams = RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT
                )
                lp.setMargins(0, 10, 0, 10)
                radioButton.layoutParams = lp
                radioButton.setBackgroundResource(R.drawable.shape_category_card)
                radioButton.setTextColor(R.color.login_text_color)
                val spannableString = SpannableString(addressList[i].title)
                spannableString.setSpan(AbsoluteSizeSpan(20, true), 0, addressList[i].title.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                radioButton.text = spannableString
                radioButton.append("\n")
                radioButton.append(addressList[i].address)
                rgAddAddress.addView(radioButton)
                btnIdMap[radioButton.id] = i

                if(i == 0){
                    rgAddAddress.check(radioButton.id)
                }
            }
        }
    }

    override fun setResult(message: String) {
        val userId = sharedPreferences.getString(USER_ID, "-1")
        userId?.let {
            getAddressPresenter.getAddress(userId)
        }
    }

    override fun onLoad(isLoading: Boolean) {
    }

    companion object {
        const val ADDRESS_TITLE = "address_title"
        const val ADDRESS_ADDRESS = "address_address"
    }
}