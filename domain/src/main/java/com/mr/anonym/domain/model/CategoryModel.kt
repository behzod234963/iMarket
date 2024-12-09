package com.mr.anonym.domain.model

import com.google.gson.annotations.SerializedName

data class CategoryModel(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
