package com.nigel.luckmoney.wechatoriginalphoto;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by Nigel on 2016/1/26.
 */
public class CheckOriginalPhoto implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final LoadPackageParam loadPackageParam) throws Throwable {

        if (!loadPackageParam.packageName.contains("tencent.mm")) {
            return;
        }

        try {

            findAndHookMethod("com.tencent.mm.plugin.sns.lucky.ui.LuckyRevealImageView", loadPackageParam.classLoader, "getBlurBitmapFilePath", new XC_MethodReplacement() {
                @Override
                protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                    return XposedHelpers.callMethod(param.thisObject,"getOriginBitmapFilePath");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}