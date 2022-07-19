package com.example.flash.model.local

import com.example.flash.model.local.CartDao.Companion.COLUMN_COUNT
import com.example.flash.model.local.CartDao.Companion.COLUMN_DESCRIPTION
import com.example.flash.model.local.CartDao.Companion.COLUMN_ID
import com.example.flash.model.local.CartDao.Companion.COLUMN_PRICE
import com.example.flash.model.local.CartDao.Companion.COLUMN_PRODUCT_ID
import com.example.flash.model.local.CartDao.Companion.COLUMN_PRODUCT_NAME
import com.example.flash.model.local.CartDao.Companion.TABLE_NAME

object Constants {
    const val DB_NAME = "Flash DB"
    const val DB_VERSION = 3

    val CREATE_CART_TABLE = """CREATE TABLE $TABLE_NAME (
        $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $COLUMN_PRODUCT_NAME TEXT,
        $COLUMN_PRODUCT_ID TEXT,
        $COLUMN_DESCRIPTION TEXT,
        $COLUMN_PRICE REAL,
        $COLUMN_COUNT INTEGER
        )""".trimIndent()

}