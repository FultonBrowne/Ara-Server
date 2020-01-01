package com.andromeda.araserver.iot

import com.microsoft.azure.sdk.iot.service.Device
import com.microsoft.azure.sdk.iot.service.RegistryManager
import com.microsoft.azure.sdk.iot.service.auth.AuthenticationType


class NewDevice {
    fun main(url: String){
        val mainVal = url.replace("/newdevice/", "")

       val rm = RegistryManager()
        val device = Device.createDevice("", AuthenticationType.SAS)
    }

}