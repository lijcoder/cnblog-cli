package play.cnblog.cli.rpc.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import play.cnblog.cli.config.GlobalConfig;
import play.cnblog.cli.model.Blog;
import play.cnblog.cli.model.Media;
import play.cnblog.cli.model.User;
import play.cnblog.cli.rpc.CnblogRpc;

/**
 * @author LiJie
 */
public class CnblogXmlRpc implements CnblogRpc {

    private static final String CREATE_BLOG = "metaWeblog.newPost";
    private static final String UPDATE_BLOG = "metaWeblog.editPost";
    private static final String GET_BLOG = "metaWeblog.getPost";
    private static final String DELETE_BLOG = "blogger.deletePost";
    private static final String UPLOAD_MEDIA = "metaWeblog.newMediaObject";
    private static final String GET_CATEGORIES = "metaWeblog.getCategories";

    private XmlRpcClient RPC_CLIENT;

    public CnblogXmlRpc() {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        try {
            config.setServerURL(new URL(GlobalConfig.getMetaWeblogUrl()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        RPC_CLIENT = new XmlRpcClient();
        RPC_CLIENT.setConfig(config);
    }

    @Override
    public String createBlog(Blog blog, User user) {
        List<Object> params = new ArrayList<>(5);
        params.add("");
        params.add(user.getUsername());
        params.add(user.getPassword());
        params.add(JSON.parse(JSON.toJSONString(blog)));
        params.add(true);
        try {
            Object result = RPC_CLIENT.execute(CREATE_BLOG, params);
            return (String) result;
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateBlog(String blogId, Blog blog, User user) {
        List<Object> params = new ArrayList<>(5);
        params.add(blogId);
        params.add(user.getUsername());
        params.add(user.getPassword());
        params.add(JSON.parse(JSON.toJSONString(blog)));
        params.add(true);
        try {
            Object result = RPC_CLIENT.execute(UPDATE_BLOG, params);
            return (boolean) result;
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Blog getBlog(String blogId, User user) {
        List<Object> params = new ArrayList<>(3);
        params.add(blogId);
        params.add(user.getUsername());
        params.add(user.getPassword());
        try {
            Object result = RPC_CLIENT.execute(GET_BLOG, params);
            return JSON.parseObject(JSON.toJSONString(result), Blog.class);
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteBlog(String blogId, User user) {
        List<Object> params = new ArrayList<>();
        params.add("");
        params.add(blogId);
        params.add(user.getUsername());
        params.add(user.getPassword());
        params.add(true);
        try {
            Object result = RPC_CLIENT.execute(DELETE_BLOG, params);
            return (boolean) result;
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String uploadMedia(Media media, User user) {
        List<Object> params = new ArrayList<>();
        params.add("");
        params.add(user.getUsername());
        params.add(user.getPassword());
        JSONObject jObj = JSON.parseObject(JSON.toJSONString(media));
        jObj.put("bits", media.getBits());
        params.add(jObj);
        try {
            Object result = RPC_CLIENT.execute(UPLOAD_MEDIA, params);
            return JSON.parseObject(JSON.toJSONString(result)).getString("url");
        } catch (XmlRpcException e) {
            e.printStackTrace();
            return null;
        }
    }
}
