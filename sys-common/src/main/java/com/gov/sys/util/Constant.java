package com.gov.sys.util;

/**
 *
 * ClassName: Constant
 * Description: System Constant
 * Author: Gavin
 * Date: Feb 2, 2016 1:08:15 PM
 */
public interface Constant {

    public static final Integer PAGE_SIZE = 10; // Select2模糊查詢時最多顯示的記錄數

    public static final String COMPLETE_STRING ="COMPLETE";

    public static final String TERMINATE_STRING ="TERMINATE";

    public static final String CANCEL_STRING ="CANCEL";

    public static final String CREATE_STRING = "CREATE";

    public static final String NONE_STRING = "NONE";

    public static final String POST_CREATE_STRING = "POST_CREATE";

    public static final String FEE_STRING = "FEE";

    // ==================== 代碼狀態 ====================//
    public static final String CODE_STATUS_A = "A"; // 有效
    public static final String CODE_STATUS_C = "C"; // 失效

    // ==================== 服務狀態 ====================//
    public static final String SERVICE_STATUS_A = "A"; // 有效
    public static final String SERVICE_STATUS_C = "C"; // 失效
    public static final String SERVICE_IS_ENQ_Y = "Y"; // 可以顯示
    public static final String SERVICE_IS_ENQ_N = "N"; // 不可以顯示

    // ==================== 操作類型狀態 ====================//
    public static final String PROCESS_STATUS_A = "A"; // 有效
    public static final String PROCESS_STATUS_C = "C"; // 失效
    public static final String PROCESS_IS_ENQ_Y = "Y"; // 可以顯示
    public static final String PROCESS_IS_ENQ_N = "N"; // 不可以顯示

    // ==================== VTA Begin ====================//

    // 型號認可檢驗結果
    public static final String VTA_INSP_RESULT_A = "A";     // 合格
    public static final String VTA_INSP_RESULT_R = "R";     // 不合格
    public static final String VTA_INSP_RESULT_N = "N";     // 缺席

    // 型號認可狀態
    public static final String VTA_STATUS_A = "A"; // 合格
    public static final String VTA_STATUS_C = "C"; // 失效
    public static final String VTA_STATUS_X = "X"; // 廣東車專用
    public static final String VTA_STATUS_P = "P"; // 擱置
    public static final String VTA_STATUS_T = "T"; // 暫時
    public static final String VTA_STATUS_D = "D"; // 已刪除, 從AS400遷移來的數據

    public static final String VTA_SERVICE_STATUS_EXPIRED = "E"; //已過五年期
    public static final String VTA_SERVICE_STATUS_ACTIVE = "A";  //已通過文件審批,但未生效,如未完成實物檢驗
    public static final String VTA_SERVICE_STATUS_VALID = "V";   //已生效,已通過文件審批,已完成實物檢驗
    public static final String VTA_SERVICE_STATUS_INVALID = "I"; //其他無效狀態
    public static final String VTA_SERVICE_STATUS = "serviceStatus";


    // 廷期狀態
    public static final String VTA_DEFERRAL_STATUS_A = "A"; // 有效
    public static final String VTA_DEFERRAL_STATUS_D = "D"; // 已刪除

    // 輪胎規格狀態
    public static final String TYRE_STATUS_O = "O"; // 原裝
    public static final String TYRE_STATUS_I = "I"; // 個別
    public static final String TYRE_STATUS_C = "C"; // 取消

    // 配件規格狀態
    public static final String ACSY_STATUS_O = "O"; // 原裝
    public static final String ACSY_STATUS_I = "I"; // 個別
    public static final String ACSY_STATUS_C = "C"; // 取消

    // 引擎
    public static final String ENGINE_STATUS_A = "A"; // 有效
    public static final String ENGINE_STATUS_C = "C"; // 取消
    public static final String ENGINE_STATUS_H = "H"; // 歷史
    public static final String EXT_ENGINE_STATUS_O = "O"; // 原裝
    public static final String EXT_ENGINE_STATUS_I = "I"; // 個別

