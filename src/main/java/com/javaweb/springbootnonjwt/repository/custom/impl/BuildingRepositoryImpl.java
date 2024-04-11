package com.javaweb.springbootnonjwt.repository.custom.impl;

import com.javaweb.springbootnonjwt.builder.BuildingSearchBuilder;
import com.javaweb.springbootnonjwt.repository.BuildingRepository;
import com.javaweb.springbootnonjwt.repository.custom.BuildingRepositoryCustom;
import com.javaweb.springbootnonjwt.repository.entity.BuildingEntity;
import com.javaweb.springbootnonjwt.utils.ObjectUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;



@Repository
@Primary
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder finalSql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.ward, b.street, b.numberofbasement," +
                " b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");

        StringBuilder whereSql = new StringBuilder("WHERE 1 = 1 ");
        StringBuilder joinSql = new StringBuilder();
        querySqlJoin(buildingSearchBuilder, joinSql);
        querySqlWhereNomal(buildingSearchBuilder, whereSql);
        querySqlWhereSpecial(buildingSearchBuilder, whereSql);

        finalSql.append(joinSql);
        finalSql.append(whereSql);
        finalSql.append("GROUP BY b.id");

        Query query = entityManager.createNativeQuery(finalSql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private void querySqlJoin(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sb){
        Long staffId = buildingSearchBuilder.getStaffId();
        if(ObjectUtil.checkObject(staffId)){
            sb.append("INNER JOIN assignmentbuilding a ON a.buildingid = b.id ");
        }
        Integer areaFrom = buildingSearchBuilder.getAreaFrom();
        Integer areaTo = buildingSearchBuilder.getAreaTo();
        if(ObjectUtil.checkObject(areaFrom) || ObjectUtil.checkObject(areaTo)){
            sb.append("INNER JOIN rentarea r ON r.buildingid = b.id ");
        }
        List<String> typeCodes = buildingSearchBuilder.getTypeCode();
        if(typeCodes != null && typeCodes.size() != 0){
            sb.append("INNER JOIN buildingrenttype brt on brt.buildingid = b.id " +
                    "INNER JOIN renttype rt on rt.id = brt.renttypeid ");
        }
    }
    private void querySqlWhereNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sb){
        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item : fields){
                item.setAccessible(true);
                String fieldName = item.getName();
                Object value = item.get(buildingSearchBuilder);
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") && !fieldName.startsWith("rentPrice")){
                    if(ObjectUtil.checkObject(value)){
                        if(ObjectUtil.isNumber(value)){
                            sb.append("AND b." + fieldName + " = " + value + " ");
                        }else {
                            sb.append("AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void querySqlWhereSpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sb){
        Long staffId = buildingSearchBuilder.getStaffId();
        if(ObjectUtil.checkObject(staffId)){
            sb.append("AND a.staffid = " + staffId + " ");
        }
        Integer areaFrom = buildingSearchBuilder.getAreaFrom();
        if(ObjectUtil.checkObject(areaFrom)){
            sb.append("AND r.value >= " + areaFrom + " ");
        }
        Integer areaTo = buildingSearchBuilder.getAreaTo();
        if(ObjectUtil.checkObject(areaTo)){
            sb.append("AND r.value <= " + areaTo + " ");
        }
        Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        if(ObjectUtil.checkObject(rentPriceFrom)){
            sb.append("AND b.rentprice >= " + rentPriceFrom + " ");
        }
        Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if(ObjectUtil.checkObject(rentPriceTo)){
            sb.append("AND b.rentprice <= " + rentPriceTo + " ");
        }
        List<String> typeCodes = buildingSearchBuilder.getTypeCode();
        if(typeCodes != null && typeCodes.size() != 0){
            sb.append("AND rt.code IN('").append(String.join("', '", typeCodes)).append("') ");
//            sb.append("AND (");
//            String sql = typeCodes.stream().map(it -> "rt.code like '%" + it + "%' ").collect(Collectors.joining("OR "));
//            sb.append(sql + ") ");
        }
    }
}
