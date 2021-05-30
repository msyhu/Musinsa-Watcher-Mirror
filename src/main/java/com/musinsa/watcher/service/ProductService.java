package com.musinsa.watcher.service;

import com.musinsa.watcher.domain.product.ProductRepository;
import com.musinsa.watcher.web.dto.ProductCountMapByBrandDto;
import com.musinsa.watcher.web.dto.Filter;
import com.musinsa.watcher.web.dto.ProductResponseDto;
import com.musinsa.watcher.web.dto.ProductWithPriceResponseDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProductService {

  private final ProductRepository productRepository;

  public Page<ProductResponseDto> findByBrand(Filter filter, Pageable pageable) {
    return productRepository.findByBrand(filter, pageable);
  }

  public ProductCountMapByBrandDto searchBrand(String brand) {
    return new ProductCountMapByBrandDto(productRepository.searchBrand(brand));
  }

  @Cacheable(value = "productCache", key = "'category'+#filter.toString()+#pageable.pageNumber+#pageable.pageSize")
  public Page<ProductResponseDto> findByCategory(Filter filter, Pageable pageable) {
    LocalDate lastUpdatedDate = productRepository.findCachedLastUpdatedDateTime().toLocalDate();
    return productRepository.findByCategoryAndDate(filter, lastUpdatedDate, pageable);
  }

  public ProductWithPriceResponseDto findProductWithPrice(int productId) {
    return new ProductWithPriceResponseDto(productRepository.findByProductIdWithPrice(productId));
  }

  @Cacheable(value = "productCache", key = "'brand-initial'+#initial1+#initial2")
  public ProductCountMapByBrandDto findBrandByInitial(String initial1, String initial2) {
    return new ProductCountMapByBrandDto(productRepository.findBrandByInitial(initial1, initial2));
  }

  public Page<ProductResponseDto> searchItems(String text, Filter filter, Pageable pageable) {
    return productRepository.searchItems(text, filter, pageable);
  }

  public LocalDateTime getLastUpdatedDateTime() {
    return productRepository.findLastUpdatedDate();
  }

  public LocalDateTime getCachedLastUpdatedDateTime() {
    return productRepository.findCachedLastUpdatedDateTime();
  }

  @CacheEvict(value = "productCache", condition = "#lastUpdatedDateTime.isAfter(#cachedDateTime)", allEntries = true)
  public void clearCacheIfOld(LocalDateTime lastUpdatedDateTime, LocalDateTime cachedDateTime) {
    log.debug(lastUpdatedDateTime.isAfter(cachedDateTime) + "");
  }

}
