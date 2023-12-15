package com.example.demo.model

data class Message(
    var senderName: String? = null,
    var receiverName: String? = null,
    var message: String? = null,
    var date: String? = null,
    var status: Status? = null
)
