package com.nitesh.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nitesh.luceneutility.LuceneDocumentField;
import com.nitesh.luceneutility.LuceneDocumentFieldType;
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
    @LuceneDocumentField(value = "address", type = LuceneDocumentFieldType.StringField)
    @JsonProperty("address")
    public String address;

    @LuceneDocumentField(value = "city", type = LuceneDocumentFieldType.StringField)
    @JsonProperty("city")
    public String city;

    @LuceneDocumentField(value = "country", type = LuceneDocumentFieldType.StringField)
    @JsonProperty("country")
    public String country;

    @JsonProperty("crawl_date")
    public String crawlDate;

    @JsonProperty("hotel_brand")
    @LuceneDocumentField(value = "hotel_brand", type = LuceneDocumentFieldType.StringField)
    public String hotelBrand;

    @JsonProperty("hotel_description")
    @LuceneDocumentField(value = "hotel_description", type = LuceneDocumentFieldType.StringField)
    public String hotelDescription;

    @JsonProperty("hotel_star_rating")
    @LuceneDocumentField(value = "hotel_star_rating", type = LuceneDocumentFieldType.StringField)
    public String hotelStarRating;

    @JsonProperty("image_count")
    @LuceneDocumentField(value = "image_count", type = LuceneDocumentFieldType.StringField)
    public int imageCount;

    @JsonProperty("locality")
    @LuceneDocumentField(value = "locality", type = LuceneDocumentFieldType.StringField)
    public String locality;

    @JsonProperty("latitude")
    public double latitude;

    @JsonProperty("longitude")
    public double longitude;

    @JsonProperty("location")
    @LuceneDocumentField(value = "location", type = LuceneDocumentFieldType.LatLonField)
    public double[] latlon;

    public void setLatLon() {
        latlon = new double[]{this.latitude, this.longitude};
    }

    @JsonProperty("page_url")
    @LuceneDocumentField(value = "page_url", type = LuceneDocumentFieldType.StringField)
    public String pageUrl;

    @JsonProperty("property_id")
    @LuceneDocumentField(value = "property_id", type = LuceneDocumentFieldType.IntegerField)
    public int PropertyId;

    @JsonProperty("property_name")
    @LuceneDocumentField(value = "property_name", type = LuceneDocumentFieldType.StringField)
    public String PropertyName;

    @JsonProperty("property_type")
    @LuceneDocumentField(value = "property_type", type = LuceneDocumentFieldType.StringField)
    public String PropertyType;

    @JsonProperty("province")
    @LuceneDocumentField(value = "province", type = LuceneDocumentFieldType.StringField)
    public String province;

    @JsonProperty("qts")
    public String qts;

    @JsonProperty("room_count")
    @LuceneDocumentField(value = "room_count", type = LuceneDocumentFieldType.IntegerField)
    public int roomCount;

    @JsonProperty("room_type")
    @LuceneDocumentField(value = "room_type", type = LuceneDocumentFieldType.StringField)
    public String roomType;

    @JsonProperty("similar_hotel")
    @LuceneDocumentField(value = "similar_hotel", type = LuceneDocumentFieldType.StringField)
    public String similarHotel;

    @JsonProperty("site_review_count")
    @LuceneDocumentField(value = "site_review_count", type = LuceneDocumentFieldType.StringField)
    public String siteReviewCount;

    @JsonProperty("site_review_rating")
    @LuceneDocumentField(value = "site_review_rating", type = LuceneDocumentFieldType.DoubleField)
    public double siteReviewRating;

    @JsonProperty("site_stay_review_rating")
    @LuceneDocumentField(value = "site_stay_review_rating", type = LuceneDocumentFieldType.StringField)
    public String siteStayReviewRating;

    @JsonProperty("special_tag")
    @LuceneDocumentField(value = "special_tag", type = LuceneDocumentFieldType.StringField)
    public String specialTag;

    @JsonProperty("state")
    @LuceneDocumentField(value = "state", type = LuceneDocumentFieldType.StringField)
    public String state;

    @JsonProperty("unique_id")
    @LuceneDocumentField(value = "unique_id", type = LuceneDocumentFieldType.StringField)
    public String uniqueId;

    @JsonProperty("zone")
    @LuceneDocumentField(value = "zone", type = LuceneDocumentFieldType.StringField)
    public String zone;
}
