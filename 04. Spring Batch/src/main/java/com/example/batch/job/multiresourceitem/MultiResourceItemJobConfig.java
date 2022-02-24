package com.example.batch.job.multiresourceitem;

import com.example.batch.dto.ItemDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MultiResourceItemJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final int CHUNK_SIZE = 3;

    @Bean
    public Job multiResourceItemJob() {
        return jobBuilderFactory.get("multiResourceItemJob")
            .start(multiResourceItemStep())
            .build();
    }

    @Bean
    public Step multiResourceItemStep() {
        return stepBuilderFactory.get("multiResourceItemStep")
            .<ItemDto, ItemDto>chunk(CHUNK_SIZE)
            .reader(multiResourceItemReader())
            .writer(printWriter())
            .build();
    }

    @Bean
    @StepScope
    public MultiResourceItemReader<ItemDto> multiResourceItemReader() {
        /* get multi resources path */
        String filePath = "/Users/henry.yoon/Documents/private/spring/04. Spring Batch/src/main/resources/csv/";
        String fileName1 = "inputSample01.csv";
        String fileName2 = "inputSample02.csv";

        FileSystemResource resource1 = new FileSystemResource(filePath + fileName1);
        FileSystemResource resource2 = new FileSystemResource(filePath + fileName2);
        List<Resource> resources = List.of(resource1, resource2);
        Resource[] resourcesArray = resources.toArray(new Resource[resources.size()]);

        /* run MultiResourceItemReader */
        return new MultiResourceItemReaderBuilder<ItemDto>()
            .name("multiResourceItemReader")
            .resources(resourcesArray)
//            .resources(resource1, resource2)
            .delegate(multiFileItemReader())
            .build();
    }

    @Bean
    public FlatFileItemReader<ItemDto> multiFileItemReader() {
        return new FlatFileItemReaderBuilder<ItemDto>()
            .name("multiFileItemReader")
            .linesToSkip(1)
            .delimited().delimiter(",")
            .names("name", "age")
            .targetType(ItemDto.class)
            .build();
    }

    private ItemWriter<ItemDto> printWriter() {
        return items -> {
            for (ItemDto item : items) {
                log.info("Current item = {}", item);
            }
            log.info("=======================");
        };
    }
}
