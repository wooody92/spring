package com.example.batch.chunk.job;

import com.example.batch.chunk.core.PayEntity;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ProcessorConvertJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job processorConvertJob() {
        return jobBuilderFactory.get("processorConvertJob")
            .start(processorConvertStep())
            .build();
    }

    /**
     * processor & writer 는 transactional 범위 안에 있어서 Lazy Loading 처리가 가능하다.
     */
    @Bean
    public Step processorConvertStep() {
        return stepBuilderFactory.get("processorConvertStep")
            .<PayEntity, Long>chunk(CHUNK_SIZE)
            .reader(reader())
            .processor(processor2())
            .writer(writer())
            .build();
    }

    @Bean
    public JpaPagingItemReader<PayEntity> reader() {
        return new JpaPagingItemReaderBuilder<PayEntity>()
            .name("processorConvertReader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(CHUNK_SIZE)
            .queryString("SELECT p FROM PayEntity p")
            .build();
    }

    @Bean
    public ItemProcessor<PayEntity, Long> processor() {
        return payEntity -> payEntity.getAmount();
    }

    /**
     * return = null 인 경우에는 writer parameter 로 전달하지 않는다.
     */
    @Bean
    public ItemProcessor<PayEntity, Long> processor2() {
        return payEntity -> {
            if (payEntity.getAmount() < 3000L) {
                log.info("amount is less than 3000, actual amount is {}", payEntity.getAmount());
                return null;
            }
            return payEntity.getAmount();
        };
    }

    private ItemWriter<Long> writer() {
        return items -> {
            Long totalAmount = 0L;
            for (Long item : items) {
                totalAmount += item;
            }
            log.info("total_amount = {}", totalAmount);
        };
    }
}
