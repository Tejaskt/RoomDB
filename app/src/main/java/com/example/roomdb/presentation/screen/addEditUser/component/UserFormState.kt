package com.example.roomdb.presentation.screen.addEditUser.component

import com.example.roomdb.data.local.entity.User

data class UserFormState(
    val name: String = "",
    val email: String = "",
    val age: String = "",
    val college: String = "",
    val stream: String = "",

    val nameError: String? = null,
    val emailError: String? = null,
    val ageError: String? = null,
    val collegeError: String? = null,
    val streamError: String? = null
)


/*--- Provide true when there is null field when ADD or EDIT action performed ---*/
fun UserFormState.hasError(): Boolean =
    listOf(nameError, emailError, ageError, collegeError, streamError)
        .any { it != null }


/*--- Convert UserFormState to User used when ADD or EDIT action performed ---*/
fun UserFormState.toUser(id: Int = 0): User =
    User(
        id = id,
        name = name,
        email = email,
        age = age.toInt(),
        college = college,
        stream = stream
    )
