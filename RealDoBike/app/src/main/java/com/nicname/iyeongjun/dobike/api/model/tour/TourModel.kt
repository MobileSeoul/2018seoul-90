package com.nicname.iyeongjun.dobike.api.model.tour

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root
class TourModel {
    @get:Element
    var header: HeaderBean? = null
    @get:Element
    var body: BodyBean? = null

    @Root
    class HeaderBean {
        @get:Element
        var resultCode: String? = null
        @get:Element
        var resultMsg: String? = null
    }

    @Root
    class BodyBean {
        @get:Element
        var numOfRows: String? = null
        @get:Element
        var pageNo: String? = null
        @get:Element
        var totalCount: String? = null
        @get:ElementList
        var items: List<ItemBean>? = null

        @Root
        class ItemBean {
            @get:Element(required = false)
            var addr1: String? = null
            @get:Element(required = false)
            var addr2: String? = null
            @get:Element(required = false)
            var areacode: String? = null
            @get:Element(required = false)
            var cat1: String? = null
            @get:Element(required = false)
            var cat2: String? = null
            @get:Element(required = false)
            var cat3: String? = null
            @get:Element(required = false)
            var contentid: String? = null
            @get:Element(required = false)
            var contenttypeid: String? = null
            @get:Element(required = false)
            var createdtime: String? = null
            @get:Element(required = false)
            var dist: String? = null
            @get:Element(required = false)
            var firstimage: String? = null
            @get:Element(required = false)
            var firstimage2: String? = null
            @get:Element(required = false)
            var mapx: String? = null
            @get:Element(required = false)
            var mapy: String? = null
            @get:Element(required = false)
            var mlevel: String? = null
            @get:Element(required = false)
            var modifiedtime: String? = null
            @get:Element(required = false)
            var readcount: String? = null
            @get:Element(required = false)
            var sigungucode: String? = null
            @get:Element(required = false)
            var tel: String? = null
            @get:Element(required = false)
            var title: String? = null
            @get:Element(required = false)
            var booktour: String? = null
        }
    }
}
