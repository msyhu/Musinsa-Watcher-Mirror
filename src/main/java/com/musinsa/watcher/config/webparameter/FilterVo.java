package com.musinsa.watcher.config.webparameter;

import com.musinsa.watcher.domain.product.Category;
import java.util.Arrays;
import java.util.Objects;
import javax.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FilterVo {

  @Nullable
  private final String[] brands;
  @Nullable
  private final Category[] categories;
  @Nullable
  private final Integer minPrice;
  @Nullable
  private final Integer maxPrice;
  @Nullable
  private final Boolean onlyTodayUpdatedData;

  @Builder
  public FilterVo(String[] brands, Category[] categories, Integer minPrice, Integer maxPrice,
      Boolean onlyTodayUpdatedData) {
    this.brands = brands;
    this.categories = categories;
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.onlyTodayUpdatedData = onlyTodayUpdatedData;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof FilterVo)) {
      return false;
    }
    FilterVo input = (FilterVo) obj;
    return this.toString().equals(input.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.toString());
  }

  @Override
  public String toString() {
    return "FilterVo{" +
        "brands=" + Arrays.toString(brands) +
        ", categories=" + Arrays.toString(categories) +
        ", minPrice=" + minPrice +
        ", maxPrice=" + maxPrice +
        ", onlyTodayUpdatedData=" + onlyTodayUpdatedData +
        '}';
  }
}