    // 車身
    public static final String VIN_STATUS_A = "A"; // 有效
    public static final String VIN_STATUS_C = "C"; // 取消
    public static final String VIN_STATUS_H = "H"; // 歷史
    public static final String EXT_VIN_STATUS_O = "O"; // 原裝
    public static final String EXT_VIN_STATUS_I = "I"; // 個別

    // 制動系統
    public static final String EXT_FBRAKE_STATUS_O = "O"; // 原裝
    public static final String EXT_FBRAKE_STATUS_I = "I"; // 個別
    public static final String EXT_RBRAKE_STATUS_O = "O"; // 原裝
    public static final String EXT_RBRAKE_STATUS_I = "I"; // 個別

    // 照片來源
    public static final String PHOTO_SOURCE_C = "C"; // 工控
    public static final String PHOTO_SOURCE_U = "U"; // 用戶上傳

    // 照片狀態
    public static final String PHOTO_STATUS_A = "A"; // 有效
    public static final String PHOTO_STATUS_D = "D"; // 已刪除

    // 廢氣報告待批
    public static final String EXHAUST_RPT_RDY_Y = "Y"; // 已批
    public static final String EXHAUST_RPT_RDY_N = "N"; // 未批

    // 認可卷宗已在汽車檢驗中心
    public static final String REF_DOC_RDY_Y = "Y"; // 是
    public static final String REF_DOC_RDY_N = "N"; // 否

    // ==================== VTA End ====================//

    // ==================== Vehicle Status ====================//
    public static final String VEH_STATUS_ACTIVE = "A";
    public static final String VEH_STATUS_CANCEL = "C";
    public static final String VEH_STATUS_SUSPEND = "S";
    public static final String VEH_STATUS_DELETED = "D";
    public static final String VEH_STATUS_TEMP = "T";
    public static final String VEH_STATUS_HISTORY = "H";
    public static final String VEH_STATUS_PENDING = "P";
    //=====================報表縮寫============================//
    public static final String VEH_REG_DOC="D";//登記摺
    public static final String VEH_REG_NOTICE="N"; //登記局憑單
    public static final String VEH_CONF_REG_DOC="S"; //登記局憑單
    

    //=====================的士狀態============================//
    public static final String TAXI_STATUS_A ="A";
    public static final String TAXI_STATUS_C ="C";
    
    
    //=====================的士期限============================//
    public static final Integer TAXILICTERM =999;

    //=====================的士牌照狀態=========================//

    public static final String TAXI_LIC_STATUS_A ="A";
    public static final String TAXI_LIC_STATUS_C ="C";

  //=====================的士續期============================//
    public static final String TAXI_RENEW_STATUS_A ="A";
    public static final String TAXI_RENEW_STATUS_H ="H";
    public static final String TAXI_RENEW_STATUS_D ="D";

    //=====================的士持牌人狀態============================//
    public static final String TAXI_HOLDER_STATUS_A ="A";
    public static final String TAXI_HOLDER_STATUS_C ="C";

  //=====================的士按揭狀態============================//
    public static final String TAXI_MORTGAGE_STATUS_A = "A";
    public static final String TAXI_MORTGAGE_STATUS_C = "C";

    // ==================== transaction Status ====================//
    public static final String TRANS_STATUS_ACTIVE = "A";
    public static final String TRANS_STATUS_DELETED = "D";
    public static final String TRANS_STATUS_COMPLETE = "F";
    public static final String TRANS_STATUS_CANCEL= "C";
    public static final String TRANS_STATUS_PAID= "P";

    // ==================== transaction Status ====================//
    public static final String BOOLEAN_TRUE = "Y";
    public static final String BOOLEAN_FALSE = "N";

    // ==================== Owner Status ====================//
    public static final String OWNER_STATUS_ACTIVE = "A";
    public static final String OWNER_STATUS_TEMP = "T";
    public static final String OWNER_STATUS_HISTORY = "H";
    public static final String OWNER_STATUS_CANCEL = "C";
    public static final String OWNER_STATUS_DELETED = "D";

