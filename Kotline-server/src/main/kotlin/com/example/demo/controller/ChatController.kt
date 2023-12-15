package com.example.demo.controller


import com.example.demo.model.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin

@Controller
@CrossOrigin
class ChatController(private val simpMessagingTemplate: SimpMessagingTemplate) {

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    fun receiveMessage(@Payload message: Message): Message {
        return message
    }

    @MessageMapping("/private-message")
    @SendTo("/chatroom/user")
    fun recMessage(@Payload message: Message): Message {
        message.receiverName?.let { simpMessagingTemplate.convertAndSendToUser(it, "/private", message) }
        println(message.toString())
        return message
    }
}
