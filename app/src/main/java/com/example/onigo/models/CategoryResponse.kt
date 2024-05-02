package com.example.onigo.models

//data class CategoryResponse(
//    val categoryEEE: Map<String, List<Category>>
//)

data class CategoryResponse(
    val COSTCO: List<COSTCO>,
    val いも根菜豆: List<いも根菜豆>,
    val お手軽時短: List<お手軽時短>,
    val きのこ: List<きのこ>,
    val オーガニック: List<オーガニック>,
    val カット野菜: List<カット野菜>,
    val 薬味香味野菜: List<薬味香味野菜>,
    val 野菜: List<野菜>
)