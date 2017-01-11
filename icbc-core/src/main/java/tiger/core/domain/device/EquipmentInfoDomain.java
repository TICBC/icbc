package tiger.core.domain.device;

import tiger.core.domain.BaseDomain;

/**
 * Created by archer on 2017/1/9.
 */
public class EquipmentInfoDomain extends BaseDomain{
    private String equSerialNum;
    private String cpuNum;
    private String memorySize;
    private String osVersions;
    private String browserVersions;
    private String browserLanguage;
    private String screenResolution;
    private String appVersions;
    private String factoryInfo;

    public EquipmentInfoDomain() {
    }


    public String getEquSerialNum() {
        return this.equSerialNum;
    }

    public void setEquSerialNum(String equSerialNum) {
        this.equSerialNum = equSerialNum == null?null:equSerialNum.trim();
    }

    public String getCpuNum() {
        return this.cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum == null?null:cpuNum.trim();
    }

    public String getMemorySize() {
        return this.memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize == null?null:memorySize.trim();
    }

    public String getOsVersions() {
        return this.osVersions;
    }

    public void setOsVersions(String osVersions) {
        this.osVersions = osVersions == null?null:osVersions.trim();
    }

    public String getBrowserVersions() {
        return this.browserVersions;
    }

    public void setBrowserVersions(String browserVersions) {
        this.browserVersions = browserVersions == null?null:browserVersions.trim();
    }

    public String getBrowserLanguage() {
        return this.browserLanguage;
    }

    public void setBrowserLanguage(String browserLanguage) {
        this.browserLanguage = browserLanguage == null?null:browserLanguage.trim();
    }

    public String getScreenResolution() {
        return this.screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution == null?null:screenResolution.trim();
    }

    public String getAppVersions() {
        return this.appVersions;
    }

    public void setAppVersions(String appVersions) {
        this.appVersions = appVersions == null?null:appVersions.trim();
    }

    public String getFactoryInfo() {
        return this.factoryInfo;
    }

    public void setFactoryInfo(String factoryInfo) {
        this.factoryInfo = factoryInfo == null?null:factoryInfo.trim();
    }
}
