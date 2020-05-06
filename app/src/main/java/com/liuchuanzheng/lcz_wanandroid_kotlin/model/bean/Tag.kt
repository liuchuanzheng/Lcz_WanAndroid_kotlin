package com.liuchuanzheng.lcz_wanandroid_kotlin.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by xiaojianjun on 2019-11-07.
 */
@Parcelize
//@Entity
data class Tag(
//    @PrimaryKey(autoGenerate = true)
    var id: Long,
//    @ColumnInfo(name = "article_id")
    var articleId: Long,
    var name: String?,
    var url: String?
) : Parcelable