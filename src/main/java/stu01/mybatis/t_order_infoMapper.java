package stu01.mybatis;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import stu01.model.t_order_info;
public interface t_order_infoMapper {
	int addOrder(@Param("title") String title_01,String order_no);
	int addOrder2(@Param("entity") t_order_info entity);
	List<t_order_info> select(@Param("title") String title,@Param("order_no") String order_no);
	List<t_order_info> select2(@Param("entity") t_order_info entity);

}
