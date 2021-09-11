package com.example.batch.chunk.job;

import com.example.batch.chunk.core.PayEntity;
import com.example.batch.chunk.core.PayRepository;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JpaPagingItemReaderJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private final PayRepository payRepository;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job jpaPagingItemReaderJob() {
        return jobBuilderFactory.get("jpaPagingItemReaderJob")
            .start(jpaPagingItemReaderStep())
            .build();
    }

    @Bean
    public Step jpaPagingItemReaderStep() {
        return stepBuilderFactory.get("jpaPagingItemReaderStep")
            .<PayEntity, PayEntity>chunk(CHUNK_SIZE)
            .reader(repositoryItemReader())
            .writer(jpaPagingItemWriter())
            .build();
    }

    @Bean
    public JpaPagingItemReader<PayEntity> jpaPagingItemReader() {
        return new JpaPagingItemReaderBuilder<PayEntity>()
            .name("jpaPagingItemReader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(CHUNK_SIZE)   /* 일반적으로 영속성 컨텍스트가 필요한 reader 사용 시 fetch size와 chunk size는 같은 값을 유지 */
            .queryString("SELECT p FROM PayEntity p WHERE amount >= 2000")
            .build();
    }

    private ItemWriter<PayEntity> jpaPagingItemWriter() {
        return list -> {
            for (PayEntity pay : list) {
                log.info("Current Pay={}", pay);
            }
        };
    }

    /**
     * 아래와 같은 방식은 spring batch의 장점인 페이징 구현이 없어 대규모 처리가 불가능하다.(chunk 단위 트랜잭션은 가능)
     * repository 사용이 필요하면 RepositoryItemReader 사용하는 것을 권장
     */
    @Bean
    public ListItemReader<PayEntity> listItemReader() {
        return new ListItemReader<>(payRepository.findAllByAmountGreaterThanEqual(2000L));
    }

    @Bean
    @StepScope
    public RepositoryItemReader<PayEntity> repositoryItemReader() {
        return new RepositoryItemReaderBuilder<PayEntity>()
            .repository(payRepository)
            .methodName("findAllByAmountGreaterThanEqual")
            .pageSize(CHUNK_SIZE)
            .maxItemCount(CHUNK_SIZE)
            .arguments(2000L)
            .sorts(Collections.singletonMap("id", Direction.ASC))
            .name("repositoryItemReader")
            .build();
    }
}
