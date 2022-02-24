package com.example.batch.job.jpapagingitem;

import com.example.batch.core.entity.PayEntity;
import com.example.batch.core.entity.StatisticEntity;
import com.example.batch.core.repository.StatisticRepository;
import javax.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JpaItemWriterJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private final StatisticRepository statisticRepository;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job jpaItemWriterJob() {
        return jobBuilderFactory.get("jpaItemWriterJob")
            .start(jpaItemWriterStep())
            .build();
    }

    @Bean
    public Step jpaItemWriterStep() {
        return stepBuilderFactory.get("jpaItemWriterStep")
            .<PayEntity, StatisticEntity>chunk(CHUNK_SIZE) /* <Reader return type, Writer parameter type> */
            .reader(jpaItemWriterReader())
            .processor(jpaItemProcessor())
            .writer(repositoryItemWriter())
            .build();
    }

    @Bean
    public JpaPagingItemReader<PayEntity> jpaItemWriterReader() {
        return new JpaPagingItemReaderBuilder<PayEntity>()
            .name("jpaItemWriterReader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(CHUNK_SIZE)
            .queryString("SELECT p FROM PayEntity p")
            .build();
    }

    @Bean
    public ItemProcessor<PayEntity, StatisticEntity> jpaItemProcessor() {
        return pay -> new StatisticEntity(pay.getAmount());
    }

    @Bean
    public JpaItemWriter<StatisticEntity> jpaItemWriter() {
        JpaItemWriter<StatisticEntity> jpaItemWriter = new JpaItemWriter<>();
        jpaItemWriter.setEntityManagerFactory(entityManagerFactory);
        return jpaItemWriter;
    }

    @Bean
    public RepositoryItemWriter<StatisticEntity> repositoryItemWriter() {
        return new RepositoryItemWriterBuilder<StatisticEntity>()
            .repository(statisticRepository)
            .methodName("save")
            .build();
    }
}
