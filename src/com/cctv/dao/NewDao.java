package com.cctv.dao;

import com.cctv.bean.News;
import com.cctv.bean.PageEntity;
import com.cctv.common.Untils;

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
        String sql = "insert into news(title,context,coverbg) values(?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, news.getTitle());
            stmt.setString(2, news.getContext());
            stmt.setString(3, news.getCoverbg());
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
        String sql = "select * from news";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<News> news = new ArrayList<>();
        try{
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()){
                news.add(new News(rs.getInt("nid"),
                        rs.getString("title"),
                        rs.getString("context"),
                        rs.getString("coverbg")
                        ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Untils.release(rs,stmt,conn);
        }
        return news;

    }

//      查询单条新闻
    public List<News> getNewDetail(int nid){
        Connection conn = Untils.getConnection();
        String sql = "select * from news where nid = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        News news = null;
        List<News> newDetail = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nid);
            rs = stmt.executeQuery();
            while (rs.next()){
                news = new News();
                news.setTitle(rs.getString("title"));
                news.setContext(rs.getString("context"));
                news.setCoverbg(rs.getString("coverbg"));
            }
            newDetail.add(news);
        }catch (SQLException e){
            System.out.println("查询用户信息失败。");
            e.printStackTrace();
        }finally {
            Untils.release(rs,stmt,conn);
        }

        return newDetail;
    }

//      删除一条新闻
    public int delOneNew(int nid){
        Connection conn = Untils.getConnection();
        String sql = "delete from news where nid = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int isDelResult = 0;

        try{
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,nid);
            isDelResult = stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("删除信息失败。");
            e.printStackTrace();
        }finally {
            Untils.release(rs,stmt,conn);
        }

        return isDelResult;

    }

//      修改一条新闻
    public boolean changeOneNew(News news){
        Connection conn = Untils.getConnection();
        String sql = "update news set title = ?, context = ?, coverbg = ? where nid = ?";
        PreparedStatement stmt = null;

        try{
            stmt = conn.prepareStatement(sql);


            stmt.setString(1, news.getTitle());
            stmt.setString(2,news.getContext());
            stmt.setString(3,news.getCoverbg());
            stmt.setInt(4, news.getNid());
            stmt.execute();

            return true;

        }catch (SQLException e){
            System.out.println("修改信息失败");
            e.printStackTrace();
            return false;
        }finally {
            Untils.release(null,stmt,conn);
        }



    }


    //      查询所有新闻(分页)

    public PageEntity getNewsPage(PageEntity pageEntityPra){
        Connection conn = Untils.getConnection();
        String sql = "select * from news ORDER BY nid asc limit ?,?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PageEntity pageEntity= new PageEntity(pageEntityPra.getPageNum(),pageEntityPra.getPageSize());
        List<News> newslist = new ArrayList<>();
        try{

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,pageEntity.getPageNum());
            stmt.setInt(2,pageEntity.getPageSize());
            rs = stmt.executeQuery();
            while (rs.next()){
                newslist.add(
                        new News(
                            rs.getInt("nid"),
                            rs.getString("title"),
                            rs.getString("context"),
                            rs.getString("coverbg")
                )
                );
            }

            String totalSql = "select count(*) as nid from news";//查总数
            ResultSet rsPageTotal;//查总数
            PreparedStatement stmts = null;//查总数
            stmts = conn.prepareStatement(totalSql);

            rsPageTotal =stmts.executeQuery();

            int total=0;
            while (rsPageTotal.next()){
                total =rsPageTotal.getInt("nid");
                System.out.println(total);
            }

            pageEntity.setPageTotal(total);
            pageEntity.setNewsList(newslist);





        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            Untils.release(rs,stmt,conn);
        }
        return pageEntity;

    }







}
