package com.example.flash.model.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class CartDao(private val context: Context) {

    private val dbHelper = DBHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase


    fun addCartItem(cartItem: CartItem): Long? {
        val addedCartItem = getCartItemByProductId(cartItem.productId.toInt())

        if (addedCartItem == null) {
            val contentValues = ContentValues()
            contentValues.apply {

                put(COLUMN_PRODUCT_NAME, cartItem.productName)
                put(COLUMN_PRODUCT_ID, cartItem.productId)
                put(COLUMN_DESCRIPTION, cartItem.description)
                put(COLUMN_PRICE, cartItem.price)
                put(COLUMN_COUNT, cartItem.count)
            }

            return db.insert(TABLE_NAME, null, contentValues)
        } else {
           updateCartByProductId(addedCartItem,cartItem)
            return null
        }
    }

    private fun updateCartByProductId(addedCartItem:CartItem, cartItem: CartItem):Long{
        val contentValues = ContentValues()
        contentValues.apply {

            put(COLUMN_PRODUCT_NAME, cartItem.productName)
            put(COLUMN_PRODUCT_ID, cartItem.productId)
            put(COLUMN_DESCRIPTION, cartItem.description)
            put(COLUMN_PRICE, cartItem.price)
            put(COLUMN_COUNT, addedCartItem.count + cartItem.count)
        }
       return db.update(TABLE_NAME,contentValues,"${COLUMN_PRODUCT_ID}=${cartItem.productId}",null).toLong()

    }

     fun increaseCountByOne(cartItem:CartItem){
         val contentValues = ContentValues()
         contentValues.apply {

             put(COLUMN_PRODUCT_NAME, cartItem.productName)
             put(COLUMN_PRODUCT_ID, cartItem.productId)
             put(COLUMN_DESCRIPTION, cartItem.description)
             put(COLUMN_PRICE, cartItem.price)
             put(COLUMN_COUNT, cartItem.count+ 1)
         }
          db.update(TABLE_NAME,contentValues,"${COLUMN_PRODUCT_ID}=${cartItem.productId}",null)
     }


    fun decreaseCountByOne(cartItem:CartItem){
        val contentValues = ContentValues()
        contentValues.apply {

            put(COLUMN_PRODUCT_NAME, cartItem.productName)
            put(COLUMN_PRODUCT_ID, cartItem.productId)
            put(COLUMN_DESCRIPTION, cartItem.description)
            put(COLUMN_PRICE, cartItem.price)
            put(COLUMN_COUNT, cartItem.count- 1)
        }
        db.update(TABLE_NAME,contentValues,"${COLUMN_PRODUCT_ID}=${cartItem.productId}",null)
    }



    @SuppressLint("Range")
    fun getAllCartItem(): ArrayList<CartItem> {
        val cartItemItemList = ArrayList<CartItem>()
        val cursor: Cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val cartId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME))
                val productId = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
                val count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))
                cartItemItemList.add(
                    CartItem(
                        cartId,
                        productName,
                        productId,
                        description,
                        price,
                        count
                    )
                )
            } while (cursor.moveToNext())
        }
        return cartItemItemList
    }

    @SuppressLint("Range")
    fun getCartItemByProductId(productId: Int): CartItem? {
        val cursor: Cursor = db.query(
            TABLE_NAME,
            null,
            "$COLUMN_PRODUCT_ID=?",
            arrayOf("$productId"),
            null,
            null,
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            val cartId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
            val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME))
            val productId = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))
            val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
            val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
            val count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))
            return CartItem(
                cartId,
                productName,
                productId,
                description,
                price,
                count
            )
        }
        return null
    }

    fun deleteCartItem(productId:String): Boolean {
        val numOfRowDeleted: Int = db.delete(TABLE_NAME, "$COLUMN_PRODUCT_ID=$productId", null)
        return numOfRowDeleted == 1
    }


    fun clearTable() {
        db.execSQL("delete from $TABLE_NAME");
    }

    companion object {
        const val TABLE_NAME = "cart"
        const val COLUMN_ID = "cartId"
        const val COLUMN_PRODUCT_NAME = "productName"
        const val COLUMN_PRODUCT_ID = "productId"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PRICE = "price"
        const val COLUMN_COUNT = "count"

    }

}