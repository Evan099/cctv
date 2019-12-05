package dao;

import com.cctv.bean.News;
import com.cctv.common.Untils;
import sun.reflect.misc.ConstructorUtil;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewDao {

//    增加一条新闻
    public boolean saveNews(News news){
        Connection conn = Untils.getConnection();
        String sql = "insert into news(title,context) values(?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, news.getTitle());
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

//      查询所有新闻
    public List<News> getNewsList(){
        Connection conn = Untils.getConnection();
        String sql = "select * from news LIMIT 10";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<News> news = new ArrayList<>();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()){
                news.add(new News(rs.getInt("nid"),
                        rs.getString("title"),
                        rs.getString("context")
                        ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Untils.release(rs,stmt,conn);
        }
        return news;

    }





}
