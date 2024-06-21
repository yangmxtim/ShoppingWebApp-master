package com.shoppingwebapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shoppingwebapp.Model.ChartData;

//@Repository
public interface ChartDataRepository extends JpaRepository<ChartData, Integer> {

	@Query("SELECT c FROM ChartData c WHERE c.chartName = :name AND c.month = :month")
	List<ChartData> findByNameAndMonth(@Param(value = "name") String name, @Param(value = "month") String month);

	@Query("SELECT c FROM ChartData c WHERE c.chartName = :name")
	List<ChartData> findByName(@Param(value = "name") String name);

	@Query("SELECT c.id FROM ChartData c WHERE c.chartName = :name AND c.month = :month")
	Integer getId(@Param(value = "name") String name, @Param(value = "month") String month);

	@Query(value = "SELECT * FROM\r\n" + "(SELECT '台北市' addr, COUNT(*) c FROM orderitem o\r\n"
			+ "LEFT JOIN product_detail pd on o.product_detail_id = pd.product_detail_id \r\n"
			+ "LEFT JOIN product p on p.product_id = pd.product_id\r\n" + "WHERE address LIKE '%台北%') as x,\r\n"
			+ "(SELECT '新北市' addr, COUNT(*) c FROM orderitem o\r\n"
			+ "LEFT JOIN product_detail pd on o.product_detail_id = pd.product_detail_id \r\n"
			+ "LEFT JOIN product p on p.product_id = pd.product_id\r\n" + "WHERE address LIKE '%新北%') as y,\r\n"
			+ "(SELECT '台中市' addr, COUNT(*) c FROM orderitem o\r\n"
			+ "LEFT JOIN product_detail pd on o.product_detail_id = pd.product_detail_id \r\n"
			+ "LEFT JOIN product p on p.product_id = pd.product_id\r\n" + "WHERE address LIKE '%台中%') as z,\r\n"
			+ "(SELECT '台南市' addr, COUNT(*) c FROM orderitem o\r\n"
			+ "LEFT JOIN product_detail pd on o.product_detail_id = pd.product_detail_id \r\n"
			+ "LEFT JOIN product p on p.product_id = pd.product_id\r\n" + "WHERE address LIKE '%台南%') as w,\r\n"
			+ "(SELECT '高雄市' addr, COUNT(*) c FROM orderitem o\r\n"
			+ "LEFT JOIN product_detail pd on o.product_detail_id = pd.product_detail_id \r\n"
			+ "LEFT JOIN product p on p.product_id = pd.product_id\r\n"
			+ "WHERE address LIKE '%高雄%') as q", nativeQuery = true)
	List<String> getChart6Data();
}