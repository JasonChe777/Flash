package com.example.flash.model.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class FlashDao (private val context: Context) {

    private val dbHelper = DBHelper(context)
    private val db: SQLiteDatabase = dbHelper.writableDatabase

    fun addCartItem(cartItem : Cart): Long {
        val contentValues = ContentValues()
        contentValues.apply {

            put(COLUMN_PRODUCT_NAME, cartItem.productName)
            put(COLUMN_PRODUCT_ID, cartItem.productId)
            put(COLUMN_DESCRIPTION, cartItem.description)
            put(COLUMN_PRICE, cartItem.price)
            put(COLUMN_CATEGORY_ID, cartItem.categoryId)
            put(COLUMN_SUB_CATEGORY_ID, cartItem.subCategoryId)
            put(COLUMN_PRODUCT_IMAGE_URL, cartItem.productImageUrl)
            put(COLUMN_COUNT, cartItem.count)
        }

        return db.insert(TABLE_NAME, null, contentValues)
    }

    @SuppressLint("Range")
    fun getAllCartItem(): ArrayList<Cart> {
        val cartItemList = ArrayList<Cart>()
        val cursor: Cursor = db.query(TABLE_NAME, null, null, null, null, null, null)
        if(cursor.moveToFirst()) {
            do {
                val cartId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
                val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME))
                val productId = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))
                val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
                val categoryId = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID))
                val subCategoryId = cursor.getString(cursor.getColumnIndex(COLUMN_SUB_CATEGORY_ID))
                val productImageUrl =
                    cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE_URL))
                val count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))
                cartItemList.add(
                    Cart(
                        cartId,
                        productName,
                        productId,
                        description,
                        price,
                        categoryId,
                        subCategoryId,
                        productImageUrl,
                        count
                    )
                )
            } while (cursor.moveToNext())
        }
        return cartItemList
    }

    @SuppressLint("Range")
    fun getCartItem(cartId: Long): Cart? {
        val cursor: Cursor = db.query(TABLE_NAME, null, "$COLUMN_ID=?", arrayOf("$cartId"), null, null, null)
        if(cursor.moveToFirst()) {
            val cartId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
            val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME))
            val productId = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))
            val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
            val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
            val categoryId = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID))
            val subCategoryId = cursor.getString(cursor.getColumnIndex(COLUMN_SUB_CATEGORY_ID))
            val productImageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE_URL))
            val count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))
            return Cart(
                cartId,
                productName,
                productId,
                description,
                price,
                categoryId,
                subCategoryId,
                productImageUrl,
                count
            )
        }
        return null
    }

    @SuppressLint("Range")
    fun getCartItemByProductId(productId: Int): Cart? {
        val cursor: Cursor = db.query(TABLE_NAME, null, "$COLUMN_PRODUCT_ID=?", arrayOf("$productId"), null, null, null)
        if(cursor.moveToFirst()) {
            val cartId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
            val productName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_NAME))
            val productId = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_ID))
            val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
            val price = cursor.getDouble(cursor.getColumnIndex(COLUMN_PRICE))
            val categoryId = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_ID))
            val subCategoryId = cursor.getString(cursor.getColumnIndex(COLUMN_SUB_CATEGORY_ID))
            val productImageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE_URL))
            val count = cursor.getInt(cursor.getColumnIndex(COLUMN_COUNT))
            return Cart(
                cartId,
                productName,
                productId,
                description,
                price,
                categoryId,
                subCategoryId,
                productImageUrl,
                count
            )
        }
        return null
    }

    fun deleteCartItem(cartId: Long): Boolean {
        val numOfRowDeleted: Int = db.delete(TABLE_NAME, "$COLUMN_ID=$cartId", null)
        return numOfRowDeleted == 1
    }

    fun updateCartItem(cart: Cart): Boolean {
        val contentValues = ContentValues()
        contentValues.apply {
            put(COLUMN_PRODUCT_NAME, cart.productName)
            put(COLUMN_PRODUCT_ID, cart.productId)
            put(COLUMN_DESCRIPTION, cart.description)
            put(COLUMN_PRICE, cart.price)
            put(COLUMN_CATEGORY_ID, cart.categoryId)
            put(COLUMN_SUB_CATEGORY_ID, cart.subCategoryId)
            put(COLUMN_PRODUCT_IMAGE_URL, cart.productImageUrl)
            put(COLUMN_COUNT, cart.count)
        }

        val numOfRowDeleted: Int =
            db.update(TABLE_NAME, contentValues, "$COLUMN_ID=${cart.cartId}", null)
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
        const val COLUMN_CATEGORY_ID = "categoryId"
        const val COLUMN_SUB_CATEGORY_ID = "subCategoryId"
        const val COLUMN_PRODUCT_IMAGE_URL = "productImageUrl"
        const val COLUMN_COUNT = "count"

    }


}