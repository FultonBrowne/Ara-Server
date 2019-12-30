package com.andromeda.araserver.iot

import com.microsoft.azure.sdk.iot.service.*


class SendData {
    private val protocol = IotHubServiceClientProtocol.AMQPS

    fun main(deviceId:String, message: String){
        val connectionString = "UxEGJfoC9IRnpeonMxDxo7A41ZT21+Z45+4CCeIeto0="
        val serviceClient = ServiceClient.createFromConnectionString(
            connectionString, protocol
        )

        if (serviceClient != null) {
            serviceClient.open()
            val feedbackReceiver = serviceClient
                .feedbackReceiver
            feedbackReceiver?.open()
            val messageToSend = Message(message)
            messageToSend.deliveryAcknowledgement = DeliveryAcknowledgement.Full
            serviceClient.send(deviceId, messageToSend)
            println("Message sent to device")
            val feedbackBatch = feedbackReceiver!!.receive(10000)
            if (feedbackBatch != null) {
                println(
                    "Message feedback received, feedback time: "
                            + feedbackBatch.enqueuedTimeUtc.toString()
                )
            }
            feedbackReceiver.close()
            serviceClient.close()
        }
    }
}