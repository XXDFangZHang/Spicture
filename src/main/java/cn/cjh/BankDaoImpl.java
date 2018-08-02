package cn.cjh;

import java.sql.Connection;

public class BankDaoImpl extends  BaseDao {

    public void   fromToBank(String name, double money){
        try {
            String sql="UPDATE bank  SET money=money+? WHERE `name`=?";
            Object [] params={money,name};
            int num=executeUpdate(sql,params);
            if (num>0){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
