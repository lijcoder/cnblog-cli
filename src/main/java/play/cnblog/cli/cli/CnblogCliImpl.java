package play.cnblog.cli.cli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import play.cnblog.cli.config.GlobalConfig;
import play.cnblog.cli.model.Blog;
import play.cnblog.cli.model.User;
import play.cnblog.cli.rpc.CnblogRpc;
import play.cnblog.cli.rpc.impl.CnblogXmlRpc;

/**
 * @author LiJie
 */
public class CnblogCliImpl implements CnblogCli {

    private static final int COLUMN_MIN = 2;
    private static final int BLOG_INDEX = 0;
    private static final int TITLE_INDEX = 1;
    private static final int CATEGORIES_INDEX = 2;
    private static final int KEYWORDS_INDEX = 3;

    private static final User USER = GlobalConfig.getUser();

    private CnblogRpc cnblogRpc = new CnblogXmlRpc();

    @Override
    public String add(String filePath) {
        Blog blog = getBlog(filePath);
        if (StringUtils.isNotBlank(blog.getPostid())) {
            throw new RuntimeException("This blog already exists, please execute `update` command");
        }
        String blogId = cnblogRpc.createBlog(blog, USER);
        if (Objects.isNull(blogId)) {
            throw new RuntimeException("cnblog's blog create fail");
        }
        if (addId(blogId, filePath)) {
            return blogId;
        } else {
            throw new RuntimeException("blog id [" + blogId + "] add fail.");
        }
    }

    @Override
    public boolean update(String filePath) {
        Blog blog = getBlog(filePath);
        if (StringUtils.isBlank(blog.getPostid())) {
            throw new RuntimeException("[" + filePath + "] blog's id not exist.");
        }
        return cnblogRpc.updateBlog(blog.getPostid(), blog, USER);
    }

    @Override
    public boolean delete(String filePath) {
        String blogId = getId(filePath);
        if (StringUtils.isBlank(blogId)) {
            throw new RuntimeException("blog's id not exist.");
        }
        return cnblogRpc.deleteBlog(blogId, USER);
    }

    @Override
    public String uploadMedia(String filePath) {
        return null;
    }

    @Override
    public Blog get(String filePath) {
        String blogId = getId(filePath);
        if (StringUtils.isBlank(blogId)) {
            throw new RuntimeException("blog's id not exist.");
        }
        return cnblogRpc.getBlog(blogId, USER);
    }

    String getId(String filePath) {
        List<String> contents;
        try {
            contents = Files.readAllLines(getPath(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String firstLine = contents.get(0);
        if (Objects.isNull(firstLine)) {
            throw new RuntimeException("blog's id not exist.");
        }
        return firstLine.split("\\|")[0];
    }

    private boolean addId(String id, String filePath) {
        List<String> contents;
        try {
            contents = Files.readAllLines(getPath(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String firstLine = contents.get(0);
        contents.set(0, id + firstLine);
        try {
            Files.write(getPath(filePath), contents);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Blog getBlog(String filePath) {
        List<String> contents;
        try {
            contents = Files.readAllLines(getPath(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String firstLine = contents.get(0);
        if (Objects.isNull(firstLine)) {
            throw new RuntimeException("第一行没有相关信息");
        }
        String[] info = firstLine.split("\\|");
        if (info.length < COLUMN_MIN) {
            throw new RuntimeException("行首信息缺失");
        }
        Blog blog = new Blog();
        blog.setPostid(info[BLOG_INDEX]);
        blog.setTitle(info[TITLE_INDEX]);
        blog.setMt_keywords(keywords(info));
        blog.setCategories(categories(info));
        blog.setDescription(content(contents));
        return blog;
    }

    private Path getPath(String filePath) {
        return Paths.get(filePath);
    }

    private List<String> categories(String[] info) {
        if (info.length - 1 - CATEGORIES_INDEX < 0) {
            return null;
        }
        String[] tmpCategories = info[CATEGORIES_INDEX].split(",");
        List<String> categories = new ArrayList<>(tmpCategories.length + 1);
        categories.add("[Markdown]");
        for (String tmp : tmpCategories) {
            categories.add("[随笔分类]" + tmp.trim());
        }
        return categories;
    }

    private String keywords(String[] info) {
        if (info.length - KEYWORDS_INDEX < 0) {
            return null;
        }
        return info[KEYWORDS_INDEX];
    }

    private String content(List<String> contentList) {
        contentList.remove(0);
        StringBuilder blogContent = new StringBuilder();
        for (String line : contentList) {
            blogContent.append(line).append("\n");
        }
        return blogContent.toString();
    }
}
