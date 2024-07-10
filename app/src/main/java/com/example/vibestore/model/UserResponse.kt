package com.example.vibestore.model

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("address")
	val address: Address,

	@field:SerializedName("phone")
	val phone: String,

	@field:SerializedName("name")
	val name: Name,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)

data class Geolocation(

	@field:SerializedName("lat")
	val lat: String,

	@field:SerializedName("long")
	val jsonMemberLong: String
)

data class Address(

	@field:SerializedName("zipcode")
	val zipcode: String,

	@field:SerializedName("number")
	val number: Int,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("street")
	val street: String,

	@field:SerializedName("geolocation")
	val geolocation: Geolocation
)

data class Name(

	@field:SerializedName("firstname")
	val firstname: String,

	@field:SerializedName("lastname")
	val lastname: String
)
