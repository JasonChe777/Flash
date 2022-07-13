package com.example.flash.model.local

import com.example.flash.model.local.FlashDao.Companion.COLUMN_CATEGORY_ID
import com.example.flash.model.local.FlashDao.Companion.COLUMN_COUNT
import com.example.flash.model.local.FlashDao.Companion.COLUMN_DESCRIPTION
import com.example.flash.model.local.FlashDao.Companion.COLUMN_ID
import com.example.flash.model.local.FlashDao.Companion.COLUMN_PRICE
import com.example.flash.model.local.FlashDao.Companion.COLUMN_PRODUCT_ID
import com.example.flash.model.local.FlashDao.Companion.COLUMN_PRODUCT_IMAGE_URL
import com.example.flash.model.local.FlashDao.Companion.COLUMN_PRODUCT_NAME
import com.example.flash.model.local.FlashDao.Companion.COLUMN_SUB_CATEGORY_ID
import com.example.flash.model.local.FlashDao.Companion.TABLE_NAME

object Constants {
    const val DB_NAME = "Flash DB"
    const val DB_VERSION = 1

    val CREATE_CART_TABLE = """CREATE TABLE $TABLE_NAME (
        $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
        $COLUMN_PRODUCT_NAME TEXT,
        $COLUMN_PRODUCT_ID TEXT,
        $COLUMN_DESCRIPTION TEXT,
        $COLUMN_PRICE REAL,
        $COLUMN_CATEGORY_ID TEXT,
        $COLUMN_SUB_CATEGORY_ID TEXT,
        $COLUMN_PRODUCT_IMAGE_URL TEXT,
        $COLUMN_COUNT INTEGER
        )""".trimIndent()

}