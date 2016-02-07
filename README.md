# 红包照片查看器
check wechat sns photos without sending lucky money / 让你不发红包就能查看朋友圈照片的Xposed插件

###  注意：建议自行编译及安装，避免安装不合法或者被修改过的 Xposed 插件。

关于 Xposed 请参考此处 [酷安-Xposed](http://coolapk.com/apk/de.robv.android.xposed.installer "酷安-Xposed")

## *如果您没有听说过 Xposed ，请勿尝试本项目*

## 更新日志
* 针对微信6.3.13做了一点小调整，修复了长按自己头像无法进入红包照片发送界面的问题。由于照片红包是随机给某些用户开通权限，所以你可能没有收到微信的提示，无法通过正常途径发送，但是可以通过此插件自由的发送红包照片。1点半的时候测试发送成功。 / 2.7.16
* 增加了红包照片的新入口，朋友圈首页，长按自己的头像即可进入红包照片发送页面，可正常选择照片和填写文字。（PS：非活动期间发送的红包照片会被腾讯拒绝，导致发送失败）/ 1.27.16

![image](https://github.com/NigelYao/WechatOriginalFile/blob/master/bin/entry_demo.gif?raw=true)

## 使用方法
1、编译并使用
* 安装并启用 Xposed 软件（需要 root，支持Android Marshmallow）
* git clone https://github.com/NigelYao/WechatOriginalFile.git
* 导入到 AS 中
* 运行并选择 Launch No Activity
* 重启即可在微信中查看照片（免发送红包，且需要最新版的微信）

2、直接安装使用
* 安装并启用 Xposed 软件
* 下载 bin 目录下的 apk 安装包，或者[点击此处](https://github.com/NigelYao/WechatOriginalFile/raw/master/bin/WechatOriginalPhoto.apk "点击此处")
* 安 装App 之后，系统会提示启用并重启插件
* 打开微信，点开红包照片，即可查看原图

## 使用效果
![images](https://pic2.zhimg.com/4f96466d8ec51dded98c60b5ebfd4d3d_b.png)
![images](https://pic1.zhimg.com/ff0ea901134b8470cde4c14685719a28_b.png)

## License
APACHE 2.0
