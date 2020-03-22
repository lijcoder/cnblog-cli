package play.xmlrpc;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;
import com.alibaba.fastjson.JSON;
import net.coobird.thumbnailator.Thumbnails;
import play.cnblog.cli.model.Blog;

/**
 * @author LiJie
 */
public class XmlRpcClientTest {


    private static final String RPC_URL =
        "https://rpc.cnblogs.com/metaweblog/newber";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private static XmlRpcClient RPC_CLIENT = new XmlRpcClient();

    static {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(RPC_URL));
            RPC_CLIENT.setConfig(config);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private static final String CREATE_BLOG = "metaWeblog.newPost";
    private static final String UPDATE_BLOG = "metaWeblog.edidPost";
    private static final String GET_BLOG = "metaWeblog.getPost";
    private static final String DELETE_BLOG = "blogger.deletePost";
    private static final String CREATE_MEDIA = "metaWeblog.newMediaObject";
    private static final String GET_CATEGORIES = "metaWeblog.getCategories";

    @Test
    public void getBlog() throws XmlRpcException {
        List<Object> params = new ArrayList<>(3);
        params.add("12444422");
        params.add(USERNAME);
        params.add(PASSWORD);
        Object result = RPC_CLIENT.execute(GET_BLOG, params);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void createBlog() throws XmlRpcException {
        Blog blogModel = new Blog();
        blogModel.setTitle("模型创建");
        blogModel.setDescription("模型创建的内容");
        blogModel.setCategories(new ArrayList<String>() {{
            add("[Markdown]");
            add("[Shell]");
        }});

        List<Object> params = new ArrayList<>(20);
        params.add("");
        params.add(USERNAME);
        params.add(PASSWORD);
        params.add(JSON.parse(JSON.toJSONString(blogModel)));
        params.add(true);

        Object result = RPC_CLIENT.execute(CREATE_BLOG, params);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void crateMedia() throws XmlRpcException, IOException {
        Map<String, Object> fileData = new HashMap<>(3);
        fileData.put("name", "P.jpg");
        fileData.put("type", "image/jpg");
        fileData.put("bits", getBase64("AT.jpg"));

        List<Object> params = new ArrayList<>(20);
        params.add("");
        params.add(USERNAME);
        params.add(PASSWORD);
        params.add(fileData);
        Object result = RPC_CLIENT.execute(CREATE_MEDIA, params);
        System.out.println(JSON.toJSONString(result));
    }

    private byte[] getBase64(String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("/home/jie/Downloads/" + fileName));
    }

    @Test
    public void getCategories() throws XmlRpcException {
        List<Object> params = new ArrayList<>();
        params.add("");
        params.add(USERNAME);
        params.add(PASSWORD);

        Object result = RPC_CLIENT.execute(GET_CATEGORIES, params);
        System.out.println(JSON.toJSONString(result));
    }

    @Test
    public void pictureZip() throws IOException {
        String name = "Y";
        File source = new File("/home/jie/Downloads/" + name + ".jpg");
        File target = new File("/home/jie/Downloads/" + name + "T.jpg");
//        Thumbnails.of(source).scale(0.5f).outputQuality(0.25f).toFile(target);
        Thumbnails.of(source).size(400,400).outputQuality(1f).toFile(target);
    }
}
