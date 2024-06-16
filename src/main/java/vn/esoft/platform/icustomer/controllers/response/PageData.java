package vn.esoft.platform.icustomer.controllers.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageData<T> {

    private T data;
    private Integer pageSize;
    private Integer pageNum;
    private long total;
}
