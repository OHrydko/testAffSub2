package com.example.testaffsub2.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList


data class Data(

	@SerializedName("results") val results: ArrayList<Results>,
	@SerializedName("info") val info: Info
)