    public static final String OWNER_SOURCE_DSAT="DSAT";
    public static final String OWNER_SOURCE_DSAJ="DSAJ";

    // ==================== Personal Alert Status ====================//
    public static final String PSN_ALERT_ACTIVE = "A";
    public static final String PSN_ALERT_FINISH = "F";


    // ==================== Vehicle Alert Status ====================//
    public static final String VEH_ALERT_ACTIVE = "P";
    public static final String VEH_ALERT_FINISH = "F";

    public static final String VEH_ALERT_WARN = "V";
    public static final String VEH_ALERT_REJECT = "R";
    public static final String VEH_ALERT_AUTHORIZE = "A";

   // ==================== Alert Code Status ====================//
    public static final String ALERT_CODE_STATUS_ACTIVE = "A";
    public static final String ALERT_CODE_STATUS_CANCEL = "C";
    public static final String ALERT_CODE_STATUS_HISTORY = "H";

    // ==================== Alert Source ====================//
    public static final String ALERT_SOURCE_FSM = "FSM";
    public static final String ALERT_SOURCE_DSAT = "DSAT";


    // ==================== Vehicle Switch Plate type====================//
    public static final String SWITCH_TYPE_1 = "1";
    public static final String SWITCH_TYPE_2 = "2";
    public static final String SWITCH_TYPE_3 = "3";
    public static final String SWITCH_TYPE_4 = "4";
    public static final String SWITCH_TYPE_5 = "5";
    public static final String SWITCH_TYPE_6 = "6";
    public static final String SWITCH_TYPE_7 = "7";
    public static final String SWITCH_TYPE_8 = "8";

    public static final String SWITCH_ACCEPT = "1";
    public static final String SWITCH_TRANSFER = "2";

    public static final String SWITCH_ACTION_1 = "1";
    public static final String SWITCH_ACTION_2 = "2";
    public static final String SWITCH_ACTION_3 = "3";

    public static final String SWITCH_STATUS_A = "A";
    public static final String SWITCH_STATUS_D = "D";//第一階段終止
    public static final String SWITCH_STATUS_C = "C";//取消
    public static final String SWITCH_STATUS_F = "F";//完成
    public static final String SWITCH_STATUS_P = "P";//第一階段完成
    public static final String SWITCH_STATUS_R = "R";//待審批
    public static final String SWITCH_STATUS_N = "N";//審批不通過
    public static final String SWITCH_STATUS_T = "T";//待繳行車稅
    public static final String SWITCH_STATUS_S = "S";//第二階段終止

    // ==================== EX Plate Status ====================//
    public static final String EX_STATUS_G = "G";//正常
    public static final String EX_STATUS_W = "W";//遺失
    public static final String EX_STATUS_B = "B";//損毀

    public static final String EX_PLATE_PREFIX = "EX";

    //======= 購買車牌的狀態 ============//
    public static final String MOUNT_PLATE_STATUS_USED="U";
    public static final String MOUNT_PLATE_STATUS_PAID="P";

    //======= 車牌的狀態 ============//
    public static final String PLATE_STATUS_TEMP="T";
    public static final String PLATE_STATUS_PAID="P";
    public static final String PLATE_STATUS_USED="U";

    //======= 車牌種類 ============//
    public static final String PLATE_TYPE_PAID="P"; //P＝購買車牌
    public static final String PLATE_TYPE_NORMAL="N";//N＝正式車牌（新車註冊抽籤之用）
    public static final String PLATE_TYPE_EBID="E";//E＝競投車牌
    public static final String PLATE_TYPE_GOV="O";//O＝政府車號
    public static final String PLATE_TYPE_RESERVE="R";//R＝預留機密
    public static final String PLATE_TYPE_CUSTOMIZE="C";//C = 專有車牌


