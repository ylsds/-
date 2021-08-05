package ds.domain;

public class Page {
    private Integer pageno;
    private Integer pagesize;
    private Integer pagetotel;//总条数
    private Integer pagecount;//总页数

    //起始页码
    private Integer begin;

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getPageno() {
        return pageno;
    }

    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPagetotel() {
        return pagetotel;
    }

    public void setPagetotel(Integer pagetotel) {
        this.pagetotel = pagetotel;
    }

    public Integer getPagecount() {
        return pagecount;
    }

    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageno=" + pageno +
                ", pagesize=" + pagesize +
                ", pagetotel=" + pagetotel +
                ", pagecount=" + pagecount +
                ", begin=" + begin +
                '}';
    }
}
