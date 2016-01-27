package com.nigel.luckmoney.wechatoriginalphoto;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by Nigel on 2016/1/26.
 */
public class CheckOriginalPhoto implements IXposedHookLoadPackage {

    Context context;

    @Override
    public void handleLoadPackage(final LoadPackageParam loadPackageParam) throws Throwable {

        if (!loadPackageParam.packageName.contains("tencent.mm")) {
            return;
        }

        try {

            /* 替换模糊方法为原始文件方法 */
            findAndHookMethod("com.tencent.mm.plugin.sns.lucky.ui.LuckyRevealImageView", loadPackageParam.classLoader, "getBlurBitmapFilePath", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return XposedHelpers.callMethod(param.thisObject,"getOriginBitmapFilePath");
                }
            });

            /* 增加照片红包的入口 */
            findAndHookMethod("com.tencent.mm.plugin.sns.ui.SnsHeader", loadPackageParam.classLoader, "init", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("启动红包照片页面");
                    /* 长按方法。进入红包发送页面 */
                    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener(){
                        @Override
                        public boolean onLongClick(View v) {
                            Intent intent = new Intent();
                            context = (Context) XposedHelpers.callStaticMethod(findClass("com.tencent.mm.sdk.platformtools.y", loadPackageParam.classLoader), "getContext");
                            intent.setClassName("com.tencent.mm", "com.tencent.mm.plugin.sns.lucky.ui.SnsLuckyNewUploadUI");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            return false;
                        }
                    };
                    /* 找到头像并设置监听 */
                    Object hec = XposedHelpers.getObjectField(param.thisObject, "gXo");
                    Object avatar = XposedHelpers.getObjectField(hec, "cls");
                    XposedHelpers.callMethod(avatar, "setOnLongClickListener", onLongClickListener);
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}