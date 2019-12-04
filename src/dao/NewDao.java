package dao;

import com.cctv.bean.News;
import com.cctv.common.Untils;
import java.sql.PreparedStatement;

import java.sql.Connection;

public class NewDao {

//    增加新闻
    public boolean saveNews(News news){
        Connection conn = Untils.getConnection();
        String sql = "insert into news(nid,context) values(?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, news.getNid());
            stmt.setString(2, news.getContext());
            stmt.execute();
        } catch (Exception e) {
            System.out.println("保存留言信息失败。");
            e.printStackTrace();
            return false;
        } finally {
            Untils.release(null, stmt, conn);
        }

        return true;

    }




}
