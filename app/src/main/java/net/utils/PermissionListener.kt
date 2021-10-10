package net.utils

interface PermissionListener {
    fun granted()
    fun denied()
    fun neverAsk()
}