    //======= PARAM NAME============//
    public static final String PARAM_INP_S_APP_PERIOD_059 = "INP_S_APP_PERIOD_059";
    public static final String PARAM_INP_S_APP_PERIOD_SOR = "INP_S_APP_PERIOD_SOR";
    public static final String PARAM_INP_S_VALID_PERIOD = "INP_S_VALID_PERIOD";
    public static final String PARAM_INP_FIRST_REG_VALID_PERIOD = "VEH_EX_EXTEND_LIMIT";
    public static final String PARAM_INP_POST_COMP_URL = "INP_POST_COMPLETE_URL";
    public static final String PARAM_INP_SCH_NO_ADJUST_DAY = "INP_SCH_NO_ADJUST_DAY";
    public static final String PARAM_INP_SCH_PERIOD_DURATION = "INP_SCH_PERIOD_DURATION";

    public static final String LANGUAGE_CN = "C";
    public static final String LANGUAGE_PT = "P";

    //======= 違例付費的狀態 ============//
    /** 違例行車稅繳付情況。舊AS400: S=己付, N=未付；新VSS: Y=己付, N=未付  */
    public static final String ALERT_CT_PAY_STATUS_Y="Y";
    /** 違例行車稅繳付情況。舊AS400: S=己付, N=未付；新VSS: Y=己付, N=未付  */
    public static final String ALERT_CT_PAY_STATUS_N="N";
    /** 違例行車稅編碼  78 */
    public static final String ALERT_CODE_ID_78 = "78";
    public static final int ALERT_CODE_34 = 34;
    public static final int ALERT_CODE_59 = 59;
    public static final int ALERT_CODE_78 = 78;
    public static final int ALERT_CODE_104 = 104;
    public static final int ALERT_CODE_105 = 105;
    public static final int ALERT_CODE_106 = 106;

    public static final String MSG_TYPE_WARNING = "warning";
    public static final String MSG_TYPE_SUCCESS = "success";
    public static final String MSG_TYPE_ERROR = "error";

    //======= unMountReason ============//
    public static final int UNMOUNT_PLATE_WHILE_CANCEL_VEH = 1;
    public static final int UNMOUNT_PLATE_WHILE_SWITCH_PLATE = 2;

    //======= 行車稅 ============//
    public static final String  CT_PAYMENT_SERVICE_RENEWAL = "R";
    public static final String  CT_PAYMENT_SERVICE_REISSUE = "2";

    public static final String  OUTSIDE_GOV_VEH_USAGE_CODE = "O4";
    public static final String  INSIDE_GOV_VEH_USAGE_CODE = "O5";
    
    //======= 車輛分類 ============//
    public static final String 	OTH_TAG_TXI="TXI";//的士分類
    public static final String 	OTH_TAG_BUS="BUS";//巴士分類
    public static final String 	OTH_TAG_OFFICIAL_INSIDE="MOF";//境內公務車分類
    public static final String 	OTH_TAG_OFFICIAL_OUTSIDE="OOF";//境外公務車分類
    
    public static final int  OTH_RIVM_Y=1;//免稅車
    public static final String  OTH_STATUS_C="C";//失效狀態
    public static final String  OTH_STATUS_A="A";//有效狀態
    public static final String  OTH_STATUS_D="D";//錯誤刪除
    public static final String  OTH_BUS_STATUS_O="O";//營運巴士
    public static final String  OTH_BUS_STATUS_S="S";//備用巴士
    public static final String  OTH_BUS_M="M";//中巴
    public static final String  OTH_BUS_L="L";//大巴
    public static final String  OTH_BUS_X="X";//超長巴
    public static final String  OTH_BUS_S="S";//小巴
    public static final String  OTH_BUS_Y="Y";
    public static final String  OTH_BUS_N="N";
    public static final String  OTH_SCRAPED_PRINT_1="1";
    public static final String  OTH_SCRAPED_PRINT_0="0";

    //======= SP 代碼 ============//
    public static final String SP_NON_DAY_END_ZONE = "not.enable.dayEnd.zone";

}