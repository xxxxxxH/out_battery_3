package net.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import net.basicmodel.R
import net.entity.ResourceEntity

class ResourceManager {

    private val bg = "https:magichua.club/preview/img/bg_*.jpg"

    companion object {
        private var i: ResourceManager? = null
            get() {
                field ?: run {
                    field = ResourceManager()
                }
                return field
            }

        @Synchronized
        fun get(): ResourceManager {
            return i!!
        }
    }

    fun initTipsData(): ArrayList<String> {
        val tips = ArrayList<String>()
        tips.add("Background")
        tips.add("From Photo")
        tips.add("Animation")
        return tips
    }

    fun initImagesData(): ArrayList<Int> {
        val images: ArrayList<Int> = ArrayList()
        images.add(R.mipmap.arrow)
        images.add(R.mipmap.arrow)
        images.add(R.mipmap.arrow)
        return images
    }

    fun res2String(context: Context, id: Int): String {
        val r = context.resources
        val uri = Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                    + r.getResourcePackageName(id) + "/"
                    + r.getResourceTypeName(id) + "/"
                    + r.getResourceEntryName(id)
        )
        return uri.toString()
    }

    fun getBackgroundData(): ArrayList<String> {
        val data = ArrayList<String>()
        for (index in 1..31) {
            if (index == 24)
                continue
            data.add(bg.replace("*", index.toString()))
        }
        return data
    }

    fun getResourceByFolder(
        context: Context,
        clazz: Class<*>,
        folderName: String,
        filter: String
    ): ArrayList<ResourceEntity> {
        val result = ArrayList<ResourceEntity>()
        for (field in clazz.fields) {
            val name = field.name
            if (name.startsWith(filter)) {
                val id = context.resources.getIdentifier(name, folderName, context.packageName)
                val entity = ResourceEntity(name, id)
                result.add(entity)
            }
        }
        return result
    }
}