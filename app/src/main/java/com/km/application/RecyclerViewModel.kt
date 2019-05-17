package com.km.application

class RecyclerViewModel (val name: String? = "", val description: String? = "", val star: Long? = 0, viewType: Int) :
    ViewType(viewType)