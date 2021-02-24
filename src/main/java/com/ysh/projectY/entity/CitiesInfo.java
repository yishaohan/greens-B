package com.ysh.projectY.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "cities_info")
public class CitiesInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`city_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '城市名称'")
    private String cityName;

    @Column(name = "`city_ascii`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '城市ASCII'")
    private String cityAscii;

    @Column(name = "`province_abb`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '省份简称'")
    private String provinceAbb;

    @Column(name = "`province_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '省份名称'")
    private String provinceName;

    @Column(name = "`longitude`", columnDefinition = "decimal(10,6) NOT NULL comment '经度'")
    private Double longitude;

    @Column(name = "`latitude`", columnDefinition = "decimal(10,6) NOT NULL comment '纬度'")
    private Double latitude;

    @Column(name = "`population`", columnDefinition = "int NOT NULL comment '人口数量'")
    private int population;

    @Column(name = "`density`", columnDefinition = "double NOT NULL comment '密度'")
    private double density;

    @Column(name = "`timezone`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '时区'")
    private String timezone;

    @Column(name = "`ranking`", columnDefinition = "int NOT NULL comment '排名'")
    private int ranking;

    @Column(name = "`postal`", columnDefinition = "varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '邮政编码'")
    private String postal;

    @Column(name = "`city_id`", columnDefinition = "int NOT NULL comment '城市ID'")
    private int cityId;

    // 双向关联
    @OneToMany(mappedBy = "citiesInfo", fetch = FetchType.LAZY)
    Set<SchoolsInfo> schoolsInfo;

    // 双向关联
    @OneToMany(mappedBy = "citiesInfo", fetch = FetchType.LAZY)
    Set<SchoolDistrict> schoolDistricts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityAscii() {
        return cityAscii;
    }

    public void setCityAscii(String cityAscii) {
        this.cityAscii = cityAscii;
    }

    public String getProvinceAbb() {
        return provinceAbb;
    }

    public void setProvinceAbb(String provinceAbb) {
        this.provinceAbb = provinceAbb;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Set<SchoolsInfo> getSchoolsInfo() {
        return schoolsInfo;
    }

    public void setSchoolsInfo(Set<SchoolsInfo> schoolsInfo) {
        this.schoolsInfo = schoolsInfo;
    }

    public Set<SchoolDistrict> getSchoolDistricts() {
        return schoolDistricts;
    }

    public void setSchoolDistricts(Set<SchoolDistrict> schoolDistricts) {
        this.schoolDistricts = schoolDistricts;
    }

    @Override
    public String toString() {
        return "CitiesInfo{\n" +
                "id = " + id +
                ", \ncityName = '" + cityName + '\'' +
                ", \ncityAscii = '" + cityAscii + '\'' +
                ", \nprovinceAbb = '" + provinceAbb + '\'' +
                ", \nprovinceName = '" + provinceName + '\'' +
                ", \nlongitude = " + longitude +
                ", \nlatitude = " + latitude +
                ", \npopulation = " + population +
                ", \ndensity = " + density +
                ", \ntimezone = '" + timezone + '\'' +
                ", \nranking = " + ranking +
                ", \npostal = '" + postal + '\'' +
                ", \ncityId = " + cityId +
                ", \nschoolsInfo = " + schoolsInfo.getClass().getName() +
                ", \nschoolDistricts = " + schoolDistricts.getClass().getName() +
                "\n}";
    }
}
