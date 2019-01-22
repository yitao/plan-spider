package com.simile.plan.spider;

/**
 * Created by yitao on 2019/1/8.
 */
public class Job extends ToJsonEntity{
    private String title;
    private String salary;
    private String education;
    private String addr;
    private String company;
    private String industry;
    private String size;
    private String pubTime;

    private String detailUrl;


    public Job(String title, String salary, String education, String addr, String company, String industry, String size, String pubTime, String detailUrl) {
        this.title = title;
        this.salary = salary;
        this.education = education;
        this.addr = addr;
        this.company = company;
        this.industry = industry;
        this.size = size;
        this.pubTime = pubTime;
        this.detailUrl = detailUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }
}
