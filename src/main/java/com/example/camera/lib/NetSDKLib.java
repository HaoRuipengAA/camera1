package com.example.camera.lib;

import com.sun.jna.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public interface NetSDKLib extends Library{

    NetSDKLib NETSDK_INSTANCE = (NetSDKLib)Native.loadLibrary(Utils.getLoadLibrary("dhnetsdk"), NetSDKLib.class);

    /************************************************************************
     ** 常量定义
     ***********************************************************************/
    public static final int NET_SERIALNO_LEN                      = 48;             // 设备序列号字符长度

    // 错误类型代号，对应CLIENT_GetLastError接口的返回值, 十进制
    public static final int NET_NOERROR                         =  0;               // 没有错误
    public static final int NET_ERROR                           = -1;               // 未知错误
    public static final int NET_SYSTEM_ERROR                    = (0x80000000|1);   // Windows系统出错
    public static final int NET_NETWORK_ERROR                   = (0x80000000|2);   // 网络错误，可能是因为网络超时
    public static final int NET_DEV_VER_NOMATCH                 = (0x80000000|3);   // 设备协议不匹配
    public static final int NET_INVALID_HANDLE                  = (0x80000000|4);   // 句柄无效
    public static final int NET_OPEN_CHANNEL_ERROR              = (0x80000000|5);   // 打开通道失败
    public static final int NET_CLOSE_CHANNEL_ERROR             = (0x80000000|6);   // 关闭通道失败
    public static final int NET_ILLEGAL_PARAM                   = (0x80000000|7);   // 用户参数不合法
    public static final int NET_SDK_INIT_ERROR                  = (0x80000000|8);   // SDK初始化出错
    public static final int NET_SDK_UNINIT_ERROR                = (0x80000000|9);   // SDK清理出错
    public static final int NET_RENDER_OPEN_ERROR               = (0x80000000|10);  // 申请render资源出错
    public static final int NET_DEC_OPEN_ERROR                  = (0x80000000|11);  // 打开解码库出错
    public static final int NET_DEC_CLOSE_ERROR                 = (0x80000000|12);  // 关闭解码库出错
    public static final int NET_MULTIPLAY_NOCHANNEL             = (0x80000000|13);  // 多画面预览中检测到通道数为0
    public static final int NET_TALK_INIT_ERROR                 = (0x80000000|14);  // 录音库初始化失败
    public static final int NET_TALK_NOT_INIT                   = (0x80000000|15);  // 录音库未经初始化
    public static final int NET_TALK_SENDDATA_ERROR             = (0x80000000|16);  // 发送音频数据出错
    public static final int NET_REAL_ALREADY_SAVING             = (0x80000000|17);  // 实时数据已经处于保存状态
    public static final int NET_NOT_SAVING                      = (0x80000000|18);  // 未保存实时数据
    public static final int NET_OPEN_FILE_ERROR                 = (0x80000000|19);  // 打开文件出错
    public static final int NET_PTZ_SET_TIMER_ERROR             = (0x80000000|20);  // 启动云台控制定时器失败
    public static final int NET_RETURN_DATA_ERROR               = (0x80000000|21);  // 对返回数据的校验出错
    public static final int NET_INSUFFICIENT_BUFFER             = (0x80000000|22);  // 没有足够的缓存
    public static final int NET_NOT_SUPPORTED                   = (0x80000000|23);  // 当前SDK未支持该功能
    public static final int NET_NO_RECORD_FOUND                 = (0x80000000|24);  // 查询不到录像
    public static final int NET_NOT_AUTHORIZED                  = (0x80000000|25);  // 无操作权限
    public static final int NET_NOT_NOW                         = (0x80000000|26);  // 暂时无法执行
    public static final int NET_NO_TALK_CHANNEL                 = (0x80000000|27);  // 未发现对讲通道
    public static final int NET_NO_AUDIO                        = (0x80000000|28);  // 未发现音频
    public static final int NET_NO_INIT                         = (0x80000000|29);  // 网络SDK未经初始化
    public static final int NET_DOWNLOAD_END                    = (0x80000000|30);  // 下载已结束
    public static final int NET_EMPTY_LIST                      = (0x80000000|31);  // 查询结果为空
    public static final int NET_ERROR_GETCFG_SYSATTR            = (0x80000000|32);  // 获取系统属性配置失败
    public static final int NET_ERROR_GETCFG_SERIAL             = (0x80000000|33);  // 获取序列号失败
    public static final int NET_ERROR_GETCFG_GENERAL            = (0x80000000|34);  // 获取常规属性失败
    public static final int NET_ERROR_GETCFG_DSPCAP             = (0x80000000|35);  // 获取DSP能力描述失败
    public static final int NET_ERROR_GETCFG_NETCFG             = (0x80000000|36);  // 获取网络配置失败
    public static final int NET_ERROR_GETCFG_CHANNAME           = (0x80000000|37);  // 获取通道名称失败
    public static final int NET_ERROR_GETCFG_VIDEO              = (0x80000000|38);  // 获取视频属性失败
    public static final int NET_ERROR_GETCFG_RECORD             = (0x80000000|39);  // 获取录象配置失败
    public static final int NET_ERROR_GETCFG_PRONAME            = (0x80000000|40);  // 获取解码器协议名称失败
    public static final int NET_ERROR_GETCFG_FUNCNAME           = (0x80000000|41);  // 获取232串口功能名称失败
    public static final int NET_ERROR_GETCFG_485DECODER         = (0x80000000|42);  // 获取解码器属性失败
    public static final int NET_ERROR_GETCFG_232COM             = (0x80000000|43);  // 获取232串口配置失败
    public static final int NET_ERROR_GETCFG_ALARMIN            = (0x80000000|44);  // 获取外部报警输入配置失败
    public static final int NET_ERROR_GETCFG_ALARMDET           = (0x80000000|45);  // 获取动态检测报警失败
    public static final int NET_ERROR_GETCFG_SYSTIME            = (0x80000000|46);  // 获取设备时间失败
    public static final int NET_ERROR_GETCFG_PREVIEW            = (0x80000000|47);  // 获取预览参数失败
    public static final int NET_ERROR_GETCFG_AUTOMT             = (0x80000000|48);  // 获取自动维护配置失败
    public static final int NET_ERROR_GETCFG_VIDEOMTRX          = (0x80000000|49);  // 获取视频矩阵配置失败
    public static final int NET_ERROR_GETCFG_COVER              = (0x80000000|50);  // 获取区域遮挡配置失败
    public static final int NET_ERROR_GETCFG_WATERMAKE          = (0x80000000|51);  // 获取图象水印配置失败
    public static final int NET_ERROR_GETCFG_MULTICAST          = (0x80000000|52);  // 获取配置失败位置：组播端口按通道配置
    public static final int NET_ERROR_SETCFG_GENERAL            = (0x80000000|55);  // 修改常规属性失败
    public static final int NET_ERROR_SETCFG_NETCFG             = (0x80000000|56);  // 修改网络配置失败
    public static final int NET_ERROR_SETCFG_CHANNAME           = (0x80000000|57);  // 修改通道名称失败
    public static final int NET_ERROR_SETCFG_VIDEO              = (0x80000000|58);  // 修改视频属性失败
    public static final int NET_ERROR_SETCFG_RECORD             = (0x80000000|59);  // 修改录象配置失败
    public static final int NET_ERROR_SETCFG_485DECODER         = (0x80000000|60);  // 修改解码器属性失败
    public static final int NET_ERROR_SETCFG_232COM             = (0x80000000|61);  // 修改232串口配置失败
    public static final int NET_ERROR_SETCFG_ALARMIN            = (0x80000000|62);  // 修改外部输入报警配置失败
    public static final int NET_ERROR_SETCFG_ALARMDET           = (0x80000000|63);  // 修改动态检测报警配置失败
    public static final int NET_ERROR_SETCFG_SYSTIME            = (0x80000000|64);  // 修改设备时间失败
    public static final int NET_ERROR_SETCFG_PREVIEW            = (0x80000000|65);  // 修改预览参数失败
    public static final int NET_ERROR_SETCFG_AUTOMT             = (0x80000000|66);  // 修改自动维护配置失败
    public static final int NET_ERROR_SETCFG_VIDEOMTRX          = (0x80000000|67);  // 修改视频矩阵配置失败
    public static final int NET_ERROR_SETCFG_COVER              = (0x80000000|68);  // 修改区域遮挡配置失败
    public static final int NET_ERROR_SETCFG_WATERMAKE          = (0x80000000|69);  // 修改图象水印配置失败
    public static final int NET_ERROR_SETCFG_WLAN               = (0x80000000|70);  // 修改无线网络信息失败
    public static final int NET_ERROR_SETCFG_WLANDEV            = (0x80000000|71);  // 选择无线网络设备失败
    public static final int NET_ERROR_SETCFG_REGISTER           = (0x80000000|72);  // 修改主动注册参数配置失败
    public static final int NET_ERROR_SETCFG_CAMERA             = (0x80000000|73);  // 修改摄像头属性配置失败
    public static final int NET_ERROR_SETCFG_INFRARED           = (0x80000000|74);  // 修改红外报警配置失败
    public static final int NET_ERROR_SETCFG_SOUNDALARM         = (0x80000000|75);  // 修改音频报警配置失败
    public static final int NET_ERROR_SETCFG_STORAGE            = (0x80000000|76);  // 修改存储位置配置失败
    public static final int NET_AUDIOENCODE_NOTINIT             = (0x80000000|77);  // 音频编码接口没有成功初始化
    public static final int NET_DATA_TOOLONGH                   = (0x80000000|78);  // 数据过长
    public static final int NET_UNSUPPORTED                     = (0x80000000|79);  // 设备不支持该操作
    public static final int NET_DEVICE_BUSY                     = (0x80000000|80);  // 设备资源不足
    public static final int NET_SERVER_STARTED                  = (0x80000000|81);  // 服务器已经启动
    public static final int NET_SERVER_STOPPED                  = (0x80000000|82);  // 服务器尚未成功启动
    public static final int NET_LISTER_INCORRECT_SERIAL         = (0x80000000|83);  // 输入序列号有误
    public static final int NET_QUERY_DISKINFO_FAILED           = (0x80000000|84);  // 获取硬盘信息失败
    public static final int NET_ERROR_GETCFG_SESSION            = (0x80000000|85);  // 获取连接Session信息
    public static final int NET_USER_FLASEPWD_TRYTIME           = (0x80000000|86);  // 输入密码错误超过限制次数
    public static final int NET_LOGIN_ERROR_PASSWORD            = (0x80000000|100); // 密码不正确
    public static final int NET_LOGIN_ERROR_USER                = (0x80000000|101); // 帐户不存在
    public static final int NET_LOGIN_ERROR_TIMEOUT             = (0x80000000|102); // 等待登录返回超时
    public static final int NET_LOGIN_ERROR_RELOGGIN            = (0x80000000|103); // 帐号已登录
    public static final int NET_LOGIN_ERROR_LOCKED              = (0x80000000|104); // 帐号已被锁定
    public static final int NET_LOGIN_ERROR_BLACKLIST           = (0x80000000|105); // 帐号已被列为黑名单
    public static final int NET_LOGIN_ERROR_BUSY                = (0x80000000|106); // 资源不足，系统忙
    public static final int NET_LOGIN_ERROR_CONNECT             = (0x80000000|107); // 登录设备超时，请检查网络并重试
    public static final int NET_LOGIN_ERROR_NETWORK             = (0x80000000|108); // 网络连接失败
    public static final int NET_LOGIN_ERROR_SUBCONNECT          = (0x80000000|109); // 登录设备成功，但无法创建视频通道，请检查网络状况
    public static final int NET_LOGIN_ERROR_MAXCONNECT          = (0x80000000|110); // 超过最大连接数
    public static final int NET_LOGIN_ERROR_PROTOCOL3_ONLY      = (0x80000000|111); // 只支持3代协议
    public static final int NET_LOGIN_ERROR_UKEY_LOST           = (0x80000000|112); // 未插入U盾或U盾信息错误
    public static final int NET_LOGIN_ERROR_NO_AUTHORIZED       = (0x80000000|113); // 客户端IP地址没有登录权限
    public static final int NET_LOGIN_ERROR_USER_OR_PASSOWRD    = (0x80000000|117); // 账号或密码错误
    public static final int NET_LOGIN_ERROR_DEVICE_NOT_INIT		= (0x80000000|118);	// 设备尚未初始化，不能登录，请先初始化设备
    public static final int NET_RENDER_SOUND_ON_ERROR           = (0x80000000|120); // Render库打开音频出错
    public static final int NET_RENDER_SOUND_OFF_ERROR          = (0x80000000|121); // Render库关闭音频出错
    public static final int NET_RENDER_SET_VOLUME_ERROR         = (0x80000000|122); // Render库控制音量出错
    public static final int NET_RENDER_ADJUST_ERROR             = (0x80000000|123); // Render库设置画面参数出错
    public static final int NET_RENDER_PAUSE_ERROR              = (0x80000000|124); // Render库暂停播放出错
    public static final int NET_RENDER_SNAP_ERROR               = (0x80000000|125); // Render库抓图出错
    public static final int NET_RENDER_STEP_ERROR               = (0x80000000|126); // Render库步进出错
    public static final int NET_RENDER_FRAMERATE_ERROR          = (0x80000000|127); // Render库设置帧率出错
    public static final int NET_RENDER_DISPLAYREGION_ERROR      = (0x80000000|128); // Render库设置显示区域出错
    public static final int NET_RENDER_GETOSDTIME_ERROR         = (0x80000000|129); // Render库获取当前播放时间出错
    public static final int NET_GROUP_EXIST                     = (0x80000000|140); // 组名已存在
    public static final int NET_GROUP_NOEXIST                   = (0x80000000|141); // 组名不存在
    public static final int NET_GROUP_RIGHTOVER                 = (0x80000000|142); // 组的权限超出权限列表范围
    public static final int NET_GROUP_HAVEUSER                  = (0x80000000|143); // 组下有用户，不能删除
    public static final int NET_GROUP_RIGHTUSE                  = (0x80000000|144); // 组的某个权限被用户使用，不能删除
    public static final int NET_GROUP_SAMENAME                  = (0x80000000|145); // 新组名同已有组名重复
    public static final int NET_USER_EXIST                      = (0x80000000|146); // 用户已存在
    public static final int NET_USER_NOEXIST                    = (0x80000000|147); // 用户不存在
    public static final int NET_USER_RIGHTOVER                  = (0x80000000|148); // 用户权限超出组权限
    public static final int NET_USER_PWD                        = (0x80000000|149); // 保留帐号，不容许修改密码
    public static final int NET_USER_FLASEPWD                   = (0x80000000|150); // 密码不正确
    public static final int NET_USER_NOMATCHING                 = (0x80000000|151); // 密码不匹配
    public static final int NET_USER_INUSE                      = (0x80000000|152); // 账号正在使用中
    public static final int NET_ERROR_GETCFG_ETHERNET           = (0x80000000|300); // 获取网卡配置失败
    public static final int NET_ERROR_GETCFG_WLAN               = (0x80000000|301); // 获取无线网络信息失败
    public static final int NET_ERROR_GETCFG_WLANDEV            = (0x80000000|302); // 获取无线网络设备失败
    public static final int NET_ERROR_GETCFG_REGISTER           = (0x80000000|303); // 获取主动注册参数失败
    public static final int NET_ERROR_GETCFG_CAMERA             = (0x80000000|304); // 获取摄像头属性失败
    public static final int NET_ERROR_GETCFG_INFRARED           = (0x80000000|305); // 获取红外报警配置失败
    public static final int NET_ERROR_GETCFG_SOUNDALARM         = (0x80000000|306); // 获取音频报警配置失败
    public static final int NET_ERROR_GETCFG_STORAGE            = (0x80000000|307); // 获取存储位置配置失败
    public static final int NET_ERROR_GETCFG_MAIL               = (0x80000000|308); // 获取邮件配置失败
    public static final int NET_CONFIG_DEVBUSY                  = (0x80000000|309); // 暂时无法设置
    public static final int NET_CONFIG_DATAILLEGAL              = (0x80000000|310); // 配置数据不合法
    public static final int NET_ERROR_GETCFG_DST                = (0x80000000|311); // 获取夏令时配置失败
    public static final int NET_ERROR_SETCFG_DST                = (0x80000000|312); // 设置夏令时配置失败
    public static final int NET_ERROR_GETCFG_VIDEO_OSD          = (0x80000000|313); // 获取视频OSD叠加配置失败
    public static final int NET_ERROR_SETCFG_VIDEO_OSD          = (0x80000000|314); // 设置视频OSD叠加配置失败
    public static final int NET_ERROR_GETCFG_GPRSCDMA           = (0x80000000|315); // 获取CDMA\GPRS网络配置失败
    public static final int NET_ERROR_SETCFG_GPRSCDMA           = (0x80000000|316); // 设置CDMA\GPRS网络配置失败
    public static final int NET_ERROR_GETCFG_IPFILTER           = (0x80000000|317); // 获取IP过滤配置失败
    public static final int NET_ERROR_SETCFG_IPFILTER           = (0x80000000|318); // 设置IP过滤配置失败
    public static final int NET_ERROR_GETCFG_TALKENCODE         = (0x80000000|319); // 获取语音对讲编码配置失败
    public static final int NET_ERROR_SETCFG_TALKENCODE         = (0x80000000|320); // 设置语音对讲编码配置失败
    public static final int NET_ERROR_GETCFG_RECORDLEN          = (0x80000000|321); // 获取录像打包长度配置失败
    public static final int NET_ERROR_SETCFG_RECORDLEN          = (0x80000000|322); // 设置录像打包长度配置失败
    public static final int NET_DONT_SUPPORT_SUBAREA            = (0x80000000|323); // 不支持网络硬盘分区
    public static final int NET_ERROR_GET_AUTOREGSERVER         = (0x80000000|324); // 获取设备上主动注册服务器信息失败
    public static final int NET_ERROR_CONTROL_AUTOREGISTER      = (0x80000000|325); // 主动注册重定向注册错误
    public static final int NET_ERROR_DISCONNECT_AUTOREGISTER   = (0x80000000|326); // 断开主动注册服务器错误
    public static final int NET_ERROR_GETCFG_MMS                = (0x80000000|327); // 获取mms配置失败
    public static final int NET_ERROR_SETCFG_MMS                = (0x80000000|328); // 设置mms配置失败
    public static final int NET_ERROR_GETCFG_SMSACTIVATION      = (0x80000000|329); // 获取短信激活无线连接配置失败
    public static final int NET_ERROR_SETCFG_SMSACTIVATION      = (0x80000000|330); // 设置短信激活无线连接配置失败
    public static final int NET_ERROR_GETCFG_DIALINACTIVATION   = (0x80000000|331); // 获取拨号激活无线连接配置失败
    public static final int NET_ERROR_SETCFG_DIALINACTIVATION   = (0x80000000|332); // 设置拨号激活无线连接配置失败
    public static final int NET_ERROR_GETCFG_VIDEOOUT           = (0x80000000|333); // 查询视频输出参数配置失败
    public static final int NET_ERROR_SETCFG_VIDEOOUT           = (0x80000000|334); // 设置视频输出参数配置失败
    public static final int NET_ERROR_GETCFG_OSDENABLE          = (0x80000000|335); // 获取osd叠加使能配置失败
    public static final int NET_ERROR_SETCFG_OSDENABLE          = (0x80000000|336); // 设置osd叠加使能配置失败
    public static final int NET_ERROR_SETCFG_ENCODERINFO        = (0x80000000|337); // 设置数字通道前端编码接入配置失败
    public static final int NET_ERROR_GETCFG_TVADJUST           = (0x80000000|338); // 获取TV调节配置失败
    public static final int NET_ERROR_SETCFG_TVADJUST           = (0x80000000|339); // 设置TV调节配置失败
    public static final int NET_ERROR_CONNECT_FAILED            = (0x80000000|340); // 请求建立连接失败
    public static final int NET_ERROR_SETCFG_BURNFILE           = (0x80000000|341); // 请求刻录文件上传失败
    public static final int NET_ERROR_SNIFFER_GETCFG            = (0x80000000|342); // 获取抓包配置信息失败
    public static final int NET_ERROR_SNIFFER_SETCFG            = (0x80000000|343); // 设置抓包配置信息失败
    public static final int NET_ERROR_DOWNLOADRATE_GETCFG       = (0x80000000|344); // 查询下载限制信息失败
    public static final int NET_ERROR_DOWNLOADRATE_SETCFG       = (0x80000000|345); // 设置下载限制信息失败
    public static final int NET_ERROR_SEARCH_TRANSCOM           = (0x80000000|346); // 查询串口参数失败
    public static final int NET_ERROR_GETCFG_POINT              = (0x80000000|347); // 获取预制点信息错误
    public static final int NET_ERROR_SETCFG_POINT              = (0x80000000|348); // 设置预制点信息错误
    public static final int NET_SDK_LOGOUT_ERROR                = (0x80000000|349); // SDK没有正常登出设备
    public static final int NET_ERROR_GET_VEHICLE_CFG           = (0x80000000|350); // 获取车载配置失败
    public static final int NET_ERROR_SET_VEHICLE_CFG           = (0x80000000|351); // 设置车载配置失败
    public static final int NET_ERROR_GET_ATM_OVERLAY_CFG       = (0x80000000|352); // 获取atm叠加配置失败
    public static final int NET_ERROR_SET_ATM_OVERLAY_CFG       = (0x80000000|353); // 设置atm叠加配置失败
    public static final int NET_ERROR_GET_ATM_OVERLAY_ABILITY   = (0x80000000|354); // 获取atm叠加能力失败
    public static final int NET_ERROR_GET_DECODER_TOUR_CFG      = (0x80000000|355); // 获取解码器解码轮巡配置失败
    public static final int NET_ERROR_SET_DECODER_TOUR_CFG      = (0x80000000|356); // 设置解码器解码轮巡配置失败
    public static final int NET_ERROR_CTRL_DECODER_TOUR         = (0x80000000|357); // 控制解码器解码轮巡失败
    public static final int NET_GROUP_OVERSUPPORTNUM            = (0x80000000|358); // 超出设备支持最大用户组数目
    public static final int NET_USER_OVERSUPPORTNUM             = (0x80000000|359); // 超出设备支持最大用户数目
    public static final int NET_ERROR_GET_SIP_CFG               = (0x80000000|368); // 获取SIP配置失败
    public static final int NET_ERROR_SET_SIP_CFG               = (0x80000000|369); // 设置SIP配置失败
    public static final int NET_ERROR_GET_SIP_ABILITY           = (0x80000000|370); // 获取SIP能力失败
    public static final int NET_ERROR_GET_WIFI_AP_CFG           = (0x80000000|371); // 获取WIFI ap配置失败
    public static final int NET_ERROR_SET_WIFI_AP_CFG           = (0x80000000|372); // 设置WIFI ap配置失败
    public static final int NET_ERROR_GET_DECODE_POLICY         = (0x80000000|373); // 获取解码策略配置失败
    public static final int NET_ERROR_SET_DECODE_POLICY         = (0x80000000|374); // 设置解码策略配置失败
    public static final int NET_ERROR_TALK_REJECT               = (0x80000000|375); // 拒绝对讲
    public static final int NET_ERROR_TALK_OPENED               = (0x80000000|376); // 对讲被其他客户端打开
    public static final int NET_ERROR_TALK_RESOURCE_CONFLICIT   = (0x80000000|377); // 资源冲突
    public static final int NET_ERROR_TALK_UNSUPPORTED_ENCODE   = (0x80000000|378); // 不支持的语音编码格式
    public static final int NET_ERROR_TALK_RIGHTLESS            = (0x80000000|379); // 无权限
    public static final int NET_ERROR_TALK_FAILED               = (0x80000000|380); // 请求对讲失败
    public static final int NET_ERROR_GET_MACHINE_CFG           = (0x80000000|381); // 获取机器相关配置失败
    public static final int NET_ERROR_SET_MACHINE_CFG           = (0x80000000|382); // 设置机器相关配置失败
    public static final int NET_ERROR_GET_DATA_FAILED           = (0x80000000|383); // 设备无法获取当前请求数据
    public static final int NET_ERROR_MAC_VALIDATE_FAILED       = (0x80000000|384); // MAC地址验证失败
    public static final int NET_ERROR_GET_INSTANCE              = (0x80000000|385); // 获取服务器实例失败
    public static final int NET_ERROR_JSON_REQUEST              = (0x80000000|386); // 生成的json字符串错误
    public static final int NET_ERROR_JSON_RESPONSE             = (0x80000000|387); // 响应的json字符串错误
    public static final int NET_ERROR_VERSION_HIGHER            = (0x80000000|388); // 协议版本低于当前使用的版本
    public static final int NET_SPARE_NO_CAPACITY               = (0x80000000|389); // 设备操作失败, 容量不足
    public static final int NET_ERROR_SOURCE_IN_USE             = (0x80000000|390); // 显示源被其他输出占用
    public static final int NET_ERROR_REAVE                     = (0x80000000|391); // 高级用户抢占低级用户资源
    public static final int NET_ERROR_NETFORBID                 = (0x80000000|392); // 禁止入网
    public static final int NET_ERROR_GETCFG_MACFILTER          = (0x80000000|393); // 获取MAC过滤配置失败
    public static final int NET_ERROR_SETCFG_MACFILTER          = (0x80000000|394); // 设置MAC过滤配置失败
    public static final int NET_ERROR_GETCFG_IPMACFILTER        = (0x80000000|395); // 获取IP/MAC过滤配置失败
    public static final int NET_ERROR_SETCFG_IPMACFILTER        = (0x80000000|396); // 设置IP/MAC过滤配置失败
    public static final int NET_ERROR_OPERATION_OVERTIME        = (0x80000000|397); // 当前操作超时
    public static final int NET_ERROR_SENIOR_VALIDATE_FAILED    = (0x80000000|398); // 高级校验失败
    public static final int NET_ERROR_DEVICE_ID_NOT_EXIST       = (0x80000000|399); // 设备ID不存在
    public static final int NET_ERROR_UNSUPPORTED               = (0x80000000|400); // 不支持当前操作
    public static final int NET_ERROR_PROXY_DLLLOAD             = (0x80000000|401); // 代理库加载失败
    public static final int NET_ERROR_PROXY_ILLEGAL_PARAM       = (0x80000000|402); // 代理用户参数不合法
    public static final int NET_ERROR_PROXY_INVALID_HANDLE      = (0x80000000|403); // 代理句柄无效
    public static final int NET_ERROR_PROXY_LOGIN_DEVICE_ERROR 	= (0x80000000|404); // 代理登入前端设备失败
    public static final int NET_ERROR_PROXY_START_SERVER_ERROR  = (0x80000000|405); // 启动代理服务失败
    public static final int NET_ERROR_SPEAK_FAILED              = (0x80000000|406); // 请求喊话失败
    public static final int NET_ERROR_NOT_SUPPORT_F6            = (0x80000000|407); // 设备不支持此F6接口调用
    public static final int NET_ERROR_CD_UNREADY                = (0x80000000|408); // 光盘未就绪
    public static final int NET_ERROR_DIR_NOT_EXIST             = (0x80000000|409); // 目录不存在
    public static final int NET_ERROR_UNSUPPORTED_SPLIT_MODE    = (0x80000000|410); // 设备不支持的分割模式
    public static final int NET_ERROR_OPEN_WND_PARAM            = (0x80000000|411); // 开窗参数不合法
    public static final int NET_ERROR_LIMITED_WND_COUNT         = (0x80000000|412); // 开窗数量超过限制
    public static final int NET_ERROR_UNMATCHED_REQUEST         = (0x80000000|413); // 请求命令与当前模式不匹配
    public static final int NET_RENDER_ENABLELARGEPICADJUSTMENT_ERROR = (0x80000000|414); // Render库启用高清图像内部调整策略出错
    public static final int NET_ERROR_UPGRADE_FAILED            = (0x80000000|415); // 设备升级失败
    public static final int NET_ERROR_NO_TARGET_DEVICE          = (0x80000000|416); // 找不到目标设备
    public static final int NET_ERROR_NO_VERIFY_DEVICE          = (0x80000000|417); // 找不到验证设备
    public static final int NET_ERROR_CASCADE_RIGHTLESS         = (0x80000000|418); // 无级联权限
    public static final int NET_ERROR_LOW_PRIORITY              = (0x80000000|419); // 低优先级
    public static final int NET_ERROR_REMOTE_REQUEST_TIMEOUT    = (0x80000000|420); // 远程设备请求超时
    public static final int NET_ERROR_LIMITED_INPUT_SOURCE      = (0x80000000|421); // 输入源超出最大路数限制
    public static final int NET_ERROR_SET_LOG_PRINT_INFO        = (0x80000000|422); // 设置日志打印失败
    public static final int NET_ERROR_PARAM_DWSIZE_ERROR        = (0x80000000|423); // 入参的dwsize字段出错
    public static final int NET_ERROR_LIMITED_MONITORWALL_COUNT = (0x80000000|424); // 电视墙数量超过上限
    public static final int NET_ERROR_PART_PROCESS_FAILED       = (0x80000000|425); // 部分过程执行失败
    public static final int NET_ERROR_TARGET_NOT_SUPPORT        = (0x80000000|426); // 该功能不支持转发
    public static final int NET_ERROR_VISITE_FILE               = (0x80000000|510); // 访问文件失败
    public static final int NET_ERROR_DEVICE_STATUS_BUSY        = (0x80000000|511); // 设备忙
    public static final int NET_USER_PWD_NOT_AUTHORIZED         = (0x80000000|512); // 修改密码无权限
    public static final int NET_USER_PWD_NOT_STRONG             = (0x80000000|513); // 密码强度不够
    public static final int NET_ERROR_NO_SUCH_CONFIG            = (0x80000000|514); // 没有对应的配置
    public static final int NET_ERROR_AUDIO_RECORD_FAILED       = (0x80000000|515); // 录音失败
    public static final int NET_ERROR_SEND_DATA_FAILED          = (0x80000000|516); // 数据发送失败
    public static final int NET_ERROR_OBSOLESCENT_INTERFACE     = (0x80000000|517); // 废弃接口
    public static final int NET_ERROR_INSUFFICIENT_INTERAL_BUF  = (0x80000000|518); // 内部缓冲不足
    public static final int NET_ERROR_NEED_ENCRYPTION_PASSWORD  = (0x80000000|519); // 修改设备ip时,需要校验密码
    public static final int NET_ERROR_NOSUPPORT_RECORD          = (0x80000000|520); // 设备不支持此记录集
    public static final int NET_ERROR_SERIALIZE_ERROR           = (0x80000000|1010);// 数据序列化错误
    public static final int NET_ERROR_DESERIALIZE_ERROR         = (0x80000000|1011);// 数据反序列化错误
    public static final int NET_ERROR_LOWRATEWPAN_ID_EXISTED    = (0x80000000|1012);// 该无线ID已存在
    public static final int NET_ERROR_LOWRATEWPAN_ID_LIMIT      = (0x80000000|1013);// 无线ID数量已超限
    public static final int NET_ERROR_LOWRATEWPAN_ID_ABNORMAL   = (0x80000000|1014);// 无线异常添加
    public static final int NET_ERROR_ENCRYPT                   = (0x80000000|1015);// 加密数据失败
    public static final int NET_ERROR_PWD_ILLEGAL               = (0x80000000|1016);// 新密码不合规范
    public static final int NET_ERROR_DEVICE_ALREADY_INIT       = (0x80000000|1017);// 设备已经初始化
    public static final int NET_ERROR_SECURITY_CODE             = (0x80000000|1018);// 安全码错误
    public static final int NET_ERROR_SECURITY_CODE_TIMEOUT     = (0x80000000|1019);// 安全码超出有效期
    public static final int NET_ERROR_GET_PWD_SPECI             = (0x80000000|1020);// 获取密码规范失败
    public static final int NET_ERROR_NO_AUTHORITY_OF_OPERATION = (0x80000000|1021);// 无权限进行该操作
    public static final int NET_ERROR_DECRYPT                   = (0x80000000|1022);// 解密数据失败
    public static final int NET_ERROR_2D_CODE                   = (0x80000000|1023);// 2D code校验失败
    public static final int NET_ERROR_INVALID_REQUEST           = (0x80000000|1024);// 非法的RPC请求
    public static final int NET_ERROR_PWD_RESET_DISABLE			= (0x80000000|1025);// 密码重置功能已关闭
    public static final int NET_ERROR_PLAY_PRIVATE_DATA         = (0x80000000|1026);// 显示私有数据，比如规则框等失败
    public static final int NET_ERROR_ROBOT_OPERATE_FAILED      = (0x80000000|1027);// 机器人操作失败
    public static final int NET_ERROR_PHOTOSIZE_EXCEEDSLIMIT    = (0x80000000|1028);// 图片大小超限
    public static final int NET_ERROR_USERID_INVALID            = (0x80000000|1029);// 用户ID不存在
    public static final int NET_ERROR_EXTRACTFEATURE_FAILED     = (0x80000000|1030);// 照片特征值提取失败
    public static final int NET_ERROR_PHOTO_EXIST               = (0x80000000|1031);// 照片已存在
    public static final int NET_ERROR_PHOTO_OVERFLOW            = (0x80000000|1032);// 照片数量超过上限
    public static final int NET_ERROR_CHANNEL_ALREADY_OPENED	= (0x80000000|1033);// 通道已经打开
    public static final int NET_ERROR_CREATE_SOCKET				= (0x80000000|1034);// 创建套接字失败
    public static final int NET_ERROR_CHANNEL_NUM				= (0x80000000|1035);// 通道号错误
//    public static final int NET_ERROR_PHOTO_FORMAT				= (0x80000000|1036);// 图片格式错误
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_INTERNAL_ERROR = (0x80000000|1037);	  // 内部错误(比如：相关硬件问题，获取公钥失败，内部接口调用失败，写文件失败等等)
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_GET_ID_FAILED	 = (0x80000000|1038);	  // 获取设备ID失败
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_IMPORT_ILLEGAL = (0x80000000|1039);	  // 证书文件非法(格式不支持或者不是证书文件)
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_SN_ERROR		 = (0x80000000|1040);	  // 证书sn重复或错误或不规范
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_COMMON_NAME_ILLEGAL = (0x80000000|1041);// 证书commonName非法(本地设备证书与系统中的不匹配devid_cryptoID,或者对端的不符合规则(devid_crytoID))
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_NO_ROOT_CERT	 = (0x80000000|1042); 	  // 根证书未导入或不存在
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_CERT_REVOKED	 = (0x80000000|1043);	  // 证书被吊销
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_CERT_INVALID	 = (0x80000000|1044);	  // 证书不可用或未生效或已过期
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_CERT_ERROR_SIGN = (0x80000000|1045);	  // 证书签名不匹配
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_COUNTS_UPPER_LIMIT = (0x80000000|1046); // 超出证书导入上限
//    public static final int NET_ERROR_DIGITAL_CERTIFICATE_CERT_NO_EXIST	 = (0x80000000|1047);	  // 证书文件不存在(导出证书或者获取对应证书的公钥)
    public static final int NET_ERROR_FACE_RECOGNITION_SERVER_GROUP_ID_EXCEED = (0x80000000|1051);// 组ID超过最大值

    //  JNA直接调用方法定义，cbDisConnect 实际情况并不回调Java代码，仅为定义可以使用如下方式进行定义。 fDisConnect 回调
    public  boolean CLIENT_Init(StdCallLibrary.StdCallCallback cbDisConnect, Pointer dwUser);
    public static class LLong extends IntegerType {
        private static final long serialVersionUID = 1L;

        /** Size of a native long, in bytes. */
        public static int size;
        static {
            size = Native.LONG_SIZE;
            if (Utils.getOsPrefix().toLowerCase().equals("linux-amd64")
                    || Utils.getOsPrefix().toLowerCase().equals("win32-amd64")) {
                size = 8;
            } else if (Utils.getOsPrefix().toLowerCase().equals("linux-i386")
                    || Utils.getOsPrefix().toLowerCase().equals("win32-x86")) {
                size = 4;
            }
        }

        /** Create a zero-valued LLong. */
        public LLong() {
            this(0);
        }

        /** Create a LLong with the given value. */
        public LLong(long value) {
            super(size, value);
        }
    }

    // 设备信息扩展///////////////////////////////////////////////////
    public static class NET_DEVICEINFO_Ex extends Structure {
        public byte[]     sSerialNumber = new byte[NET_SERIALNO_LEN];    // 序列号
        public int        byAlarmInPortNum;                              // DVR报警输入个数
        public int        byAlarmOutPortNum;                             // DVR报警输出个数
        public int        byDiskNum;                                     // DVR硬盘个数
        public int        byDVRType;                                     // DVR类型,见枚举NET_DEVICE_TYPE
        public int        byChanNum;                                     // DVR通道个数
        public byte       byLimitLoginTime;                              // 在线超时时间,为0表示不限制登陆,非0表示限制的分钟数
        public byte       byLeftLogTimes;                                // 当登陆失败原因为密码错误时,通过此参数通知用户,剩余登陆次数,为0时表示此参数无效
        public byte[]     bReserved = new byte[2];                       // 保留字节,字节对齐
        public int        byLockLeftTime;                                // 当登陆失败,用户解锁剩余时间（秒数）, -1表示设备未设置该参数
        public byte[]     Reserved = new byte[24];                       // 保留
    }

    // 返回函数执行失败代码
    public int CLIENT_GetLastError();

    //  JNA直接调用方法定义，登陆扩展接口///////////////////////////////////////////////////
    //  nSpecCap 对应  EM_LOGIN_SPAC_CAP_TYPE 登陆类型
    public LLong CLIENT_LoginEx2(String pchDVRIP, int wDVRPort, String pchUserName, String pchPassword, int nSpecCap, Pointer pCapParam, NET_DEVICEINFO_Ex lpDeviceInfo, IntByReference error/*= 0*/);


    //  JNA直接调用方法定义，向设备注销
    public boolean CLIENT_Logout(LLong lLoginID);

    //JNA StdCallCallback方法定义,断线回调
    public interface fDisConnect extends StdCallLibrary.StdCallCallback {
        public void invoke(LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser);
    }
    // 网络连接恢复回调函数原形
    public interface fHaveReConnect extends StdCallLibrary.StdCallCallback {
        public void invoke(LLong lLoginID, String pchDVRIP, int nDVRPort, Pointer dwUser);
    }

    // 智能分析数据回调;nSequence表示上传的相同图片情况，为0时表示是第一次出现，为2表示最后一次出现或仅出现一次，为1表示此次之后还有
    // int nState = *(int*) reserved 表示当前回调数据的状态, 为0表示当前数据为实时数据，为1表示当前回调数据是离线数据，为2时表示离线数据传送结束
    // pAlarmInfo 对应智能事件信息, pBuffer 对应智能图片信息, dwBufSize 智能图片信息大小
    public interface fAnalyzerDataCallBack extends StdCallLibrary.StdCallCallback {
        public int invoke(LLong lAnalyzerHandle, int dwAlarmType, Pointer pAlarmInfo, Pointer pBuffer, int dwBufSize, Pointer dwUser, int nSequence, Pointer reserved);
    }


    public static final int EVENT_IVS_TRAFFICJUNCTION           = 0x00000017;       // 交通路口事件----老规则(对应 DEV_EVENT_TRAFFICJUNCTION_INFO)
    public static final int EVENT_IVS_TRAFFIC_MANUALSNAP        = 0x00000118;       // 交通手动抓拍事件(对应  DEV_EENT_TRAFFIC_MANUALSNAP_INFO)
    public static final int NET_EVENT_NAME_LEN                  = 128;  // 事件名称长度
    public static final int NET_COMMON_STRING_128               = 128;  // 通用字符串长度128
    public static final int NET_EVENT_CARD_LEN                  = 36;   // 卡片名称长度
    public static final int NET_MAX_DRIVINGDIRECTION            = 256;  // 行驶方向字符串长度
    public static final int NET_COMMON_STRING_64                = 64;   // 通用字符串长度64
    public static final int NET_COMMON_STRING_32                = 32;   // 通用字符串长度32
    public static final int NET_MAX_POLYGON_NUM                 = 16;   // 多边形最大顶点个数
    public static final int NET_EVENT_MAX_CARD_NUM              = 16;   // 事件上报信息包含最大卡片个数
    public static final int MAX_PATH_LEN					    = 260;	// 最大路径长度
    public static final int MAX_RIDER_NUM 						= 16;	// 骑车人数组上限
    public static final int COMMON_SEAT_MAX_NUMBER              = 8;    // 默认检测最大座驾个数
    public static final int NET_MAX_ATTACHMENT_NUM              = 8;    // 最大车辆物件数量
    public static final int NET_MAX_ANNUUALINSPECTION_NUM       = 8;    // 最大年检标识位置
    public static final int NET_MAX_EVENT_PIC_NUM				= 6;    // 最大原始图片张数


    // 非机动车抠图信息
    public static class NET_NONMOTOR_PIC_INFO extends Structure
    {
        public int					 uOffset;								 // 在二进制数据块中的偏移
        public int					 uLength;								 // 图片大小,单位：字节
        public int					 uWidth;								 // 图片宽度
        public int					 uHeight;								 // 图片高度
        public byte[]				 szFilePath = new byte[MAX_PATH_LEN];	 // 文件路径
        public byte[]				 byReserved = new byte[512];			 // 保留
    }

    // 手动抓拍参数
    public static class MANUAL_SNAP_PARAMETER extends Structure
    {
        public int                   nChannel;               			// 抓图通道,从0开始
        public byte[]                bySequence = new byte[64];	        // 抓图序列号字符串
        public byte[]                byReserved = new byte[60];         // 保留字段
    }

    // 事件类型 EVENT_IVS_TRAFFICJUNCTION 交通路口老规则事件/视频电警上的交通卡口老规则事件对应的数据块描述信息
    // 由于历史原因,如果要处理卡口事件,DEV_EVENT_TRAFFICJUNCTION_INFO 和 EVENT_IVS_TRAFFICGATE要一起处理
    // 以防止有视频电警和线圈电警同时接入平台的情况发生, 另外EVENT_IVS_TRAFFIC_TOLLGATE只支持新卡口事件的配置
    public static class DEV_EVENT_TRAFFICJUNCTION_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[NET_EVENT_NAME_LEN];      // 事件名称
        public byte                byMainSeatBelt;                             // 主驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte                bySlaveSeatBelt;                            // 副驾驶座,系安全带状态,1-系安全带,2-未系安全带
        public byte                byVehicleDirection;                         // 当前被抓拍到的车辆是车头还是车尾,具体请见 EM_VEHICLE_DIRECTION
        public byte                byOpenStrobeState;                          // 开闸状态,具体请见 EM_OPEN_STROBE_STATE
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public int                 nLane;                                      // 对应车道号
        public int                 dwBreakingRule;                             // 违反规则掩码,第一位:闯红灯;
        // 第二位:不按规定车道行驶;
        // 第三位:逆行; 第四位：违章掉头;
        // 第五位:交通堵塞; 第六位:交通异常空闲
        // 第七位:压线行驶; 否则默认为:交通路口事件
        public NET_TIME_EX         RedLightUTC;                                // 红灯开始UTC时间
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public int                 nSequence;                                  // 表示抓拍序号,如3,2,1,1表示抓拍结束,0表示异常结束
        public int                 nSpeed;                                     // 车辆实际速度Km/h
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte                byDirection;                                // 路口方向,1-表示正向,2-表示反向
        public byte                byLightState;                               // LightState表示红绿灯状态:0 未知,1 绿灯,2 红灯,3 黄灯
        public byte                byReserved;                                 // 保留字节
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public NET_MSG_OBJECT      stuVehicle;                                 // 车身信息
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见 NET_RESERVED_COMMON, 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              szRecordFile = new byte[NET_COMMON_STRING_128];// 报警对应的原始录像文件信息
        public EVENT_JUNCTION_CUSTOM_INFO   stuCustomInfo;                     // 自定义信息
        public byte                byPlateTextSource;                          // 车牌识别来源, 0:本地算法识别,1:后端服务器算法识别
        public byte[]              bReserved1 = new byte[3];                   // 保留字节,留待扩展.
        public NET_GPS_INFO        stuGPSInfo;                                 // GPS信息 车载定制
        public byte[]              bReserved = new byte[196];                  // 保留字节,留待扩展.
        public int                 nTriggerType;                               // TriggerType:触发类型,0车检器,1雷达,2视频
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 交通车辆信息
        public int                 dwRetCardNumber;                            // 卡片个数
        public EVENT_CARD_INFO[]   stuCardInfo = (EVENT_CARD_INFO[])new EVENT_CARD_INFO().toArray(NET_EVENT_MAX_CARD_NUM);// 卡片信息
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息

        public int				   bNonMotorInfoEx;							   // 是否有非机动车信息, 1-true; 0-false
        public VA_OBJECT_NONMOTOR  stuNonMotor;								   // 非机动车信息
        public byte[]			   byReserved2 = new byte[2048];			   // 保留字节,留待扩展
    }
    // 骑车人信息
    public static class NET_RIDER_INFO extends Structure
    {
        public int					 bFeatureValid;							// 是否识别到特征信息, TRUE时下面数据才有效, 1-true; 0-false
        public int		             emSex;								    // 性别, 对应枚举  EM_SEX_TYPE
        public int					 nAge;								    // 年龄

        public int   				 emHelmet;					  		    // 头盔状态, 对应枚举  EM_NONMOTOR_OBJECT_STATUS
        public int   			     emCall;							    // 是否在打电话, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int   				 emBag;					  			    // 是否有背包, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int    				 emCarrierBag;					  	    // 有没有手提包, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int    			     emUmbrella;					  	    // 是否打伞, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int   				 emGlasses;					  	  	    // 是否有带眼镜, 对应枚举 EM_NONMOTOR_OBJECT_STATUS
        public int   				 emMask;					  	  	    // 是否带口罩, 对应枚举 EM_NONMOTOR_OBJECT_STATUS

        public int              	 emEmotion;                             // 表情, 对应枚举 EM_EMOTION_TYPE
        public int             		 emUpClothes;                           // 上衣类型, 对应枚举 EM_CLOTHES_TYPE
        public int             		 emDownClothes;                         // 下衣类型, 对应枚举 EM_CLOTHES_TYPE
        public int        			 emUpperBodyColor;                      // 上衣颜色, 对应枚举 EM_OBJECT_COLOR_TYPE
        public int       		     emLowerBodyColor;                      // 下衣颜色, 对应枚举 EM_OBJECT_COLOR_TYPE
        public byte[]				 byReserved = new byte[516];			// 保留
    }
    // 非机动车对象
    public static class VA_OBJECT_NONMOTOR extends Structure
    {
        public int					  nObjectID;                          	  // 物体ID,每个ID表示一个唯一的物体
        public int 					  emCategory;							  // 非机动车子类型, 对应枚举 EM_CATEGORY_NONMOTOR_TYPE
        public DH_RECT				  stuBoundingBox;                         // 包围盒， 非机动车矩形框，0~8191相对坐标
        public DH_RECT				  stuOriginalBoundingBox;                 // 包围盒， 非机动车矩形框，绝对坐标
        public NET_COLOR_RGBA		  stuMainColor;						      // 非机动车颜色, RGBA
        public int					  emColor;							      // 非机动车颜色, 枚举 EM_OBJECT_COLOR_TYPE
        public int					  bHasImage;							  // 是否有抠图, 1-true; 0-false
        public NET_NONMOTOR_PIC_INFO  stuImage;							      // 物体截图
        public int					  nNumOfCycling;						  // 骑车人数量
        public NET_RIDER_INFO[]		  stuRiderList =
                (NET_RIDER_INFO[])new NET_RIDER_INFO().toArray(MAX_RIDER_NUM); // 骑车人特征,个数和nNumOfCycling关联
        public byte[]				  byReserved = new byte[4096];			  // 保留
    }
    // 交通抓图图片信息
    public static class EVENT_PIC_INFO extends Structure
    {
        public int                       nOffset;                // 原始图片偏移，单位字节
        public int                       nLength;                // 原始图片长度，单位字节
    }
    public static class EVENT_COMM_INFO extends Structure
    {
        public int 						emNTPStatus;					// NTP校时状态, 取值为EM_NTP_STATUS中的值
        public int 						nDriversNum;					// 驾驶员信息数
        public Pointer 					pstDriversInfo;					// 驾驶员信息数据，类型为 NET_MSG_OBJECT_EX*
        public Pointer 					pszFilePath;					// 本地硬盘或者sd卡成功写入路径,为NULL时,路径不存在， 类型为char *
        public Pointer 					pszFTPPath;						// 设备成功写到ftp服务器的路径， 类型为char *
        public Pointer 					pszVideoPath;					// 当前接入需要获取当前违章的关联视频的FTP上传路径， 类型为char *
        public EVENT_COMM_SEAT[] 		stCommSeat = (EVENT_COMM_SEAT[])new EVENT_COMM_SEAT().toArray(COMMON_SEAT_MAX_NUMBER);// 驾驶位信息
        public int 						nAttachmentNum;					// 车辆物件个数
        public EVENT_COMM_ATTACHMENT[]  stuAttachment = (EVENT_COMM_ATTACHMENT[])new EVENT_COMM_ATTACHMENT().toArray(NET_MAX_ATTACHMENT_NUM);//车辆物件信息
        public int 						nAnnualInspectionNum;			// 年检标志个数
        public NET_RECT[] 				stuAnnualInspection = (NET_RECT[])new NET_RECT().toArray(NET_MAX_ANNUUALINSPECTION_NUM);//年检标志
        public float 					fHCRatio; 						// HC所占比例，单位：%
        public float 					fNORatio; 						// NO所占比例，单位：%
        public float 					fCOPercent; 					// CO所占百分比，单位：% 取值0~100
        public float 					fCO2Percent; 					// CO2所占百分比，单位：% 取值0~100
        public float 					fLightObscuration; 				// 不透光度，单位：% 取值0~100
        public int 						nPictureNum;					// 原始图片张数
        public EVENT_PIC_INFO[] 		stuPicInfos = (EVENT_PIC_INFO[])new EVENT_PIC_INFO().toArray(NET_MAX_EVENT_PIC_NUM);// 原始图片信息
        public float 					fTemperature;                   // 温度值,单位摄氏度
        public int 						nHumidity;                      // 相对湿度百分比值
        public float 					fPressure;                      // 气压值,单位Kpa
        public float 					fWindForce;                     // 风力值,单位m/s
        public int 						nWindDirection;                 // 风向,单位度,范围:[0,360]
        public float 					fRoadGradient;                  // 道路坡度值,单位度
        public float 					fAcceleration;                  // 加速度值,单位:m/s2
        public NET_RFIDELETAG_INFO		stuRFIDEleTagInfo;				// RFID 电子车牌标签信息
        public byte[] 					bReserved = new byte[704];      // 预留字节
        public byte[] 					szCountry = new byte[20];		// 国家
    }

    public static final int	MAX_RFIDELETAG_CARDID_LEN		    = 16;	// RFID 电子车牌标签信息中卡号最大长度
    public static final int NET_MAX_PLATE_NUMBER_LEN            = 32;   // 车牌字符长度
    public static final int	MAX_RFIDELETAG_DATE_LEN		 	    = 16;	// RFID 电子车牌标签信息中时间最大长度
    public static final int MAX_COMMON_STRING_8              	= 8;    // 通用字符串长度8
    public static final int MAX_COMMON_STRING_16             	= 16;   // 通用字符串长度16

    // RFID 电子车牌标签信息
    public static class NET_RFIDELETAG_INFO extends Structure
    {
        public byte[]					szCardID = new byte[MAX_RFIDELETAG_CARDID_LEN];	// 卡号
        public int						nCardType;										// 卡号类型, 0:交通管理机关发行卡, 1:新车出厂预装卡
        public int						emCardPrivince;									// 卡号省份, 对应   EM_CARD_PROVINCE
        public byte[]					szPlateNumber = new byte[NET_MAX_PLATE_NUMBER_LEN];			// 车牌号码
        public byte[]					szProductionDate = new byte[MAX_RFIDELETAG_DATE_LEN];		// 出厂日期
        public int						emCarType;										// 车辆类型, 对应  EM_CAR_TYPE
        public int						nPower;											// 功率,单位：千瓦时，功率值范围0~254；255表示该车功率大于可存储的最大功率值
        public int						nDisplacement;									// 排量,单位：百毫升，排量值范围0~254；255表示该车排量大于可存储的最大排量值
        public int						nAntennaID;										// 天线ID，取值范围:1~4
        public int						emPlateType;									// 号牌种类, 对应  EM_PLATE_TYPE
        public byte[]					szInspectionValidity = new byte[MAX_RFIDELETAG_DATE_LEN];	// 检验有效期，年-月
        public int						nInspectionFlag;								// 逾期未年检标志, 0:已年检, 1:逾期未年检
        public int						nMandatoryRetirement;							// 强制报废期，从检验有效期开始，距离强制报废期的年数
        public int						emCarColor;										// 车身颜色, 对应  EM_CAR_COLOR_TYPE
        public int						nApprovedCapacity;								// 核定载客量，该值<0时：无效；此值表示核定载客，单位为人
        public int						nApprovedTotalQuality;							// 此值表示总质量，单位为百千克；该值<0时：无效；该值的有效范围为0~0x3FF，0x3FF（1023）表示数据值超过了可存储的最大值
        public NET_TIME_EX				stuThroughTime;									// 过车时间
        public int						emUseProperty;									// 使用性质, 对应  EM_USE_PROPERTY_TYPE
        public byte[]					szPlateCode = new byte[MAX_COMMON_STRING_8];	// 发牌代号，UTF-8编码
        public byte[]					szPlateSN = new byte[MAX_COMMON_STRING_16];		// 号牌号码序号，UTF-8编码
        public byte[]               	bReserved = new byte[104];		                // 保留字节,留待扩展.
    }

    // 实时上传智能分析数据－图片(扩展接口,bNeedPicFile表示是否订阅图片文件,Reserved类型为RESERVED_PARA)
    // bNeedPicFile为BOOL类型，取值范围为0或者1, fAnalyzerDataCallBack回调
    public LLong CLIENT_RealLoadPictureEx(LLong lLoginID, int nChannelID, int dwAlarmType, int bNeedPicFile, StdCallLibrary.StdCallCallback cbAnalyzerData, Pointer dwUser, Pointer Reserved);

    public static final int EVENT_IVS_ALL                       = 0x00000001;       // 订阅所有事件

    //事件类型EVENT_IVS_TRAFFIC_MANUALSNAP(交通手动抓拍事件)对应的数据块描述信息
    public static class DEV_EVENT_TRAFFIC_MANUALSNAP_INFO extends Structure
    {
        public int                 nChannelID;                                 // 通道号
        public byte[]              szName = new byte[128];                     // 事件名称
        public byte[]              bReserved1 = new byte[4];                   // 字节对齐
        public double              PTS;                                        // 时间戳(单位是毫秒)
        public NET_TIME_EX         UTC;                                        // 事件发生的时间
        public int                 nEventID;                                   // 事件ID
        public int                 nLane;                                      // 对应车道号
        public byte[]              szManualSnapNo = new byte[64];              // 手动抓拍序号
        public NET_MSG_OBJECT      stuObject;                                  // 检测到的物体
        public NET_MSG_OBJECT      stuVehicle;                                 // 检测到的车身信息
        public DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO stTrafficCar;                 // 表示交通车辆的数据库记录
        public NET_EVENT_FILE_INFO stuFileInfo;                                // 事件对应文件信息
        public byte                bEventAction;                               // 事件动作,0表示脉冲事件,1表示持续性事件开始,2表示持续性事件结束;
        public byte                byOpenStrobeState;                          // 开闸状态, 具体请见 EM_OPEN_STROBE_STATE
        public byte[]              byReserved = new byte[1];
        public byte                byImageIndex;                               // 图片的序号, 同一时间内(精确到秒)可能有多张图片, 从0开始
        public int                 dwSnapFlagMask;                             // 抓图标志(按位),具体见NET_RESERVED_COMMON , 0位:"*",1位:"Timing",2位:"Manual",3位:"Marked",4位:"Event",5位:"Mosaic",6位:"Cutout"
        public NET_RESOLUTION_INFO stuResolution;                              // 对应图片的分辨率
        public byte[]              bReserved = new byte[1016];                 // 保留字节,留待扩展.
        public EVENT_COMM_INFO     stCommInfo;                                 // 公共信息
    }
    //区域；各边距按整长8192的比例
    public static class NET_RECT extends Structure
    {
        public int left;
        public int top;
        public int right;
        public int bottom;

        @Override
        public String toString() {
            return "[" + left + " " + top + " " + right + " " + bottom + "]";
        }
    }
    // 设备控制扩展接口，兼容 CLIENT_ControlDevice (pInBuf, pOutBuf内存由用户申请释放)
    // emType的取值为CtrlType中的值
    public boolean CLIENT_ControlDeviceEx(LLong lLoginID, int emType, Pointer pInBuf, Pointer pOutBuf, int nWaitTime);
    // 控制类型，对应CLIENT_ControlDevice接口
    public static class CtrlType extends Structure
    {
        public static final int CTRLTYPE_CTRL_REBOOT = 0;//重启设备
        public static final int CTRLTYPE_CTRL_SHUTDOWN = CTRLTYPE_CTRL_REBOOT+1; //关闭设备
        public static final int CTRLTYPE_CTRL_DISK = CTRLTYPE_CTRL_SHUTDOWN+1; //硬盘管理
        public static final int CTRLTYPE_KEYBOARD_POWER =3;//网络键盘
        public static final int CTRLTYPE_KEYBOARD_ENTER = CTRLTYPE_KEYBOARD_POWER+1;
        public static final int CTRLTYPE_KEYBOARD_ESC = CTRLTYPE_KEYBOARD_ENTER+1;
        public static final int CTRLTYPE_KEYBOARD_UP = CTRLTYPE_KEYBOARD_ESC+1;
        public static final int CTRLTYPE_KEYBOARD_DOWN = CTRLTYPE_KEYBOARD_UP+1;
        public static final int CTRLTYPE_KEYBOARD_LEFT = CTRLTYPE_KEYBOARD_DOWN+1;
        public static final int CTRLTYPE_KEYBOARD_RIGHT = CTRLTYPE_KEYBOARD_LEFT+1;
        public static final int CTRLTYPE_KEYBOARD_BTN0 = CTRLTYPE_KEYBOARD_RIGHT+1;
        public static final int CTRLTYPE_KEYBOARD_BTN1 = CTRLTYPE_KEYBOARD_BTN0+1;
        public static final int CTRLTYPE_KEYBOARD_BTN2 = CTRLTYPE_KEYBOARD_BTN1+1;
        public static final int CTRLTYPE_KEYBOARD_BTN3 = CTRLTYPE_KEYBOARD_BTN2+1;
        public static final int CTRLTYPE_KEYBOARD_BTN4 = CTRLTYPE_KEYBOARD_BTN3+1;
        public static final int CTRLTYPE_KEYBOARD_BTN5 = CTRLTYPE_KEYBOARD_BTN4+1;
        public static final int CTRLTYPE_KEYBOARD_BTN6 = CTRLTYPE_KEYBOARD_BTN5+1;
        public static final int CTRLTYPE_KEYBOARD_BTN7 = CTRLTYPE_KEYBOARD_BTN6+1;
        public static final int CTRLTYPE_KEYBOARD_BTN8 = CTRLTYPE_KEYBOARD_BTN7+1;
        public static final int CTRLTYPE_KEYBOARD_BTN9 = CTRLTYPE_KEYBOARD_BTN8+1;
        public static final int CTRLTYPE_KEYBOARD_BTN10 = CTRLTYPE_KEYBOARD_BTN9+1;
        public static final int CTRLTYPE_KEYBOARD_BTN11 = CTRLTYPE_KEYBOARD_BTN10+1;
        public static final int CTRLTYPE_KEYBOARD_BTN12 = CTRLTYPE_KEYBOARD_BTN11+1;
        public static final int CTRLTYPE_KEYBOARD_BTN13 = CTRLTYPE_KEYBOARD_BTN12+1;
        public static final int CTRLTYPE_KEYBOARD_BTN14 = CTRLTYPE_KEYBOARD_BTN13+1;
        public static final int CTRLTYPE_KEYBOARD_BTN15 = CTRLTYPE_KEYBOARD_BTN14+1;
        public static final int CTRLTYPE_KEYBOARD_BTN16 = CTRLTYPE_KEYBOARD_BTN15+1;
        public static final int CTRLTYPE_KEYBOARD_SPLIT = CTRLTYPE_KEYBOARD_BTN16+1;
        public static final int CTRLTYPE_KEYBOARD_ONE = CTRLTYPE_KEYBOARD_SPLIT+1;
        public static final int CTRLTYPE_KEYBOARD_NINE = CTRLTYPE_KEYBOARD_ONE+1;
        public static final int CTRLTYPE_KEYBOARD_ADDR = CTRLTYPE_KEYBOARD_NINE+1;
        public static final int CTRLTYPE_KEYBOARD_INFO = CTRLTYPE_KEYBOARD_ADDR+1;
        public static final int CTRLTYPE_KEYBOARD_REC = CTRLTYPE_KEYBOARD_INFO+1;
        public static final int CTRLTYPE_KEYBOARD_FN1 = CTRLTYPE_KEYBOARD_REC+1;
        public static final int CTRLTYPE_KEYBOARD_FN2 = CTRLTYPE_KEYBOARD_FN1+1;
        public static final int CTRLTYPE_KEYBOARD_PLAY = CTRLTYPE_KEYBOARD_FN2+1;
        public static final int CTRLTYPE_KEYBOARD_STOP = CTRLTYPE_KEYBOARD_PLAY+1;
        public static final int CTRLTYPE_KEYBOARD_SLOW = CTRLTYPE_KEYBOARD_STOP+1;
        public static final int CTRLTYPE_KEYBOARD_FAST = CTRLTYPE_KEYBOARD_SLOW+1;
        public static final int CTRLTYPE_KEYBOARD_PREW = CTRLTYPE_KEYBOARD_FAST+1;
        public static final int CTRLTYPE_KEYBOARD_NEXT = CTRLTYPE_KEYBOARD_PREW+1;
        public static final int CTRLTYPE_KEYBOARD_JMPDOWN = CTRLTYPE_KEYBOARD_NEXT+1;
        public static final int CTRLTYPE_KEYBOARD_JMPUP = CTRLTYPE_KEYBOARD_JMPDOWN+1;
        public static final int CTRLTYPE_KEYBOARD_10PLUS = CTRLTYPE_KEYBOARD_JMPUP+1;
        public static final int CTRLTYPE_KEYBOARD_SHIFT = CTRLTYPE_KEYBOARD_10PLUS+1;
        public static final int CTRLTYPE_KEYBOARD_BACK = CTRLTYPE_KEYBOARD_SHIFT+1;
        public static final int CTRLTYPE_KEYBOARD_LOGIN = CTRLTYPE_KEYBOARD_BACK+1;//新网络键盘功能
        public static final int CTRLTYPE_KEYBOARD_CHNNEL = CTRLTYPE_KEYBOARD_LOGIN+1;//切换视频通道
        public static final int CTRLTYPE_TRIGGER_ALARM_IN =100;//触发报警输入
        public static final int CTRLTYPE_TRIGGER_ALARM_OUT = CTRLTYPE_TRIGGER_ALARM_IN+1; //触发报警输出
        public static final int CTRLTYPE_CTRL_MATRIX = CTRLTYPE_TRIGGER_ALARM_OUT+1; //矩阵控制
        public static final int CTRLTYPE_CTRL_SDCARD = CTRLTYPE_CTRL_MATRIX+1; //SD卡控制(IPC产品)参数同硬盘控制
        public static final int CTRLTYPE_BURNING_START = CTRLTYPE_CTRL_SDCARD+1; //刻录机控制，开始刻录
        public static final int CTRLTYPE_BURNING_STOP = CTRLTYPE_BURNING_START+1; //刻录机控制，结束刻录
        public static final int CTRLTYPE_BURNING_ADDPWD = CTRLTYPE_BURNING_STOP+1; //刻录机控制，叠加密码(以'\0'为结尾的字符串，最大长度8位)
        public static final int CTRLTYPE_BURNING_ADDHEAD = CTRLTYPE_BURNING_ADDPWD+1; //刻录机控制，叠加片头(以'\0'为结尾的字符串，最大长度1024字节，支持分行，行分隔符'\n')
        public static final int CTRLTYPE_BURNING_ADDSIGN = CTRLTYPE_BURNING_ADDHEAD+1; //刻录机控制，叠加打点到刻录信息(参数无)
        public static final int CTRLTYPE_BURNING_ADDCURSTOMINFO = CTRLTYPE_BURNING_ADDSIGN+1; //刻录机控制，自定义叠加(以'\0'为结尾的字符串，最大长度1024字节，支持分行，行分隔符'\n')
        public static final int CTRLTYPE_CTRL_RESTOREDEFAULT = CTRLTYPE_BURNING_ADDCURSTOMINFO+1; //恢复设备的默认设置
        public static final int CTRLTYPE_CTRL_CAPTURE_START = CTRLTYPE_CTRL_RESTOREDEFAULT+1; //触发设备抓图
        public static final int CTRLTYPE_CTRL_CLEARLOG = CTRLTYPE_CTRL_CAPTURE_START+1; //清除日志
        public static final int CTRLTYPE_TRIGGER_ALARM_WIRELESS =200;//触发无线报警(IPC产品)
        public static final int CTRLTYPE_MARK_IMPORTANT_RECORD = CTRLTYPE_TRIGGER_ALARM_WIRELESS+1; //标识重要录像文件
        public static final int CTRLTYPE_CTRL_DISK_SUBAREA = CTRLTYPE_MARK_IMPORTANT_RECORD+1; //网络硬盘分区
        public static final int CTRLTYPE_BURNING_ATTACH = CTRLTYPE_CTRL_DISK_SUBAREA+1; //刻录机控制，附件刻录.
        public static final int CTRLTYPE_BURNING_PAUSE = CTRLTYPE_BURNING_ATTACH+1; //刻录暂停
        public static final int CTRLTYPE_BURNING_CONTINUE = CTRLTYPE_BURNING_PAUSE+1; //刻录继续
        public static final int CTRLTYPE_BURNING_POSTPONE = CTRLTYPE_BURNING_CONTINUE+1; //刻录顺延
        public static final int CTRLTYPE_CTRL_OEMCTRL = CTRLTYPE_BURNING_POSTPONE+1; //报停控制
        public static final int CTRLTYPE_BACKUP_START = CTRLTYPE_CTRL_OEMCTRL+1; //设备备份开始
        public static final int CTRLTYPE_BACKUP_STOP = CTRLTYPE_BACKUP_START+1; //设备备份停止
        public static final int CTRLTYPE_VIHICLE_WIFI_ADD = CTRLTYPE_BACKUP_STOP+1; //车载手动增加WIFI配置
        public static final int CTRLTYPE_VIHICLE_WIFI_DEC = CTRLTYPE_VIHICLE_WIFI_ADD+1; //车载手动删除WIFI配置
        public static final int CTRLTYPE_BUZZER_START = CTRLTYPE_VIHICLE_WIFI_DEC+1; //蜂鸣器控制开始
        public static final int CTRLTYPE_BUZZER_STOP = CTRLTYPE_BUZZER_START+1; //蜂鸣器控制结束
        public static final int CTRLTYPE_REJECT_USER = CTRLTYPE_BUZZER_STOP+1; //剔除用户
        public static final int CTRLTYPE_SHIELD_USER = CTRLTYPE_REJECT_USER+1; //屏蔽用户
        public static final int CTRLTYPE_RAINBRUSH = CTRLTYPE_SHIELD_USER+1; //智能交通,雨刷控制
        public static final int CTRLTYPE_MANUAL_SNAP = CTRLTYPE_RAINBRUSH+1; //智能交通,手动抓拍(对应结构体MANUAL_SNAP_PARAMETER)
        public static final int CTRLTYPE_MANUAL_NTP_TIMEADJUST = CTRLTYPE_MANUAL_SNAP+1; //手动NTP校时
        public static final int CTRLTYPE_NAVIGATION_SMS = CTRLTYPE_MANUAL_NTP_TIMEADJUST+1; //导航信息和短消息
        public static final int CTRLTYPE_CTRL_ROUTE_CROSSING = CTRLTYPE_NAVIGATION_SMS+1; //路线点位信息
        public static final int CTRLTYPE_BACKUP_FORMAT = CTRLTYPE_CTRL_ROUTE_CROSSING+1; //格式化备份设备
        public static final int CTRLTYPE_DEVICE_LOCALPREVIEW_SLIPT = CTRLTYPE_BACKUP_FORMAT+1; //控制设备端本地预览分割(对应结构体DEVICE_LOCALPREVIEW_SLIPT_PARAMETER)
        public static final int CTRLTYPE_CTRL_INIT_RAID = CTRLTYPE_DEVICE_LOCALPREVIEW_SLIPT+1; //RAID初始化
        public static final int CTRLTYPE_CTRL_RAID = CTRLTYPE_CTRL_INIT_RAID+1; //RAID操作
        public static final int CTRLTYPE_CTRL_SAPREDISK = CTRLTYPE_CTRL_RAID+1; //热备盘操作
        public static final int CTRLTYPE_WIFI_CONNECT = CTRLTYPE_CTRL_SAPREDISK+1; //手动发起WIFI连接(对应结构体WIFI_CONNECT)
        public static final int CTRLTYPE_WIFI_DISCONNECT = CTRLTYPE_WIFI_CONNECT+1; //手动断开WIFI连接(对应结构体WIFI_CONNECT)
        public static final int CTRLTYPE_CTRL_ARMED = CTRLTYPE_WIFI_DISCONNECT+1; //布撤防操作
        public static final int CTRLTYPE_CTRL_IP_MODIFY = CTRLTYPE_CTRL_ARMED+1; //修改前端IP(对应结构体 NET_CTRL_IPMODIFY_PARAM)
        public static final int CTRLTYPE_CTRL_WIFI_BY_WPS = CTRLTYPE_CTRL_IP_MODIFY+1; //wps连接wifi(对应结构体NET_CTRL_CONNECT_WIFI_BYWPS)
        public static final int CTRLTYPE_CTRL_FORMAT_PATITION = CTRLTYPE_CTRL_WIFI_BY_WPS+1; //格式化分区(对应结构体NET_FORMAT_PATITION)
        public static final int CTRLTYPE_CTRL_EJECT_STORAGE = CTRLTYPE_CTRL_FORMAT_PATITION+1; //手动卸载设备(对应结构体NET_EJECT_STORAGE_DEVICE)
        public static final int CTRLTYPE_CTRL_LOAD_STORAGE = CTRLTYPE_CTRL_EJECT_STORAGE+1; //手动装载设备(对应结构体NET_LOAD_STORAGE_DEVICE)
        public static final int CTRLTYPE_CTRL_CLOSE_BURNER = CTRLTYPE_CTRL_LOAD_STORAGE+1; //关闭刻录机光驱门(对应结构体NET_CTRL_BURNERDOOR)一般需要等6
        public static final int CTRLTYPE_CTRL_EJECT_BURNER = CTRLTYPE_CTRL_CLOSE_BURNER+1; //弹出刻录机光驱门(对应结构体NET_CTRL_BURNERDOOR)一般需要等4秒
        public static final int CTRLTYPE_CTRL_CLEAR_ALARM = CTRLTYPE_CTRL_EJECT_BURNER+1; //消警(对应结构体NET_CTRL_CLEAR_ALARM)
        public static final int CTRLTYPE_CTRL_MONITORWALL_TVINFO = CTRLTYPE_CTRL_CLEAR_ALARM+1; //电视墙信息显示(对应结构体NET_CTRL_MONITORWALL_TVINFO)
        public static final int CTRLTYPE_CTRL_START_VIDEO_ANALYSE = CTRLTYPE_CTRL_MONITORWALL_TVINFO+1; //开始视频智能分析(对应结构体NET_CTRL_START_VIDEO_ANALYSE)
        public static final int CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE = CTRLTYPE_CTRL_START_VIDEO_ANALYSE+1; //停止视频智能分析(对应结构体NET_CTRL_STOP_VIDEO_ANALYSE)
        public static final int CTRLTYPE_CTRL_UPGRADE_DEVICE = CTRLTYPE_CTRL_STOP_VIDEO_ANALYSE+1; //控制启动设备升级,由设备独立完成升级过程,不需要传输升级文件
        public static final int CTRLTYPE_CTRL_MULTIPLAYBACK_CHANNALES = CTRLTYPE_CTRL_UPGRADE_DEVICE+1; //切换多通道预览回放的通道(对应结构体NET_CTRL_MULTIPLAYBACK_CHANNALES)
        public static final int CTRLTYPE_CTRL_SEQPOWER_OPEN = CTRLTYPE_CTRL_MULTIPLAYBACK_CHANNALES+1; //电源时序器打开开关量输出口(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_SEQPOWER_CLOSE = CTRLTYPE_CTRL_SEQPOWER_OPEN+1; //电源时序器关闭开关量输出口(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_SEQPOWER_OPEN_ALL = CTRLTYPE_CTRL_SEQPOWER_CLOSE+1; //电源时序器打开开关量输出口组(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_SEQPOWER_CLOSE_ALL = CTRLTYPE_CTRL_SEQPOWER_OPEN_ALL+1; //电源时序器关闭开关量输出口组(对应NET_CTRL_SEQPOWER_PARAM)
        public static final int CTRLTYPE_CTRL_PROJECTOR_RISE = CTRLTYPE_CTRL_SEQPOWER_CLOSE_ALL+1; //投影仪上升(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int CTRLTYPE_CTRL_PROJECTOR_FALL = CTRLTYPE_CTRL_PROJECTOR_RISE+1; //投影仪下降(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int CTRLTYPE_CTRL_PROJECTOR_STOP = CTRLTYPE_CTRL_PROJECTOR_FALL+1; //投影仪停止(对应NET_CTRL_PROJECTOR_PARAM)
        public static final int CTRLTYPE_CTRL_INFRARED_KEY = CTRLTYPE_CTRL_PROJECTOR_STOP+1; //红外按键(对应NET_CTRL_INFRARED_KEY_PARAM)
        public static final int CTRLTYPE_CTRL_START_PLAYAUDIO = CTRLTYPE_CTRL_INFRARED_KEY+1; //设备开始播放音频文件(对应结构体NET_CTRL_START_PLAYAUDIO)
        public static final int CTRLTYPE_CTRL_STOP_PLAYAUDIO = CTRLTYPE_CTRL_START_PLAYAUDIO+1; //设备停止播放音频文件
        public static final int CTRLTYPE_CTRL_START_ALARMBELL = CTRLTYPE_CTRL_STOP_PLAYAUDIO+1; //开启警号(对应结构体 NET_CTRL_ALARMBELL )
        public static final int CTRLTYPE_CTRL_STOP_ALARMBELL = CTRLTYPE_CTRL_START_ALARMBELL+1; //关闭警号(对应结构体 NET_CTRL_ALARMBELL )
        public static final int CTRLTYPE_CTRL_ACCESS_OPEN = CTRLTYPE_CTRL_STOP_ALARMBELL+1; //门禁控制-开门(对应结构体 NET_CTRL_ACCESS_OPEN)
        public static final int CTRLTYPE_CTRL_SET_BYPASS = CTRLTYPE_CTRL_ACCESS_OPEN+1; //设置旁路功能(对应结构体NET_CTRL_SET_BYPASS)
        public static final int CTRLTYPE_CTRL_RECORDSET_INSERT = CTRLTYPE_CTRL_SET_BYPASS+1; //添加记录，获得记录集编号(对应NET_CTRL_RECORDSET_INSERT_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_UPDATE = CTRLTYPE_CTRL_RECORDSET_INSERT+1; //更新某记录集编号的记录(对应 NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_REMOVE = CTRLTYPE_CTRL_RECORDSET_UPDATE+1; //根据记录集编号删除某记录(对应 NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_RECORDSET_CLEAR = CTRLTYPE_CTRL_RECORDSET_REMOVE+1; //清除所有记录集信息(对应NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_ACCESS_CLOSE = CTRLTYPE_CTRL_RECORDSET_CLEAR+1; //门禁控制-关门(对应结构体NET_CTRL_ACCESS_CLOSE)
        public static final int CTRLTYPE_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET = CTRLTYPE_CTRL_ACCESS_CLOSE+1; //报警子系统激活设置(对应结构体NET_CTRL_ALARM_SUBSYSTEM_SETACTIVE)
        public static final int CTRLTYPE_CTRL_FORBID_OPEN_STROBE = CTRLTYPE_CTRL_ALARM_SUBSYSTEM_ACTIVE_SET+1; //禁止设备端开闸(对应结构体NET_CTRL_FORBID_OPEN_STROBE)
        public static final int CTRLTYPE_CTRL_OPEN_STROBE = CTRLTYPE_CTRL_FORBID_OPEN_STROBE+1; //开启道闸(对应结构体 NET_CTRL_OPEN_STROBE)
        public static final int CTRLTYPE_CTRL_TALKING_REFUSE = CTRLTYPE_CTRL_OPEN_STROBE+1; //对讲拒绝接听(对应结构体NET_CTRL_TALKING_REFUSE)
        public static final int CTRLTYPE_CTRL_ARMED_EX = CTRLTYPE_CTRL_TALKING_REFUSE+1; //布撤防操作(对应结构体CTRL_ARM_DISARM_PARAM_EX),对CTRL_ARM_DISARM_PARAM升级，建议用这个
        public static final int CTRLTYPE_CTRL_NET_KEYBOARD =400;//网络键盘控制(对应结构体NET_CTRL_NET_KEYBOARD)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_OPEN = CTRLTYPE_CTRL_NET_KEYBOARD+1; //打开空调(对应结构体NET_CTRL_OPEN_AIRCONDITION)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_CLOSE = CTRLTYPE_CTRL_AIRCONDITION_OPEN+1; //关闭空调(对应结构体NET_CTRL_CLOSE_AIRCONDITION)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_SET_TEMPERATURE = CTRLTYPE_CTRL_AIRCONDITION_CLOSE+1; //设定空调温度(对应结构体NET_CTRL_SET_TEMPERATURE)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_ADJUST_TEMPERATURE = CTRLTYPE_CTRL_AIRCONDITION_SET_TEMPERATURE+1; //调节空调温度(对应结构体NET_CTRL_ADJUST_TEMPERATURE)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_SETMODE = CTRLTYPE_CTRL_AIRCONDITION_ADJUST_TEMPERATURE+1; //设置空调工作模式(对应结构体NET_CTRL_ADJUST_TEMPERATURE)
        public static final int CTRLTYPE_CTRL_AIRCONDITION_SETWINDMODE = CTRLTYPE_CTRL_AIRCONDITION_SETMODE+1; //设置空调送风模式(对应结构体NET_CTRL_AIRCONDITION_SETMODE)
        public static final int CTRLTYPE_CTRL_RESTOREDEFAULT_EX  = CTRLTYPE_CTRL_AIRCONDITION_SETWINDMODE+1;//恢复设备的默认设置新协议(对应结构体 NET_CTRL_RESTORE_DEFAULT )
        // 恢复配置优先使用该枚举，如果接口失败，
        // 且CLIENT_GetLastError返回NET_UNSUPPORTED,再尝试使用NET_CTRL_RESTOREDEFAULT恢复配置
        public static final int CTRLTYPE_CTRL_NOTIFY_EVENT = CTRLTYPE_CTRL_RESTOREDEFAULT_EX+1; //向设备发送事件(对应结构体NET_NOTIFY_EVENT_DATA)
        public static final int CTRLTYPE_CTRL_SILENT_ALARM_SET = CTRLTYPE_CTRL_NOTIFY_EVENT+1; //无声报警设置
        public static final int CTRLTYPE_CTRL_START_PLAYAUDIOEX = CTRLTYPE_CTRL_SILENT_ALARM_SET+1; //设备开始语音播报(对应结构体NET_CTRL_START_PLAYAUDIOEX)
        public static final int CTRLTYPE_CTRL_STOP_PLAYAUDIOEX = CTRLTYPE_CTRL_START_PLAYAUDIOEX+1; //设备停止语音播报
        public static final int CTRLTYPE_CTRL_CLOSE_STROBE = CTRLTYPE_CTRL_STOP_PLAYAUDIOEX+1; //关闭道闸(对应结构体 NET_CTRL_CLOSE_STROBE)
        public static final int CTRLTYPE_CTRL_SET_ORDER_STATE = CTRLTYPE_CTRL_CLOSE_STROBE+1; //设置车位预定状态(对应结构体NET_CTRL_SET_ORDER_STATE)
        public static final int CTRLTYPE_CTRL_RECORDSET_INSERTEX = CTRLTYPE_CTRL_SET_ORDER_STATE+1; //添加记录，获得记录集编号(对应 NET_CTRL_RECORDSET_INSERT_PARAM )
        public static final int CTRLTYPE_CTRL_RECORDSET_UPDATEEX = CTRLTYPE_CTRL_RECORDSET_INSERTEX+1; //更新某记录集编号的记录(对应NET_CTRL_RECORDSET_PARAM)
        public static final int CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT = CTRLTYPE_CTRL_RECORDSET_UPDATEEX+1; // 指纹采集(对应结构体NET_CTRL_CAPTURE_FINGER_PRINT)
        public static final int CTRLTYPE_CTRL_ECK_LED_SET = CTRLTYPE_CTRL_CAPTURE_FINGER_PRINT+1; //停车场出入口控制器LED设置(对应结构体NET_CTRL_ECK_LED_SET_PARAM)
        public static final int CTRLTYPE_CTRL_ECK_IC_CARD_IMPORT = CTRLTYPE_CTRL_ECK_LED_SET+1; //智能停车系统出入口机IC卡信息导入(对应结构体NET_CTRL_ECK_IC_CARD_IMPORT_PARAM)
        public static final int CTRLTYPE_CTRL_ECK_SYNC_IC_CARD = CTRLTYPE_CTRL_ECK_IC_CARD_IMPORT+1; //智能停车系统出入口机IC卡信息同步指令，收到此指令后，设备删除原有IC卡信息(对应结构体NET_CTRL_ECK_SYNC_IC_CARD_PARAM)
        public static final int CTRLTYPE_CTRL_LOWRATEWPAN_REMOVE = CTRLTYPE_CTRL_ECK_SYNC_IC_CARD+1; //删除指定无线设备(对应结构体NET_CTRL_LOWRATEWPAN_REMOVE)
        public static final int CTRLTYPE_CTRL_LOWRATEWPAN_MODIFY = CTRLTYPE_CTRL_LOWRATEWPAN_REMOVE+1; //修改无线设备信息(对应结构体NET_CTRL_LOWRATEWPAN_MODIFY)
        public static final int CTRLTYPE_CTRL_ECK_SET_PARK_INFO = CTRLTYPE_CTRL_LOWRATEWPAN_MODIFY+1; //智能停车系统出入口机设置车位信息(对应结构体NET_CTRL_ECK_SET_PARK_INFO_PARAM)
        public static final int CTRLTYPE_CTRL_VTP_DISCONNECT = CTRLTYPE_CTRL_ECK_SET_PARK_INFO+1; //挂断视频电话(对应结构体NET_CTRL_VTP_DISCONNECT)
        public static final int CTRLTYPE_CTRL_UPDATE_FILES = CTRLTYPE_CTRL_VTP_DISCONNECT+1; //远程投放多媒体文件更新(对应结构体NET_CTRL_UPDATE_FILES)
        public static final int CTRLTYPE_CTRL_MATRIX_SAVE_SWITCH = CTRLTYPE_CTRL_UPDATE_FILES+1; //保存上下位矩阵输出关系(对应结构体NET_CTRL_MATRIX_SAVE_SWITCH)
        public static final int CTRLTYPE_CTRL_MATRIX_RESTORE_SWITCH = CTRLTYPE_CTRL_MATRIX_SAVE_SWITCH+1; //恢复上下位矩阵输出关系(对应结构体NET_CTRL_MATRIX_RESTORE_SWITCH)
        public static final int CTRLTYPE_CTRL_VTP_DIVERTACK = CTRLTYPE_CTRL_MATRIX_RESTORE_SWITCH+1; //呼叫转发响应(对应结构体NET_CTRL_VTP_DIVERTACK)
        public static final int CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE = CTRLTYPE_CTRL_VTP_DIVERTACK+1; //雨刷来回刷一次，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_MOVEONCE)
        public static final int CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY = CTRLTYPE_CTRL_RAINBRUSH_MOVEONCE+1; //雨刷来回循环刷，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_MOVECONTINUOUSLY)
        public static final int CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE = CTRLTYPE_CTRL_RAINBRUSH_MOVECONTINUOUSLY+1; //雨刷停止刷，雨刷模式配置为手动模式时有效(对应结构体NET_CTRL_RAINBRUSH_STOPMOVE)
        public static final int CTRLTYPE_CTRL_ALARM_ACK = CTRLTYPE_CTRL_RAINBRUSH_STOPMOVE+1; //报警事件确认(对应结构体NET_CTRL_ALARM_ACK)
        // NET_CTRL_ALARM_ACK 该操作切勿在报警回调接口中调用
        public static final int CTRLTYPE_CTRL_RECORDSET_IMPORT = CTRLTYPE_CTRL_ALARM_ACK + 1; // 批量导入记录集信息(对应 NET_CTRL_RECORDSET_PARAM )
        public static final int CTRLTYPE_CTRL_DELIVERY_FILE = CTRLTYPE_CTRL_RECORDSET_IMPORT + 1; // 向视频输出口投放视频和图片文件, 楼宇对讲使用，同一时间投放(对应 NET_CTRL_DELIVERY_FILE )
        // 以下命令只在 CLIENT_ControlDeviceEx 上有效
        public static final int CTRLTYPE_CTRL_THERMO_GRAPHY_ENSHUTTER = 0x10000;//设置热成像快门启用/禁用,pInBuf= NET_IN_THERMO_EN_SHUTTER*, pOutBuf= NET_OUT_THERMO_EN_SHUTTER *
        public static final int CTRLTYPE_CTRL_RADIOMETRY_SETOSDMARK = CTRLTYPE_CTRL_THERMO_GRAPHY_ENSHUTTER+1; // 设置测温项的osd为高亮,pInBuf=NET_IN_RADIOMETRY_SETOSDMARK*,pOutBuf= NET_OUT_RADIOMETRY_SETOSDMARK *
        public static final int CTRLTYPE_CTRL_AUDIO_REC_START_NAME = CTRLTYPE_CTRL_RADIOMETRY_SETOSDMARK+1; // 开启音频录音并得到录音名,pInBuf = NET_IN_AUDIO_REC_MNG_NAME *, pOutBuf = NET_OUT_AUDIO_REC_MNG_NAME *
        public static final int CTRLTYPE_CTRL_AUDIO_REC_STOP_NAME = CTRLTYPE_CTRL_AUDIO_REC_START_NAME+1; // 关闭音频录音并返回文件名称,pInBuf = NET_IN_AUDIO_REC_MNG_NAME *, pOutBuf = NET_OUT_AUDIO_REC_MNG_NAME *
        public static final int CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT = CTRLTYPE_CTRL_AUDIO_REC_STOP_NAME+1; // 即时抓图(又名手动抓图),pInBuf  =NET_IN_SNAP_MNG_SHOT *, pOutBuf = NET_OUT_SNAP_MNG_SHOT *
        public static final int CTRLTYPE_CTRL_LOG_STOP = CTRLTYPE_CTRL_SNAP_MNG_SNAP_SHOT+1; // 强制同步缓存数据到数据库并关闭数据库,pInBuf = NET_IN_LOG_MNG_CTRL *, pOutBuf = NET_OUT_LOG_MNG_CTRL *
        public static final int CTRLTYPE_CTRL_LOG_RESUME = CTRLTYPE_CTRL_LOG_STOP+1; // 恢复数据库,pInBuf = NET_IN_LOG_MNG_CTRL *, pOutBuf = NET_OUT_LOG_MNG_CTRL *
    }
    // 车辆物件
    public static class EVENT_COMM_ATTACHMENT extends Structure
    {
        public int emAttachmentType;//物件类型, 取值为EM_COMM_ATTACHMENT_TYPE中的值
        public NET_RECT stuRect;//坐标
        public byte[] bReserved = new byte[20];//预留字节
    }
    // 违规状态
    public static class EVENT_COMM_STATUS extends Structure
    {
        public byte bySmoking;//是否抽烟
        public byte byCalling;//是否打电话
        public byte[] szReserved = new byte[14];//预留字段
    }
    // 驾驶位违规信息
    public static class EVENT_COMM_SEAT extends Structure
    {
        public int bEnable;//是否检测到座驾信息, 类型BOOL, 取值0或者1
        public int emSeatType;//座驾类型,0:未识别;1:主驾驶; 取值为EM_COMMON_SEAT_TYPE中的值
        public EVENT_COMM_STATUS stStatus;//违规状态
        public int emSafeBeltStatus;//安全带状态, 取值为NET_SAFEBELT_STATE中的值
        public int emSunShadeStatus;//遮阳板状态, 取值为NET_SUNSHADE_STATE中的值
        public byte[] szReserved = new byte[24];//预留字节
    }

    public static class NET_TIME_EX extends Structure
    {
        public int                dwYear;                    // 年
        public int                dwMonth;                   // 月
        public int                dwDay;                     // 日
        public int                dwHour;                    // 时
        public int                dwMinute;                  // 分
        public int                dwSecond;                  // 秒
        public int                dwMillisecond;             // 毫秒
        public int[]              dwReserved = new int[2];   // 保留字段

        @Override
        public String toString() {
            return dwYear + "/" + dwMonth + "/" + dwDay + " " + dwHour + ":" + dwMinute + ":" + dwSecond;
        }

        //用于列表中显示
        public String toStringTime()
        {
            return  String.format("%02d/%02d/%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        //存储文件名使用
        public String toStringTitle()
        {
            return  String.format("Time_%02d%02d%02d_%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }

    // 事件对应文件信息
    public static class NET_EVENT_FILE_INFO extends Structure
    {
        public byte 		bCount;			// 当前文件所在文件组中的文件总数
        public byte 		bIndex;			// 当前文件在文件组中的文件编号(编号1开始)
        public byte 		bFileTag;		// 文件标签,具体说明见枚举类型 EM_EVENT_FILETAG
        public byte 		bFileType;		// 文件类型,0-普通1-合成2-抠图
        public NET_TIME_EX  stuFileTime;	// 文件时间
        public int 			nGroupId;		// 同一组抓拍文件的唯一标识
    }

    //视频分析物体信息结构体
    public static class NET_MSG_OBJECT extends Structure
    {
        public int 			nObjectID;							//物体ID,每个ID表示一个唯一的物体
        public byte[] 		szObjectType = new byte[128];		//物体类型
        public int 			nConfidence;						//置信度(0~255),值越大表示置信度越高
        public int 			nAction;						    //物体动作:1:Appear2:Move3:Stay
        public DH_RECT 		BoundingBox;						//包围盒
        public NET_POINT 	Center;								//物体型心
        public int 			nPolygonNum;						//多边形顶点个数
        public NET_POINT[]  Contour = (NET_POINT[])new NET_POINT().toArray(NET_MAX_POLYGON_NUM);//较精确的轮廓多边形
        public int 			rgbaMainColor;						//表示车牌、车身等物体主要颜色；按字节表示,分别为红、绿、蓝和透明度,例如:RGB值为(0,255,0),透明度为0时,其值为0x00ff0000.

        public byte[] 		szText = new byte[128];			    // 物体上相关的带0结束符文本,比如车牌,集装箱号等等
        // "ObjectType"为"Vehicle"或者"Logo"时（尽量使用Logo。Vehicle是为了兼容老产品）表示车标,支持：

        public byte[] 		szObjectSubType = new byte[62];		//物体子类别,根据不同的物体类型,可以取以下子类型：
        // Vehicle Category:"Unknown"  未知,"Motor" 机动车,"Non-Motor":非机动车,"Bus": 公交车,"Bicycle" 自行车,"Motorcycle":摩托车,"PassengerCar":客车,
        // "LargeTruck":大货车,    "MidTruck":中货车,"SaloonCar":轿车,"Microbus":面包车,"MicroTruck":小货车,"Tricycle":三轮车,    "Passerby":行人
        // Plate Category："Unknown" 未知,"Normal" 蓝牌黑牌,"Yellow" 黄牌,"DoubleYellow" 双层黄尾牌,"Police" 警牌"Armed" 武警牌,
        // "Military" 部队号牌,"DoubleMilitary" 部队双层,"SAR" 港澳特区号牌,"Trainning" 教练车号牌
        // "Personal" 个性号牌,"Agri" 农用牌,"Embassy" 使馆号牌,"Moto" 摩托车号牌,"Tractor" 拖拉机号牌,"Other" 其他号牌
        // HumanFace Category:"Normal" 普通人脸,"HideEye" 眼部遮挡,"HideNose" 鼻子遮挡,"HideMouth" 嘴部遮挡

        public short        wColorLogoIndex;                    // 车标索引
        public short 		wSubBrand;   						// 车辆子品牌 需要通过映射表得到真正的子品牌 映射表详见开发手册
        public byte 		byReserved1;
        public byte 		bPicEnble;							//是否有物体对应图片文件信息, 类型小bool, 取值0或者1
        public NET_PIC_INFO stPicInfo;							//物体对应图片信息
        public byte 		bShotFrame;							//是否是抓拍张的识别结果,  类型小bool, 取值0或者1
        public byte 		bColor;								//物体颜色(rgbaMainColor)是否可用, 类型小bool, 取值0或者1
        public byte 		byReserved2;
        public byte 		byTimeType;							//时间表示类型,详见 EM_TIME_TYPE 说明
        public NET_TIME_EX  stuCurrentTime;						//针对视频浓缩,当前时间戳（物体抓拍或识别时,会将此识别智能帧附在一个视频帧或jpeg图片中,此帧所在原始视频中的出现时间）
        public NET_TIME_EX  stuStartTime;						//开始时间戳（物体开始出现时）
        public NET_TIME_EX  stuEndTime;							//结束时间戳（物体最后出现时）
        public DH_RECT 		stuOriginalBoundingBox;				//包围盒(绝对坐标)
        public DH_RECT 		stuSignBoundingBox;					//车标坐标包围盒
        public int 			dwCurrentSequence;					//当前帧序号（抓下这个物体时的帧）
        public int 			dwBeginSequence;					//开始帧序号（物体开始出现时的帧序号）
        public int 			dwEndSequence;						//结束帧序号（物体消逝时的帧序号）
        public long 		nBeginFileOffse;					//开始时文件偏移,单位:字（物体开始出现时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public long 		nEndFileOffset;						//结束时文件偏移,单位:字节（物体消逝时,视频帧在原始视频文件中相对于文件起始处的偏移）
        public byte[] 		byColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];			//物体颜色相似度,取值范围：0-100,数组下标值代表某种颜色,详见 EM_COLOR_TYPE
        public byte[] 		byUpperBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];	//上半身物体颜色相似度(物体类型为人时有效)
        public byte[] 		byLowerBodyColorSimilar = new byte[EM_COLOR_TYPE.NET_COLOR_TYPE_MAX];	//下半身物体颜色相似度(物体类型为人时有效)
        public int 			nRelativeID;						//相关物体ID
        public byte[] 		szSubText = new byte[20];			//"ObjectType"为"Vehicle"或者"Logo"时,表示车标下的某一车系,比如奥迪A6L,由于车系较多,SDK实现时透传此字段,设备如实填写。
        public short 		wBrandYear;   						// 车辆品牌年款 需要通过映射表得到真正的年款 映射表详见开发手册

        public NET_MSG_OBJECT()
        {
            if(Utils.getOsName().equals("win")) {
                // 强制采用最大四字节对其
                setAlignType(ALIGN_GNUC);
            }
        }
    }
    //颜色类型
    public static class EM_COLOR_TYPE extends Structure
    {
        public static final int NET_COLOR_TYPE_RED = 0;//红色
        public static final int NET_COLOR_TYPE_YELLOW = 1;//黄色
        public static final int NET_COLOR_TYPE_GREEN = 2; //绿色
        public static final int NET_COLOR_TYPE_CYAN = 3; //青色
        public static final int NET_COLOR_TYPE_BLUE = 4; //蓝色
        public static final int NET_COLOR_TYPE_PURPLE = 5; //紫色
        public static final int NET_COLOR_TYPE_BLACK = 6; //黑色
        public static final int NET_COLOR_TYPE_WHITE = 7; //白色
        public static final int NET_COLOR_TYPE_MAX = 8;
    }
    public static class DH_RECT extends Structure
    {
        public NativeLong left;
        public NativeLong  	top;
        public NativeLong  	right;
        public NativeLong  	bottom;
    }
    // 图片分辨率
    public static class NET_RESOLUTION_INFO extends Structure
    {
        public short snWidth;//宽
        public short snHight;//高
    }
    // 事件上报携带卡片信息
    public static class EVENT_CARD_INFO extends Structure
    {
        public byte[]              szCardNumber = new byte[NET_EVENT_CARD_LEN];// 卡片序号字符串
        public byte[]              bReserved = new byte[32];                   // 保留字节,留待扩展.
    }
    //卡口事件专用定制上报内容，定制需求增加到Custom下
    public static class EVENT_JUNCTION_CUSTOM_INFO extends Structure
    {
        public EVENT_CUSTOM_WEIGHT_INFO    stuWeightInfo;      				   // 原始图片信息
        public byte[]					   bReserved = new byte[60];		   // 预留字节
    }
    //建委地磅定制称重信息
    public static class EVENT_CUSTOM_WEIGHT_INFO extends Structure
    {
        public int        dwRoughWeight;                    				   // 毛重,车辆满载货物重量。单位KG
        public int        dwTareWeight;                     				   // 皮重,空车重量。单位KG
        public int        dwNetWeight;                      				   // 净重,载货重量。单位KG
        public byte[]	  bReserved = new byte[28];					   	 	   // 预留字节
    }
    // GPS信息
    public static class NET_GPS_INFO extends Structure
    {
        public int                  nLongitude;         	    // 经度(单位是百万分之一度)
        // 西经：0 - 180000000				实际值应为: 180*1000000 – dwLongitude
        // 东经：180000000 - 360000000		实际值应为: dwLongitude – 180*1000000
        // 如: 300168866应为（300168866 - 180*1000000）/1000000 即东经120.168866度
        public int					nLatidude;                  // 纬度(单位是百万分之一度)
        // 南纬：0 - 90000000				实际值应为: 90*1000000 – dwLatidude
        // 北纬：90000000 – 180000000		实际值应为: dwLatidude – 90*1000000
        // 如: 120186268应为 (120186268 - 90*1000000)/1000000 即北纬30. 186268度
        public double              dbAltitude;                  // 高度,单位为米
        public double              dbSpeed;                     // 速度,单位km/H
        public double              dbBearing;                   // 方向角,单位°
        public byte[]              bReserved = new byte[8];     // 保留字段

        public NET_GPS_INFO()
        {
            if(Utils.getOsName().equals("win")) {
                // 强制采用最大四字节对齐
                setAlignType(ALIGN_GNUC);
            }
        }
    }
    // TrafficCar 交通车辆信息
    public static class DEV_EVENT_TRAFFIC_TRAFFICCAR_INFO extends Structure
    {
        public byte[] 			szPlateNumber = new byte[32];	//车牌号码
        public byte[] 			szPlateType = new byte[32];		//号牌类型参见VideoAnalyseRule中车牌类型定义
        public byte[] 			szPlateColor = new byte[32];	//车牌颜色"Blue","Yellow",
        public byte[] 			szVehicleColor = new byte[32];	//车身颜色"White",
        public int 				nSpeed;							//速度单位Km/H
        public byte[] 			szEvent = new byte[64];			//触发的相关事件参见事件列表Event
        public byte[] 			szViolationCode = new byte[32]; //违章代码详见TrafficGlobal.ViolationCode
        public byte[] 			szViolationDesc = new byte[64]; //违章描述
        public int 				nLowerSpeedLimit;				//速度下限
        public int 				nUpperSpeedLimit;				//速度上限
        public int 				nOverSpeedMargin;				//限高速宽限值单位：km/h
        public int 				nUnderSpeedMargin;				//限低速宽限值单位：km/h
        public int 				nLane;							//车道参见事件列表EventList中卡口和路口事件。
        public int 				nVehicleSize;					//车辆大小,-1表示未知,否则按位
        // 第0位:"Light-duty", 小型车
        // 第1位:"Medium", 中型车
        // 第2位:"Oversize", 大型车
        // 第3位:"Minisize", 微型车
        // 第4位:"Largesize", 长车
        public float 			fVehicleLength;					//车辆长度单位米
        public int 				nSnapshotMode;					//抓拍方式0-未分类,1-全景,2-近景,4-同向抓拍,8-反向抓拍,16-号牌图像
        public byte[] 			szChannelName = new byte[32];	//本地或远程的通道名称,可以是地点信息来源于通道标题配置ChannelTitle.Name
        public byte[] 			szMachineName = new byte[256];	// 本地或远程设备名称来源于普通配置General.MachineName
        public byte[] 			szMachineGroup = new byte[256]; // 机器分组或叫设备所属单位默认为空,用户可以将不同的设备编为一组,便于管理,可重复。
        public byte[] 			szRoadwayNo = new byte[64];		// 道路编号
        public byte[] 			szDrivingDirection = new byte[3*NET_MAX_DRIVINGDIRECTION];//
        // 行驶方向 , "DrivingDirection" : ["Approach", "上海", "杭州"],
        // "Approach"-上行,即车辆离设备部署点越来越近；"Leave"-下行,
        // 即车辆离设备部署点越来越远,第二和第三个参数分别代表上行和
        // 下行的两个地点
        public Pointer 			szDeviceAddress;				// 设备地址,OSD叠加到图片上的,来源于配 置TrafficSnapshot.DeviceAddress,'\0'结束
        public byte[] 			szVehicleSign = new byte[32];	// 车辆标识,例如
        public NET_SIG_CARWAY_INFO_EX stuSigInfo;				// 由车检器产生抓拍信号冗余信息
        public Pointer 			szMachineAddr;					// 设备部署地点
        public float 			fActualShutter;					// 当前图片曝光时间,单位为毫秒
        public byte 			byActualGain;					// 当前图片增益,范围为0~100
        public byte 			byDirection;					// 车道方向,0-南向北1-西南向东北2-西向东
        public byte[] 			byReserved = new byte[2];
        public Pointer 			szDetailedAddress;				// 详细地址,作为szDeviceAddress的补充
        public byte[] 			szDefendCode = new byte[NET_COMMON_STRING_64];//图片防伪码
        public int 				nTrafficBlackListID;			// 关联黑名单数据库记录默认主键ID,0,无效；>0,黑名单数据记录
        public NET_COLOR_RGBA 	stuRGBA;						// 车身颜色RGBA
        public NET_TIME 		stSnapTime;						// 抓拍时间
        public int 				nRecNo;							// 记录编号
        public byte[] 			szCustomParkNo= new byte[NET_COMMON_STRING_32+1];// 自定义车位号（停车场用）
        public byte[] 			byReserved1 = new byte[3];
        public int 				nDeckNo;						// 车板位号
        public int 				nFreeDeckCount;					// 空闲车板数量
        public int 				nFullDeckCount;					// 占用车板数量
        public int 				nTotalDeckCount;				// 总共车板数量
        public byte[] 			szViolationName = new byte[64]; // 违章名称
        public int 				nWeight;						// 车重(单位Kg), 类型为unsigned int

        public byte[]      		szCustomRoadwayDirection = new byte[32];// 自定义车道方向,byDirection为9时有效
        public byte        		byPhysicalLane; 				// 物理车道号,取值0到5
        public byte[]      		byReserved2 = new byte[3];
        public int 		   		emMovingDirection; 				// 车辆行驶方向 EM_TRAFFICCAR_MOVE_DIRECTION
        public NET_TIME			stuEleTagInfoUTC;			    // 对应电子车牌标签信息中的过车时间(ThroughTime)
        public byte[]      		bReserved = new byte[552]; 		// 保留字节,留待扩展.
    }
    // 车检器冗余信息
    public static class NET_SIG_CARWAY_INFO_EX extends Structure
    {
        public byte[] byRedundance = new byte[8];//由车检器产生抓拍信号冗余信息
        public byte[] bReserved = new byte[120];//保留字段
    }

    // 颜色RGBA
    public static class NET_COLOR_RGBA extends Structure
    {
        public int nRed;//红
        public int nGreen;//绿
        public int nBlue;//蓝
        public int nAlpha;//透明

        @Override
        public String toString() {
            return "[" + nRed + " " + nGreen + " " + nBlue + " " + nAlpha + "]";
        }
    }
    // CFG_NET_TIME 时间
    public static class CFG_NET_TIME extends Structure {
        public int                 	nStructSize;
        public int					dwYear;					// 年
        public int					dwMonth;				// 月
        public int					dwDay;					// 日
        public int					dwHour;					// 时
        public int					dwMinute;				// 分
        public int					dwSecond;				// 秒

        public CFG_NET_TIME() {
            this.nStructSize = this.size();
        }
    }
    // 时间
    public static class NET_TIME extends Structure {
        public int                dwYear;                   // 年
        public int                dwMonth;                  // 月
        public int                dwDay;                    // 日
        public int                dwHour;                   // 时
        public int                dwMinute;                 // 分
        public int                dwSecond;                 // 秒

        public NET_TIME() {
            this.dwYear = 0;
            this.dwMonth = 0;
            this.dwDay = 0;
            this.dwHour = 0;
            this.dwMinute = 0;
            this.dwSecond = 0;
        }

        public void setTime(int year, int month, int day, int hour, int minute, int second) {
            this.dwYear = year;
            this.dwMonth= month;
            this.dwDay= day;
            this.dwHour=hour;
            this.dwMinute=minute;
            this.dwSecond=second;
        }

        public NET_TIME(NET_TIME other) {
            this.dwYear = other.dwYear;
            this.dwMonth = other.dwMonth;
            this.dwDay = other.dwDay;
            this.dwHour = other.dwHour;
            this.dwMinute = other.dwMinute;
            this.dwSecond = other.dwSecond;
        }

        //用于列表中显示
        public String toStringTime() {
            return  String.format("%02d/%02d/%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        public String toStringTimeEx() {
            return  String.format("%02d-%02d-%02d %02d:%02d:%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }

        @Override
        public String toString() {
            return String.format("%02d%02d%02d%02d%02d%02d", dwYear, dwMonth, dwDay, dwHour, dwMinute, dwSecond);
        }
    }
    //物体对应图片文件信息
    public static class NET_PIC_INFO extends Structure
    {
        public int 			dwOffSet;				 // 文件在二进制数据块中的偏移位置,单位:字节
        public int 			dwFileLenth;			 // 文件大小,单位:字节
        public short 		wWidth;					 // 图片宽度,单位:像素
        public short 		wHeight;				 // 图片高度,单位:像素
        public Pointer 		pszFilePath;			 // 鉴于历史原因,该成员只在事件上报时有效， char *
        // 文件路径
        // 用户使用该字段时需要自行申请空间进行拷贝保存
        public byte 		bIsDetected;			 // 图片是否算法检测出来的检测过的提交识别服务器时,
        // 则不需要再时检测定位抠图,1:检测过的,0:没有检测过
        public byte[] 		bReserved = new byte[3]; // 12<--16
        public int			nFilePathLen;			 // 文件路径长度 既pszFilePath 用户申请的大小
        public NET_POINT 	stuPoint;			 	 // 小图左上角在大图的位置，使用绝对坐标系
    }
    //二维空间点
    public static class NET_POINT extends Structure
    {
        public short nx;
        public short ny;
    }
}
