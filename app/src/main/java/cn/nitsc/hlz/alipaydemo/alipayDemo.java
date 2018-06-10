package cn.nitsc.hlz.alipaydemo;

import java.lang.reflect.Field;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class alipayDemo implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws ClassNotFoundException {
        if (lpparam.packageName.equals("com.eg.android.AlipayGphone")){
            final Class<?> loginParam = XposedHelpers.findClass("com.ali.user.mobile.login.LoginParam",lpparam.classLoader);
            XposedHelpers.findAndHookMethod("com.ali.user.mobile.login.ui.BaseLoginActivity",
                    lpparam.classLoader,
                    "doUnifyLogin",
                    loginParam,
                    new XC_MethodHook() {
//                        @Override
//                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                            super.beforeHookedMethod(param);
//                            XposedBridge.log("Start Hooking");
//                            Field username = param.args[0].getClass().getField("loginAccount");
//                            Field password = param.args[0].getClass().getField("loginPassword");
//                            XposedBridge.log("Hooking Param:\n"+
//                                    "LoginAccount:"+username.get(param.args[0])+"\n"+
//                                    "LoginPassword:"+password.get(param.args[0])+"\n"+
//                                    "\n"
//                            );
//                            XposedBridge.log("Finish Hooking");
//                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("Start Hooking");
                            Field username = param.args[0].getClass().getField("loginAccount");
                            Field password = param.args[0].getClass().getField("loginPassword");
                            XposedBridge.log("Hooking Param:\n"+
                                    "LoginAccount:"+username.get(param.args[0])+"\n"+
                                    "LoginPassword:"+password.get(param.args[0])+"\n"+
                                    "\n"
                            );
                            XposedBridge.log("Finish Hooking");
                        }
                    }
            );
        }
    }
}
