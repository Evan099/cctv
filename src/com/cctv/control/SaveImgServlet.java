package com.cctv.control;

import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/SaveImgServlet")
public class SaveImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        FileItemFactory factory = new DiskFileItemFactory();

        // 创建文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 开始解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(request);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }

        // 对所有请求信息进行判断
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            // 信息为普通的格式
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String value = item.getString();
                request.setAttribute(fieldName, value);
            }
            // 信息为文件格式
            else {
                String fileName = item.getName();
                System.out.println(fileName);
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                request.setAttribute("realFileName", fileName);


//                String basePath = request.getRealPath("/images");
                String basePath = "C:\\Users\\lipanchen\\Desktop\\imgbox\\";
                File file = new File(basePath, fileName);
                try {
                    item.write(file);
                    //12.9(返回到前端发布页面的wangEditor)
                    Map<String,Object> map=new HashMap<>();
                    map.put("errno","0");

                    String[] data=new String[1];
                    data[0]="\\Users\\lipanchen\\Desktop\\imgbox\\"+fileName;

                    map.put("data",data);

                    Gson gson = new Gson();
                    String json = gson.toJson(map);
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.append(json);
                }

                catch (Exception e) {
                    e.printStackTrace();
                }
            }

//            request.setAttribute("msg","文件上传成功!");
//            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }




    }


}

