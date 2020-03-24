
package com.thucnh.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingResponseModel<T> {
    private List<T> data;
    private long totalResult;
    private int totalPage;
    private int currentPage;
}
