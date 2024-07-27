package com.bigoat.android.view.sample

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bigoat.android.view.icon.IconView
import com.blankj.utilcode.constant.PermissionConstants
import com.blankj.utilcode.util.FileIOUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.PermissionUtils
import org.apache.fontbox.ttf.CmapSubtable
import org.apache.fontbox.ttf.CmapTable
import org.apache.fontbox.ttf.PostScriptTable
import org.apache.fontbox.ttf.TTFParser
import org.apache.fontbox.ttf.TTFTable
import org.apache.fontbox.ttf.TrueTypeFont
import java.util.function.Consumer

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        parseTTF(this, "gmd_outlined_light.ttf")

        val view:IconView = IconView(this)
        view.name
    }


    fun jumpToColorActivity(view: View) {
        startActivity(Intent(this, ColorActivity::class.java))
    }

    fun jumpToIconActivity(view: View) {
        Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, BackgroundActivity::class.java))
    }

    fun jumpToButtonActivity(view: View?) {
        startActivity(Intent(this, ButtonActivity::class.java))
    }

    fun jumpToImageActivity(view: View?) {
        startActivity(Intent(this, ImageActivity::class.java))
    }

    fun jumpToBadgeActivity(view: View) {
        startActivity(Intent(this, BadgeActivity::class.java))
    }

    fun clickJump(view: View) {
        Toast.makeText(this, "暂不支持", Toast.LENGTH_SHORT).show()
    }


    fun jumpToBackgroundActivity(view: View) {
        startActivity(Intent(this, BackgroundActivity::class.java))
    }

    fun jumpToTabbarActivity(view: View) {
        startActivity(Intent(this, TabbarActivity::class.java))
    }

    fun openActivity(view: View) {
        when (view.id) {
            R.id.icon -> startActivity(Intent(this, IconActivity::class.java))
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    fun parseTTF(context: Context, fileName: String?): Map<String, String> {
        val iconMap: Map<String, String> = HashMap()
        try {
            PermissionUtils.permission(PermissionConstants.STORAGE).request()
            com.blankj.utilcode.util.FileUtils.delete((cacheDir.path+"/icon.xml"))
            val inputStream = context.assets.open(fileName!!)
            val parser = TTFParser()
            val ttf: TrueTypeFont = parser.parse(inputStream)

            val tables: Collection<TTFTable> = ttf.getTables()

            for (table in tables) {
                if (table.getTag().equals("post")) {
                    val scriptTable: PostScriptTable = table as PostScriptTable
                    val names: Array<String> = scriptTable.getGlyphNames()
                    for (name in names) {
                        val content = "<string name=\"$name\">$name</string>"
                        val contentFill = "<string name=\"$name.fill\">$name.fill</string>"
                        Log.d("Font", content)
                        FileIOUtils.writeFileFromString(cacheDir.path+"/icon.xml", content + "\r\n", true)
                        FileIOUtils.writeFileFromString(cacheDir.path+"/icon.xml", contentFill + "\r\n", true)
                    }
                }

//                if (table.getTag().equals("cmap")) {
//                    val cmapTable: CmapTable = table as CmapTable
//                    val cmapSubtable: CmapSubtable = cmapTable.getCmaps().get(0)
//                    val codes: List<Int> = cmapSubtable.getCharCodes(262)
//                    codes.forEach(Consumer { code: Int? ->
//                        val unicode = String.format("\\u%04x", code)
//                        Log.d("Font", "code: $unicode")
//                    })
//                }
            }


            //            // 获取字体的字符映射表
//            CmapSubtable[] cmapSubtables = ttf.getCmap().getCmaps();
//            CmapSubtable cmap = null;
//
//            // 尝试找到一个有效的 cmap subtable
//            if (cmapSubtables != null) {
//                for (CmapSubtable subtable : cmapSubtables) {
//                    if (subtable != null) {
//                        cmap = subtable;
//
//                        if (cmap == null) {
//                            throw new IOException("No valid cmap subtable found in TTF file.");
//                        }
//
//                        // 遍历所有的字符映射
//                        for (int i = 0; i <= Character.MAX_VALUE; i++) {
//                            int glyphId = cmap.getGlyphId(i);
//                            if (glyphId != 0) {
//                                String unicode = String.format("\\u%04x", i);
//                                System.out.println("Font Icon unicode: " + unicode);
//                                iconMap.put("name", unicode);
//
//                            }
//                        }
//                    }
//                }
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return iconMap
    }
}