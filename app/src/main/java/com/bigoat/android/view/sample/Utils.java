package com.bigoat.android.view.sample;

import android.content.Context;
import android.util.Log;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.PermissionUtils;

import org.apache.fontbox.ttf.PostScriptTable;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.fontbox.ttf.TTFTable;
import org.apache.fontbox.ttf.TrueTypeFont;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static void parseTTFToFile(Context context, String fileName, String outPath) {
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            TTFParser parser = new TTFParser();
            TrueTypeFont ttf = parser.parse(inputStream);

            Collection<TTFTable> tables = ttf.getTables();

            FileIOUtils.writeFileFromString(outPath, "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\r\n" + "<resources>", false);

            for (TTFTable table : tables) {
                if (table instanceof PostScriptTable) {
                    PostScriptTable scriptTable = (PostScriptTable) table;
                    String[] names = scriptTable.getGlyphNames();
                    for (String name : names) {
                        String content = "<string name=\"" + name + "\">" + name + "</string>";
                        Log.d("parseTTF", content);
                        FileIOUtils.writeFileFromString(outPath, content + "\r\n", true);
                    }
                }
            }

            FileIOUtils.writeFileFromString(outPath, "</resources>", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
