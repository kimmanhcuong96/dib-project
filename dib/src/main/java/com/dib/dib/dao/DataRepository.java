package com.dib.dib.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dib.dib.model.DataRawFormat;

public interface DataRepository extends CrudRepository<DataRawFormat, Integer> {

	@Query("SELECT u FROM DataRawFormat u Where ((lower(u.tenTimKiem) LIKE lower(CONCAT('%',:name,'%'))) AND "
			+ "(lower(u.queQuanXa) LIKE lower(CONCAT('%',:ward,'%'))) AND"
			+ "(lower(u.queQuanTinh) LIKE lower(CONCAT('%',:city,'%'))) AND"
			+ "(lower(u.queQuanHuyen) LIKE lower(CONCAT('%',:district,'%'))) AND"
			+ "(u.namSinh >= :birthDayStart AND u.namSinh <= :birthDayEnd) AND"
			+ "(u.namDiB >= :goBStart AND u.namDiB <= :goBEnd) AND"
			+ "(lower(u.biDanh) LIKE lower(CONCAT('%',:aliasName,'%')))) ORDER BY u.ten ASC")
	public List<DataRawFormat> findInformation( 
			  @Param("name") String name, @Param("ward") String ward, @Param("aliasName") String aliasName,
			  @Param("city") String city, @Param("district") String district,
			  @Param("birthDayStart") Integer birthDayStart, @Param("birthDayEnd") Integer birthDayEnd,
			  @Param("goBStart") Integer goBStart, @Param("goBEnd") Integer goBEnd
			  );
}
