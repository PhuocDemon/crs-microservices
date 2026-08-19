package vn.edu.crs.registrationservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

// Client HTTP goi sang course-service de reserve/release seat
// Phai dung HttpComponentsClientHttpRequestFactory (Apache HttpClient)
// vi Java HttpURLConnection mac dinh KHONG ho tro HTTP PATCH method
@Component
public class CourseClient {

    private final RestTemplate restTemplate;

    // Doc URL tu application.properties: course.service.base-url
    private final String baseUrl;

    public CourseClient(@Value("${course.service.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        this.baseUrl = baseUrl;
    }

    /**
     * Goi PATCH /internal/courses/{courseId}/reserve-seat tren course-service.
     * 200: giam soChoConLai di 1
     * 409: Het cho → IllegalStateException
     * 404: Course khong ton tai → IllegalArgumentException
     * Connection refused: course-service tat → IllegalStateException
     */
    public void reserveSeat(Long courseId) {
        try {
            String url = baseUrl + "/internal/courses/" + courseId + "/reserve-seat";
            restTemplate.exchange(url, HttpMethod.PATCH, null, Object.class);
        } catch (HttpClientErrorException.Conflict e) {
            throw new IllegalStateException("Mon hoc da het cho, khong the dang ky");
        } catch (HttpClientErrorException.NotFound e) {
            throw new IllegalArgumentException("Mon hoc voi ID=" + courseId + " khong ton tai");
        } catch (ResourceAccessException e) {
            throw new IllegalStateException("Khong the ket noi toi course-service");
        }
    }

    /**
     * Goi PATCH /internal/courses/{courseId}/release-seat tren course-service.
     * 200: tang soChoConLai len 1
     * Bo qua loi — khong nen fail huy dang ky chi vi release that bai
     */
    public void releaseSeat(Long courseId) {
        try {
            String url = baseUrl + "/internal/courses/" + courseId + "/release-seat";
            restTemplate.exchange(url, HttpMethod.PATCH, null, Object.class);
        } catch (HttpClientErrorException.NotFound e) {
            // Bo qua — course co the da bi xoa
        } catch (ResourceAccessException e) {
            // Bo qua — course-service bi tat, khong the release
        }
    }
}
