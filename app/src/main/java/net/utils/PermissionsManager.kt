package net.utils

import androidx.appcompat.app.AppCompatActivity
import com.example.weeboos.permissionlib.PermissionRequest

class PermissionsManager {
    companion object {
        private var instance: PermissionsManager? = null
            get() {
                field ?: run {
                    field = PermissionsManager()
                }
                return field
            }

        @Synchronized
        fun get(): PermissionsManager {
            return instance!!
        }
    }

    fun requestPermissions(activity: AppCompatActivity, listener: PermissionListener) {
        PermissionRequest.getInstance().build(activity)
            .requestPermission(object : PermissionRequest.PermissionListener {
                override fun permissionGranted() {
                    listener.granted()
                }

                override fun permissionDenied(permissions: java.util.ArrayList<String>?) {
                    listener.denied()
                }

                override fun permissionNeverAsk(permissions: java.util.ArrayList<String>?) {
                    listener.neverAsk()
                }

            }, Constant.permission)
    }
}

