package com.nicname.iyeongjun.dobike.api.model.tour;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class TourModel2 {
    @Element
    private HeaderBean header;
    @Element
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    @Root
    public static class HeaderBean {
        @Element
        private String resultCode;
        @Element
        private String resultMsg;

        public String getResultCode() {
            return resultCode;
        }

        public void setResultCode(String resultCode) {
            this.resultCode = resultCode;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }

    @Root
    public static class BodyBean {
        @Element
        private String numOfRows;
        @Element
        private String pageNo;
        @Element
        private String totalCount;
        @ElementList
        private List<ItemBean> items;

        public String getNumOfRows() {
            return numOfRows;
        }

        public void setNumOfRows(String numOfRows) {
            this.numOfRows = numOfRows;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public List<ItemBean> getItems() {
            return items;
        }

        public void setItems(List<ItemBean> items) {
            this.items = items;
        }

        @Root
        public static class ItemBean {
            @Element(required = false) private String addr1;
            @Element(required = false) private String addr2;
            @Element(required = false) private String areacode;
            @Element(required = false) private String cat1;
            @Element(required = false) private String cat2;
            @Element(required = false) private String cat3;
            @Element(required = false) private String contentid;
            @Element(required = false) private String contenttypeid;
            @Element(required = false) private String createdtime;
            @Element(required = false) private String dist;
            @Element(required = false) private String firstimage;
            @Element(required = false) private String firstimage2;
            @Element(required = false) private String mapx;
            @Element(required = false) private String mapy;
            @Element(required = false) private String mlevel;
            @Element(required = false) private String modifiedtime;
            @Element(required = false) private String readcount;
            @Element(required = false) private String sigungucode;
            @Element(required = false) private String tel;
            @Element(required = false) private String title;
            @Element(required = false) private String booktour;

            public String getAddr1() {
                return addr1;
            }

            public void setAddr1(String addr1) {
                this.addr1 = addr1;
            }

            public String getAddr2() {
                return addr2;
            }

            public void setAddr2(String addr2) {
                this.addr2 = addr2;
            }

            public String getAreacode() {
                return areacode;
            }

            public void setAreacode(String areacode) {
                this.areacode = areacode;
            }

            public String getCat1() {
                return cat1;
            }

            public void setCat1(String cat1) {
                this.cat1 = cat1;
            }

            public String getCat2() {
                return cat2;
            }

            public void setCat2(String cat2) {
                this.cat2 = cat2;
            }

            public String getCat3() {
                return cat3;
            }

            public void setCat3(String cat3) {
                this.cat3 = cat3;
            }

            public String getContentid() {
                return contentid;
            }

            public void setContentid(String contentid) {
                this.contentid = contentid;
            }

            public String getContenttypeid() {
                return contenttypeid;
            }

            public void setContenttypeid(String contenttypeid) {
                this.contenttypeid = contenttypeid;
            }

            public String getCreatedtime() {
                return createdtime;
            }

            public void setCreatedtime(String createdtime) {
                this.createdtime = createdtime;
            }

            public String getDist() {
                return dist;
            }

            public void setDist(String dist) {
                this.dist = dist;
            }

            public String getFirstimage() {
                return firstimage;
            }

            public void setFirstimage(String firstimage) {
                this.firstimage = firstimage;
            }

            public String getFirstimage2() {
                return firstimage2;
            }

            public void setFirstimage2(String firstimage2) {
                this.firstimage2 = firstimage2;
            }

            public String getMapx() {
                return mapx;
            }

            public void setMapx(String mapx) {
                this.mapx = mapx;
            }

            public String getMapy() {
                return mapy;
            }

            public void setMapy(String mapy) {
                this.mapy = mapy;
            }

            public String getMlevel() {
                return mlevel;
            }

            public void setMlevel(String mlevel) {
                this.mlevel = mlevel;
            }

            public String getModifiedtime() {
                return modifiedtime;
            }

            public void setModifiedtime(String modifiedtime) {
                this.modifiedtime = modifiedtime;
            }

            public String getReadcount() {
                return readcount;
            }

            public void setReadcount(String readcount) {
                this.readcount = readcount;
            }

            public String getSigungucode() {
                return sigungucode;
            }

            public void setSigungucode(String sigungucode) {
                this.sigungucode = sigungucode;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getBooktour() {
                return booktour;
            }

            public void setBooktour(String booktour) {
                this.booktour = booktour;
            }
        }
    }
}
