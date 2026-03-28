package com.yusubov.composebook.core.addons.viewport

data class DeviceViewport(val name: String, val width: Int, val height: Int) {
    companion object {
        val None = DeviceViewport("Full Size", 0, 0)
        val iPhone14 = DeviceViewport("iPhone 14", 390, 844)
        val iPhone14ProMax = DeviceViewport("iPhone 14 Pro Max", 430, 932)
        val Pixel7 = DeviceViewport("Pixel 7", 412, 915)
        val iPadMini = DeviceViewport("iPad Mini", 744, 1133)
        val Desktop1080 = DeviceViewport("Desktop 1080p", 1920, 1080)

        val defaults = listOf(None, iPhone14, iPhone14ProMax, Pixel7, iPadMini, Desktop1080)
    }
}