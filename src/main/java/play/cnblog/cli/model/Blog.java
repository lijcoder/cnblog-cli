package play.cnblog.cli.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 博文模型
 *
 * @author LiJie
 */
public class Blog {
    /**
     * 更新时间
     */
    private LocalDateTime dateCreated;
    /**
     * 内容
     */
    private String description;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章分类
     * - 博文源文档格式（Markdown）
     * - 个人分类（文章翻译）
     * - 网站分类（Linux）
     */
    private List<String> categories;
    private Object enclosure;
    /**
     * 博客园的博文地址
     * eg: https://www.cnblogs.com/newber/p/12444422.html
     */
    private String link;
    /**
     * 博客园的博文永久链接
     * eg: https://www.cnblogs.com/newber/p/12444422.html
     */
    private String permalink;
    /**
     * blog id 唯一标识
     */
    private String postid;
    private Object source;
    private String userId;
    private String mt_allow_comments;
    private String mt_allow_pings;
    private String mt_allow_breaks;
    private String mt_text_more;
    /**
     * 摘要
     */
    private String mt_excerpt;
    /**
     * Tag 标签，多个以英文","分割
     * eg: "linux,shell"
     */
    private String mt_keywords;
    private String mt_slug;

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Object getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Object enclosure) {
        this.enclosure = enclosure;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMt_allow_comments() {
        return mt_allow_comments;
    }

    public void setMt_allow_comments(String mt_allow_comments) {
        this.mt_allow_comments = mt_allow_comments;
    }

    public String getMt_allow_pings() {
        return mt_allow_pings;
    }

    public void setMt_allow_pings(String mt_allow_pings) {
        this.mt_allow_pings = mt_allow_pings;
    }

    public String getMt_allow_breaks() {
        return mt_allow_breaks;
    }

    public void setMt_allow_breaks(String mt_allow_breaks) {
        this.mt_allow_breaks = mt_allow_breaks;
    }

    public String getMt_text_more() {
        return mt_text_more;
    }

    public void setMt_text_more(String mt_text_more) {
        this.mt_text_more = mt_text_more;
    }

    public String getMt_excerpt() {
        return mt_excerpt;
    }

    public void setMt_excerpt(String mt_excerpt) {
        this.mt_excerpt = mt_excerpt;
    }

    public String getMt_keywords() {
        return mt_keywords;
    }

    public void setMt_keywords(String mt_keywords) {
        this.mt_keywords = mt_keywords;
    }

    public String getMt_slug() {
        return mt_slug;
    }

    public void setMt_slug(String mt_slug) {
        this.mt_slug = mt_slug;
    }
}
