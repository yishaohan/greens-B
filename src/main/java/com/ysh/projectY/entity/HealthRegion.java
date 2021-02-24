package com.ysh.projectY.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "health_region")
public class HealthRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`", insertable = false, length = 32, nullable = false)
    private int id;

    @Column(name = "`health_region_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '卫生局名称'")
    private String healthRegionName;

    @Column(name = "`province_id`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '省简称'")
    private String provinceId;

    @Column(name = "`province_name`", columnDefinition = "varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL comment '省名称'")
    private String provinceName;

    // 双向关联
    @OneToMany(mappedBy = "healthRegion", fetch = FetchType.LAZY)
    Set<BCSchoolsCOVID19> bcSchoolsCOVID19;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHealthRegionName() {
        return healthRegionName;
    }

    public void setHealthRegionName(String healthRegionName) {
        this.healthRegionName = healthRegionName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Set<BCSchoolsCOVID19> getBcSchoolsCOVID19() {
        return bcSchoolsCOVID19;
    }

    public void setBcSchoolsCOVID19(Set<BCSchoolsCOVID19> bcSchoolsCOVID19) {
        this.bcSchoolsCOVID19 = bcSchoolsCOVID19;
    }

    @Override
    public String toString() {
        return "HealthRegion{\n" +
                "id = " + id +
                ", \nhealthRegionName = '" + healthRegionName + '\'' +
                ", \nprovinceId = '" + provinceId + '\'' +
                ", \nprovinceName = '" + provinceName + '\'' +
                ", \nbcSchoolsCOVID19 = " + bcSchoolsCOVID19.getClass().getName() +
                "\n}";
    }
}
