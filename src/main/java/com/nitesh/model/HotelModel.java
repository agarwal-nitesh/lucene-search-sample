package com.nitesh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nitesh.indexer.LuceneDocumentField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HotelModel {
    @LuceneDocumentField("address")
    @JsonProperty("address")
    public String address;

    @LuceneDocumentField("city")
    @JsonProperty("city")
    public String city;

    @LuceneDocumentField("country")
    @JsonProperty("country")
    public String country;

    @JsonProperty("crawl_date")
    public String crawlDate;

    @JsonProperty("hotel_brand")
    @LuceneDocumentField("hotel_brand")
    public String hotelBrand;

    @JsonProperty("hotel_description")
    @LuceneDocumentField("hotel_description")
    public String hotelDescription;

    @JsonProperty("hotel_star_rating")
    @LuceneDocumentField("hotel_star_rating")
    public String hotelStarRating;

    @JsonProperty("image_count")
    @LuceneDocumentField("image_count")
    public int imageCount;

    @JsonProperty("locality")
    @LuceneDocumentField("locality")
    public String locality;

    @JsonProperty("latitude")
    @LuceneDocumentField("latitude")
    public double latitude;

    @JsonProperty("longitude")
    @LuceneDocumentField("longitude")
    public double longitude;

    @JsonProperty("page_url")
    @LuceneDocumentField("page_url")
    public String pageUrl;

    @JsonProperty("property_id")
    @LuceneDocumentField("property_id")
    public int PropertyId;

    @JsonProperty("property_name")
    @LuceneDocumentField("property_name")
    public String PropertyName;

    @JsonProperty("property_type")
    @LuceneDocumentField("property_type")
    public String PropertyType;

    @JsonProperty("province")
    @LuceneDocumentField("province")
    public String province;

    @JsonProperty("qts")
    public String qts;

    @JsonProperty("room_count")
    @LuceneDocumentField("room_count")
    public int roomCount;

    @JsonProperty("room_type")
    @LuceneDocumentField("room_type")
    public String roomType;

    @JsonProperty("similar_hotel")
    @LuceneDocumentField("similar_hotel")
    public String similarHotel;

    @JsonProperty("site_review_count")
    @LuceneDocumentField("site_review_count")
    public String siteReviewCount;

    @JsonProperty("site_review_rating")
    @LuceneDocumentField("site_review_rating")
    public double siteReviewRating;

    @JsonProperty("site_stay_review_rating")
    @LuceneDocumentField("site_stay_review_rating")
    public String siteStayReviewRating;

    @JsonProperty("special_tag")
    @LuceneDocumentField("special_tag")
    public String specialTag;

    @JsonProperty("state")
    @LuceneDocumentField("state")
    public String state;

    @JsonProperty("unique_id")
    @LuceneDocumentField("unique_id")
    public String uniqueId;

    @JsonProperty("zone")
    @LuceneDocumentField("zone")
    public String zone;
}
